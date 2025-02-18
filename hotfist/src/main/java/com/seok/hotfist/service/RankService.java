package com.seok.hotfist.service;

import com.seok.hotfist.aggregate.GameLog;
import com.seok.hotfist.aggregate.Member;
import com.seok.hotfist.repository.RankRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class RankService {
    private final RankRepository rankRepository = new RankRepository();
    private final GameService gameService = new GameService();

    public void registerRank(int memberId, String nickname, int score) {
        GameLog gameLog = new GameLog();
        gameLog.setMemNo(memberId);
        gameLog.setScore(score);
        gameLog.setDateTime(LocalDateTime.now());

        rankRepository.saveRank(gameLog);
        System.out.println(nickname + "님의 점수 " + score + "점이 랭킹에 등록되었습니다.");
    }

    public List<GameLog> getTopRanks(int n) {
        List<GameLog> topRanksList = new ArrayList<>();
        List<GameLog> gameLogList = gameService.getAllGameLogs();

        // 최상위 점수
        Queue<GameLog> sortingGameLogQueue =
                new PriorityQueue<>((gl1, gl2) -> gl2.getScore() - gl1.getScore());

        if (!gameLogList.isEmpty()) {
            // 최상위 n개 반환
            sortingGameLogQueue.addAll(gameLogList);

            // 최상위 n개만 데이터 리스트로 변환
            int i = 0;
            while (!sortingGameLogQueue.isEmpty()) {
                if (i++ >= n) break;
                topRanksList.add(sortingGameLogQueue.poll());
            }
        }

        return topRanksList;
    }

//    public void displayRanks() {
//        List<Member> ranks = rankRepository.getRanks();
//        System.out.println("===== 불주먹 랭킹 목록 =====");
//
//        if (ranks.isEmpty()) {
//            System.out.println("등록된 랭킹이 없습니다.");
//            return;
//        }
//
//        int currentRank = 1;
//        int sameRankCount = 0;
////        int previousScore = ranks.get(0).getScore();
//
//        for (int i = 0; i < ranks.size(); i++) {
//            Member member = ranks.get(i);
//
////            if (i > 0 && previousScore != member.getScore()) {
////                currentRank += sameRankCount;
////                sameRankCount = 0;
////            }
////
////            System.out.println(currentRank + "위 | " + member.getNickname() + " | " + member.getScore() + "점");
////
////            previousScore = member.getScore();
////            sameRankCount++;
//        }
//    }
}