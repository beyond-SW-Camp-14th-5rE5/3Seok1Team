package com.seok.hotfist.service;

import com.seok.hotfist.aggregate.GameLog;
import com.seok.hotfist.aggregate.MasterScore;
import com.seok.hotfist.repository.GameRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* 게임 로직 처리 */
public class GameService {

    private final GameRepository gr = new GameRepository();

    static int totalScore = 0;
    static int aitotlaScore = 0;
    static Random random = new Random();

    public GameService() {
    }

    private void gameIntro() throws IOException {
        System.out.println("""
                🔥 전설의 격파 대회가 시작됐다! 🔥
                각 스테이지마다 강력한 고수가 송판을 지키고 있다.
                그들의 격파 점수를 뛰어넘어야만 다음 스테이지로 갈 수 있다.
                하지만 힘이 부족하면 당신의 손이 부러질 것이다.
                최강의 격파왕이 되어 역대 최고점을 갱신할 수 있을 것인가?"""
        );
        System.out.println();
        System.out.print("⭐ 아무키나 입력하세요... ⭐");

        System.in.read();

        gameStart();
    }

    private void gameStart() throws IOException {
        for (MasterScore stage : MasterScore.values()) {
            int playerPower = random.nextInt(101);  // 0 ~ 100
            int masterPower = stage.getPOWER();

            System.out.println("\n===== 스테이지 " + (stage.ordinal() + 1) + " =====");
            System.out.println("💪 도전 상대: " + stage.getMASTERNAME());
            System.out.println("🔨 송판 격파 시도 중... (힘: " + playerPower + ")");
            System.out.println("📌 고수의 점수: " + masterPower);

            if (playerPower < masterPower) {
                System.out.println("❌ 힘이 부족합니다! 손이 부러졌습니다...🚑");
                gameEnd(false);
                return;
            } else {
                int getScore = playerPower - masterPower;
                totalScore += getScore;
                System.out.println("🌫️ 성공! 송판이 부서졌습니다! (+ " + getScore + "점)");
            }
        }
        gameEnd(true);
    }

    private void gameEnd(boolean isCompleted) throws IOException {
        if (isCompleted) {
            System.out.println("🎉 모든 송판을 부쉈습니다!");
        } else {
            System.out.println("😭 게임 오버!");
        }
        System.out.println(" 최종 점수: " + totalScore);

        // 랭킹에 등록
        if (totalScore > 0) {
            System.out.println("💬 \"좀 치는데 ㅋ\"");

            // 등록 함수 호출
        } else {
            System.out.println("💬 \"손이나 낫고 와라 ㅋ\"");
        }

        // 점수 로그에 저장
        gr.saveGameScore(totalScore);

        System.out.print("⭐ 아무키나 입력하세요... ⭐");
        System.in.read();
        System.out.println("로비로 돌아갑니다...");
    }

    // 모든 게임 기록 확인
    public void findAllGameLogs() {
        ArrayList<GameLog> findLogs = gr.selectAllGameLogs();

        System.out.println("테스트용 모든 게임 기록 확인");
        for (GameLog gameLog : findLogs) {
            System.out.println(gameLog);
        }
    }

    // 로그인한 회원의 모든 게임 기록 확인
    public void findMyGameLogs() {
        ArrayList<GameLog> findMyLogs = gr.selectMyGameLogs();

        for(GameLog gameLog : findMyLogs){
            System.out.println(gameLog);
        }
    }

    // 기록 n개만 확인
    public void findLastMyGameLogs(int memNo, int count) {
       List<GameLog> lastGameLogs =  gr.getLastMyGameLogs(count);

       if(!lastGameLogs.isEmpty()) {
           for(GameLog gameLog : lastGameLogs) {
                System.out.println(gameLog.getGameNo() + ": " + gameLog.getScore() + "    " + gameLog.getDateTime());
           }
       } else {
           System.out.println(memNo + " 회원님의 게임 기록은 없습니다! 빨리 게임하세요!");
       }
    }
}