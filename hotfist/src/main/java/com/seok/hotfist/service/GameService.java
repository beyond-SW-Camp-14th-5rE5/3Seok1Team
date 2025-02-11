package com.seok.hotfist.service;

import java.io.IOException;

/* 게임 로직 처리 */
public class GameService {


    private void awakeGame() throws IOException {
        System.out.println("""
                전설의 격파 대회가 시작됐다!
                각 스테이지마다 강력한 고수가 송판을 지키고 있다.
                그들의 격파 점수를 뛰어넘어야만 다음 스테이지로 갈 수 있다.
                하지만 힘이 부족하면 당신의 손이 부러질 것이다.
                최강의 격파왕이 되어 역대 최고점을 갱신할 수 있을 것인가?"""
        );
        System.out.println();
        System.out.print("아무키나 입력하세요...");

        System.in.read();

        startGame();
    }

    private void startGame() {

    }
}
