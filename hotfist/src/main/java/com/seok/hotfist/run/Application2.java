package com.seok.hotfist.run;

import com.seok.hotfist.aggregate.GameLog;
import com.seok.hotfist.service.GameService;
import com.seok.hotfist.service.RankService;

import java.io.IOException;
import java.util.List;

public class Application2 {

    private static final GameService gs = new GameService();
    private static final RankService rs = new RankService();

    public static void main(String[] args) throws IOException {
        System.out.println("1번 회원 게임 기록 확인");

        gs.gameIntro();
//        gs.findLastMyGameLogs(1, 60);

//        gs.getTopScore(2);


        // 테스트용 랭킹 출력
//        List<GameLog> topRanks = rs.getTopRanks(10);
        List<GameLog> topRanks = gs.getLastMyGameLogs(1, 60);
        for(GameLog topRank : topRanks) {
            System.out.println(topRank);
        }

    }
}
