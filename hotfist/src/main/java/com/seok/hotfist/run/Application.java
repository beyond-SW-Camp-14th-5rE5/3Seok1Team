package com.seok.hotfist.run;

import com.seok.hotfist.service.GameService;

public class Application {
    private static final GameService gs = new GameService();

    public static void main(String[] args) {

        System.out.println("게임 기록 확인");
        gs.findAllGameLogs();

//        gs.findMyGameLogs(1);
//        System.out.println("내 게임 데이터 확인");
//        gs.findLastGameLogs(1, 2);
    }
}