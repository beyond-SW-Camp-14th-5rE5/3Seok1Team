package com.seok.hotfist.run;

import com.seok.hotfist.service.GameService;

public class Application2 {

    private static final GameService gs = new GameService();

    public static void main(String[] args) {
        System.out.println("1번 회원 게임 기록 확인");
        gs.findLastMyGameLogs(1, 2);
    }
}
