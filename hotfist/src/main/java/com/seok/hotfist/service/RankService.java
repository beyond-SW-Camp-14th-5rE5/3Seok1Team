package com.seok.hotfist.service;

import com.seok.hotfist.aggregate.Member;
import com.seok.hotfist.repository.RankRepository;

import java.util.List;

public class RankService {
    private final RankRepository rankRepository = new RankRepository();

    public void registerRank(int memberId, String nickname, int score) {
        Member member = new Member();
        rankRepository.saveRank(member);
        System.out.println("신기록을 달성하여 " + nickname + "님의 점수 " + score + " 점이 랭킹에 등록되었습니다.");
    }

    public void displayRanks() {
        List<Member> ranks = rankRepository.getRanks();
        System.out.println("===== 불주먹 랭킹 목록 =====");
        for (int i = 0; i < ranks.size(); i++) {
            Member member = ranks.get(i);
            System.out.println((i + 1) + "위 | " + member.getNickname() + " | " + member.getScore() + "점");
        }
    }


}