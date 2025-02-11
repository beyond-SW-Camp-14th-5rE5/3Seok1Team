package com.seok.hotfist.run;

public class Application {

    private static final MemberService ms = new MemberService();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("===== 회원 관리 프로그램 =====");
            System.out.println("1. 회원 가입");       // find . select
            System.out.println("2. 로그인");
            System.out.println("3. 회원탈퇴"); // 상태 변환
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴를 선택해주세요: ");
            int input = sc.nextInt();

            switch (input) {
                case 1:  ms.//회원가입();
                    break;
                case 2: ms//로그인();
                    break;
                case 3:
                    break;


                case 9:
                    System.out.println("회원 관리 프로그램을 종료합니다.");
                    return;

                default:
                    System.out.println("번호를 확인해주세요");

            }


        }

    }

    }
}
