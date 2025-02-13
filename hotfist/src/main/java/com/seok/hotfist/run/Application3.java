package com.seok.hotfist.run;

import com.seok.hotfist.aggregate.Member3;
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
            System.out.println("3. 회원 탈퇴");
            System.out.println("9. 서비스 종료");
            System.out.print("메뉴를 선택해주세요 :");
            int input = sc.nextInt();

            switch (input) {
                case 1:  ms.registMember(signUp());   //회원가입
                    break;
                case 2:     //회원 로그인
                    break;
                case 3:     //회원 탈퇴
                    break;
                case 9:
                    System.out.println("회원 서비스를 종료합니다.");
                    return;

                default:
                    System.out.println("번호를 다시 입력해주세요");
            }
        }
    }

    private static Member3 signUp() {
        Member3 member3 = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("아이디를 입력하세요: ");
        String id = sc.nextLine();

        System.out.print("패스워드를 입력하세요: ");
        String pwd = sc.nextLine();

        System.out.println("사용하실 이름을 입력하세요: ");
        String name = sc.nextLine();

        member3 = new Member3(id,pwd,name);

        return member3;
    }
}
