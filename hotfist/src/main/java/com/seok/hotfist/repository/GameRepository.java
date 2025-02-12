package com.seok.hotfist.repository;

import com.seok.hotfist.aggregate.GameLog;
import com.seok.hotfist.aggregate.Status;

import java.io.*;
import java.util.ArrayList;

/* 랭킹 저장 및 조회 */
public class GameRepository {

    private final ArrayList<GameLog> gameLogList = new ArrayList<>();
    private final File gameLogFile =
            new File("src/main/java/com/seok/hotfist/db/gameLogDB.dat");

    java.util.Date today = new java.util.Date();

    public GameRepository() {
        if(!gameLogFile.exists()) {
            ArrayList<GameLog> defaultGameLogList = new ArrayList<>();

            // 더미데이터 추가
            defaultGameLogList.add(new GameLog(1, 1, 10, new java.util.Date(today.getTime()), Status.ACTIVE));
            defaultGameLogList.add(new GameLog(2, 1, 20, new java.util.Date(today.getTime()), Status.ACTIVE));
            defaultGameLogList.add(new GameLog(3, 1, 30, new java.util.Date(today.getTime()), Status.ACTIVE));

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

    public void saveGameScore(int totalScore) {
    }

    public int getHighScore() {
        return 0;
    }

    public ArrayList<GameLog> selectAllGameLogs() {
        return gameLogList;
    }
}
