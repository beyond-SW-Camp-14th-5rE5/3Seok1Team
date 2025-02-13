package com.seok.hotfist.repository;

import com.seok.hotfist.aggregate.Member;
import com.seok.hotfist.aggregate.Status;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RankRepository {
    private final List<Member> rankList = new ArrayList<>();
    private final File file = new File("src/main/java/com/seok/hotfist/db/rankDB.dat");

    public RankRepository() {
        loadRanks();
    }

    public void saveRank(Member member) {
        rankList.add(member);
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

    public List<Member> getRanks() {
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
            List<Member> loadedList = (List<Member>) ois.readObject();
            rankList.clear();
            rankList.addAll(loadedList);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("랭크 데이터 로드 중 오류가 발생했습니다. 초기 데이터를 생성합니다.");
            createInitialData();
        }
    }

    private void createInitialData() {
        rankList.clear();

//        rankList.add(new Member(1, "user01", "pass01", "리신", 300));
//        rankList.add(new Member(2, "user02", "pass02", "엘리스", 224));
//        rankList.add(new Member(3, "user03", "pass03", "카타리나", 150));
//        rankList.add(new Member(4, "user04", "pass04", "멜", 280));
//        rankList.add(new Member(5, "user05", "pass05", "오로라", 260));
//        rankList.add(new Member(6, "user06", "pass06", "요네", 198));
//        rankList.add(new Member(7, "user07", "pass07", "뽀삐", 170));
//        rankList.add(new Member(8, "user08", "pass08", "제이스", 185));
//        rankList.add(new Member(9, "user09", "pass09", "징크스", 190));
//        rankList.add(new Member(10, "user10", "pass10", "케인", 177));

        rankList.add(new Member(1, "user01", "pass01", "리신", Status.ACTIVE));
        rankList.add(new Member(2, "user02", "pass02", "엘리스", Status.ACTIVE));
        rankList.add(new Member(3, "user03", "pass03", "카타리나", Status.ACTIVE));

        Collections.sort(rankList, new RankComparator());

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(file)))) {
            oos.writeObject(rankList);
        } catch (IOException e) {
            throw new RuntimeException("초기 데이터 저장 중 오류가 발생했습니다.", e);
        }
    }

    private static class RankComparator implements Comparator<Member> {
        @Override
        public int compare(Member m1, Member m2) {
//            return Integer.compare(m2.getScore(), m1.getScore());
            return 0;
        }
    }
}