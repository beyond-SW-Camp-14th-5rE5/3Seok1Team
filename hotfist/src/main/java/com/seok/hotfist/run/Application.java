package com.seok.hotfist.run;

import com.seok.hotfist.service.MemberService;
import com.seok.hotfist.service.RankService;

import java.util.Scanner;

public class Application {

    private static final MemberService ms = new MemberService();
    private static final RankService rankService = new RankService();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Hot Fist! 당신의 주먹은 몇 점?");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 랭크 확인");
            System.out.println("4. 나가기");
            System.out.println("5. 테스트용 랭크 등록");
            System.out.print("메뉴를 선택해주세요: ");
            int menuChoice = sc.nextInt();
            sc.nextLine();

            switch (menuChoice) {
                case 1:
                    System.out.println("로그인 기능 구현 중"); break;
                case 2:
                    System.out.println("회원가입 기능 구현 중"); break;
                case 3:
                    System.out.println("불주먹 랭킹 조회");
                    rankService.displayRanks();
                    break;
                case 4:
                    System.out.println("당신의 주먹은 0점이군요. 게임을 종료합니다."); return;
                case 5:
                    System.out.println("테스트용 랭크 등록");
                    rankService.registerRank(0, "test", 270);
                    break;
                default:
                    System.out.println("번호를 잘 누르지 못하는 사람은 주먹이 약할 확률이 높다 \n - 무슨무슨 서양 격언 -");
            }
        }
    }
}
