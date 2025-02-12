package com.seok.hotfist.run;

import com.seok.hotfist.service.GameService;

public class Application {
    private static final GameService gs = new GameService();

    public static void main(String[] args) {

        System.out.println("게임 기록 확인");
        gs.findAllGameLogs();
    }
}