package com.seok.hotfist.run;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Hot Fist! 당신의 주먹은 몇 점?");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 나가기");
            System.out.print("메뉴를 선택해주세요: ");
            int menuChoice = sc.nextInt();

            switch (menuChoice) {
                case 1: break;
                case 2: break;
                case 3: break;
                case 4:
                    System.out.println("당신의 주먹은 0점이군요. 게임을 종료합니다."); return;
                default:
                    System.out.println("번호를 잘 누르지 못하는 사람은 주먹이 약할 확률이 높다 \n - 무슨무슨 서양 격언 -");
            }
        }
    }
}
