package com.seok.hotfist.repository;

import com.seok.hotfist.aggregate.GameLog;
import com.seok.hotfist.aggregate.Status;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/* 랭킹 저장 및 조회 */
public class GameRepository {

    private final ArrayList<GameLog> gameLogList = new ArrayList<>();
    private final File gameLogFile =
            new File("src/main/java/com/seok/hotfist/db/gameLogDB.dat");

    private final ArrayList<GameLog> myGameLogList = new ArrayList<>();

    public GameRepository() {
        if(!gameLogFile.exists()) {
            ArrayList<GameLog> defaultGameLogList = new ArrayList<>();

            // 더미데이터 추가
            defaultGameLogList.add(new GameLog(1, 1, 10, LocalDateTime.now(), Status.ACTIVE));
            defaultGameLogList.add(new GameLog(2, 1, 20, LocalDateTime.now(), Status.ACTIVE));
            defaultGameLogList.add(new GameLog(3, 1, 30, LocalDateTime.now(), Status.ACTIVE));

            saveGameLog(defaultGameLogList);
        }
        
        loadGameList();
    }

    // 게임 기록 로드
    private void loadGameList() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(gameLogFile)
                )
        )) {
            while(true) {
                gameLogList.add((GameLog)ois.readObject());
            }
        } catch(EOFException e) {
            System.out.println("게임 기록 데이터 다 읽어옴");
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // 게임 데이터 저장
    private void saveGameLog(ArrayList<GameLog> inputGmaeLogList) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(gameLogFile)
                    )
            );
            for(GameLog gameLog : inputGmaeLogList) {
                oos.writeObject(gameLog);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 로그인한 회원의 모든 게임 기록
    public ArrayList<GameLog> selectMyGameLogs() {
        return myGameLogList;
    }

    // 로그인하면 회원의 게임 기록 저장 (로그인 시 호출)
    public List<GameLog> FindMyGameLogs(int memNo) {
        List<GameLog> returnGameLog = new ArrayList<>();

        for(GameLog gameLog : gameLogList) {
            if(gameLog.getMemNo() == memNo) {
                returnGameLog.add(gameLog);
            }
        }
        return returnGameLog;
    }

    public void saveGameScore(int totalScore) {
    }

    public ArrayList<GameLog> selectAllGameLogs() {
        return gameLogList;
    }

    public List<GameLog> getLastMyGameLogs(int count) {
        List<GameLog> resultList = new ArrayList<>();
        Queue<GameLog> gameLogQueue =
                new PriorityQueue<>(Comparator.comparing(GameLog::getDateTime).reversed());

        // myGameLogList의 개수만큼 || count 이하까지 queue에 저장
        int i = 0;
        for(GameLog log : myGameLogList) {
            if(i >= count) break;
            gameLogQueue.add(log);
        }

        // 정렬된 데이터 리스트에 저장
        while(!gameLogQueue.isEmpty()) {
            resultList.add(gameLogQueue.poll());
        }

        return resultList;
    }
}
