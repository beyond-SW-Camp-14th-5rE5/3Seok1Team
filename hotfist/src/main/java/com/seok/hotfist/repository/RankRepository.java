package com.seok.hotfist.repository;

import com.seok.hotfist.aggregate.GameLog;
import java.io.*;
import java.util.*;

public class RankRepository {
    private final List<GameLog> rankList = new ArrayList<>();
    private final File file = new File("src/main/java/com/seok/hotfist/db/rankDB.dat");

    public RankRepository() {
        loadRanks();
    }

    public void saveRank(GameLog gameLog) {
        rankList.add(gameLog);
        Collections.sort(rankList, new RankComparator());

        if (rankList.size() > 10) {
            rankList.subList(10, rankList.size()).clear();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(file)))) {
            oos.writeObject(rankList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GameLog> getRanks() {
        return rankList;
    }

    private void loadRanks() {
        if (!file.exists()) {
            System.out.println("초기 랭크 데이터를 생성합니다.");
            createInitialData();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(file)))) {
            List<GameLog> loadedList = (List<GameLog>) ois.readObject();
            rankList.clear();
            rankList.addAll(loadedList);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("랭크 데이터 로드 중 오류가 발생했습니다. 초기 데이터를 생성합니다.");
            createInitialData();
        }
    }

    private void createInitialData() {
        rankList.clear();
        // 초기 랭킹 데이터는 비워둡니다
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(file)))) {
            oos.writeObject(rankList);
        } catch (IOException e) {
            throw new RuntimeException("초기 데이터 저장 중 오류가 발생했습니다.", e);
        }
    }

    private static class RankComparator implements Comparator<GameLog> {
        @Override
        public int compare(GameLog g1, GameLog g2) {
            return Integer.compare(g2.getScore(), g1.getScore()); // 점수 내림차순 정렬
        }
    }
}