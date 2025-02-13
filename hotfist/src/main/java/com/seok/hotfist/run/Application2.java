package com.seok.hotfist.run;

import com.seok.hotfist.service.GameService;

import java.io.IOException;

public class Application2 {

    private static final GameService gs = new GameService();

    public static void main(String[] args) throws IOException {
        System.out.println("1번 회원 게임 기록 확인");
        gs.findLastMyGameLogs(1, 60);

        gs.gameIntro();
    }
}
