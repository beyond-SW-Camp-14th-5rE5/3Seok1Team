package com.seok.hotfist.run;

import com.seok.hotfist.aggregate.Member;
import com.seok.hotfist.service.GameService;
import com.seok.hotfist.service.MemberService;

import java.util.Scanner;

public class Application3 {

    private static final MemberService ms = new MemberService();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("====회원 서비스====");
            System.out.println("1. 회원 가입");
            System.out.println("2. 회원 로그인");
            System.out.println("3. 테스트용 회원 조회");
            System.out.println("9. 서비스 종료");
            System.out.print("메뉴를 선택해주세요: ");
            int input = sc.nextInt();

            switch (input) {
                case 1:
                    ms.registMember(signUp());
                    break;
                case 2:
                    int loginResult = login();
                    if (loginResult != -1) {
                        System.out.println("로그인 성공!");
                        showLobby();
                    } else {
                        System.out.println("로그인 실패. 아이디 또는 비밀번호를 확인해주세요.");
                    }
                    break;
                case 3:                                     // 회원 조회
                    ms.findAllMembers();
                    break;
                case 9:
                    System.out.println("회원 서비스를 종료합니다.");
                    return;
                default:
                    System.out.println("번호를 다시 입력해주세요");
            }
        }
    }

    private static int login() {
        Scanner sc = new Scanner(System.in);

        System.out.print("아이디를 입력하세요: ");
        String id = sc.nextLine().trim();

        System.out.print("비밀번호를 입력하세요: ");
        String pwd = sc.nextLine().trim();

        if (id.isEmpty() || pwd.isEmpty()) {
            System.out.println("아이디와 비밀번호는 필수 입력값입니다.");
            return -1;
        }

        return ms.loginMember(id, pwd);
    }

    private static void showLobby() {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("\n==== 반갑습니다 " + MemberService.getLoggedInUserNick() + "님, 무엇을 할까요? ====");
            System.out.println("1. 게임 시작");
            System.out.println("2. 게임 랭크");
            System.out.println("3. 나의 게임 기록");
            System.out.println("4. 로그아웃");
            System.out.println("5. 회원 탈퇴");
            System.out.print("메뉴를 선택해주세요: ");

            int choice = sc.nextInt();

            switch(choice) {
                case 1:
                    System.out.println("게임을 시작합니다...");

                    break;
                case 2:
                    System.out.println("랭킹을 확인합니다...");

                    break;
                case 3:
                    Member currentMember = ms.getLoggedInMember();
                    GameService gs = new GameService();

                    int highScore = gs.getHighScore(currentMember.getMemNo());

                    System.out.println("\n최근 게임 기록" + "               HighScore : " + highScore);
                    System.out.println("+--------+----------+-------------------+");
                    System.out.println("| 게임번호 |   점수    |        시간        |");
                    System.out.println("+--------+----------+-------------------+");

                    gs.findLastMyGameLogs(currentMember.getMemNo(), 5);

                    System.out.println("+--------+----------+-------------------+");

                    while(true) {
                        System.out.println("\n1. 게임하기");
                        System.out.println("2. 이전 메뉴로");
                        System.out.print("선택: ");

                        int menuChoice = sc.nextInt();
                        if(menuChoice == 1) {
                            System.out.println("게임을 시작합니다...");
                            break;
                        } else if(menuChoice == 2) {
                            break;
                        } else {
                            System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("로그아웃 합니다.");
                    return;
                case 5:
                    System.out.print("정말 탈퇴하시겠습니까? (Y/N): ");
                    sc.nextLine();
                    String confirm = sc.nextLine().toUpperCase();

                    if(confirm.equals("Y")) {
                        ms.removeMember(MemberService.getLoggedInUserNo());
                        System.out.println("회원 탈퇴가 완료되었습니다.");
                        return;
                    }
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }

    private static Member signUp() {
        Member member = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("아이디를 입력하세요: ");
        String id = sc.nextLine();

        System.out.print("패스워드를 입력하세요: ");
        String pwd = sc.nextLine();

        System.out.print("사용하실 이름을 입력하세요: ");
        String name = sc.nextLine();

        member = new Member(id,pwd,name);

        return member;
    }
}