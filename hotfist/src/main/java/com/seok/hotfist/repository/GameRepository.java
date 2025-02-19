package com.seok.hotfist.repository;

import com.seok.hotfist.aggregate.GameLog;
import com.seok.hotfist.aggregate.Status;
import com.seok.hotfist.stream.MyObjectOutput;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/* 랭킹 저장 및 조회 */
public class GameRepository {

    private final ArrayList<GameLog> gameLogList = new ArrayList<>();
    private final File gameLogFile =
            new File("src/main/java/com/seok/hotfist/db/gameLogDB.dat");

    public GameRepository() {
        if(!gameLogFile.exists()) {
            ArrayList<GameLog> defaultGameLogList = new ArrayList<>();

            // 더미데이터 추가
            defaultGameLogList.add(new GameLog(1, 1, 10, LocalDateTime.now()));
            defaultGameLogList.add(new GameLog(2, 1, 20, LocalDateTime.of(2025, 2, 5, 14, 33 ,20)));
            defaultGameLogList.add(new GameLog(3, 1, 30, LocalDateTime.of(2025, 3, 5, 14, 33 ,20)));
            defaultGameLogList.add(new GameLog(2, 1, 30, LocalDateTime.of(2024, 3, 5, 14, 33 ,20)));
            defaultGameLogList.add(new GameLog(3, 1, 30, LocalDateTime.of(2023, 3, 5, 14, 33 ,20)));
            defaultGameLogList.add(new GameLog(3, 1, 30, LocalDateTime.of(2021, 3, 5, 14, 33 ,20)));

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
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(gameLogFile)
                )
        )) {
            for(GameLog gameLog : inputGmaeLogList) {
                oos.writeObject(gameLog);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<GameLog> selectAllGameLogs() {
        return gameLogList;
    }

    // 마지막 게임 번호
    public int selectLastGameNo() {
        GameLog lastGame = gameLogList.get(gameLogList.size() - 1);
        return lastGame.getGameNo();
    }

    // 로그인한 회원의 전적 List<GameLog>로 반환
    public List<GameLog> getLastMyGameLogs(int memNo, int count) {
        List<GameLog> myGameLogList = FindMyGameLogs(memNo);
        List<GameLog> resultList = new ArrayList<>();

        // 최신순으로 정렬
        Queue<GameLog> gameLogQueue =
                new PriorityQueue<>(Comparator.comparing(GameLog::getDateTime).reversed());
        if(!myGameLogList.isEmpty()) {
            // pq에 모든 데이터 저장
            gameLogQueue.addAll(myGameLogList);

            int i = 0;
            // 가공 완료된 데이터 리스트에 저장 (count 이하만큼만 저장)
            while(!gameLogQueue.isEmpty()) {
                if(i++ >= count) break;
                resultList.add(gameLogQueue.poll());
            }
        }

        return resultList;
    }

    // 모든 데이터에서 로그인한 회원에 해당하는 List<GameLog> 반환
    public List<GameLog> FindMyGameLogs(int memNo) {
        List<GameLog> returnGameLog = new ArrayList<>();

        for(GameLog gameLog : gameLogList) {
            if(gameLog.getMemNo() == memNo) {
                returnGameLog.add(gameLog);
            }
        }
        return returnGameLog;
    }

    // 게임 끝나면 로그 저장
    public int saveGameScore(GameLog gameLog) {
        int result = 0;
        MyObjectOutput moo = null;

        try {
            moo = new MyObjectOutput(
                    new BufferedOutputStream(
                            new FileOutputStream(gameLogFile, true)
                    )
            );

            moo.writeObject(gameLog);
            gameLogList.add(gameLog);

            result = 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(moo != null) moo.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

}