package com.seok.hotfist.service;


import com.seok.hotfist.aggregate.Member;
import com.seok.hotfist.aggregate.Status;
import com.seok.hotfist.repository.MemberRepository;

// 비지니스 로직 처리 공간
public class MemberService {

    private final MemberRepository mr = new MemberRepository();

    public void registMember(Member member) {

        //회원가입 시 입력받은 값 제외 번호,상태 입력
        int lastMemberNo = mr.selectLastMemberNo();
        member.setMemNo(lastMemberNo + 1);

        member.setMemStatus(Status.ACTIVE);

        // 모든 DML작업이 일어난 행의 갯수
        int result = mr.insertMember(member);
        System.out.println("insert 성공실패 여부 : " + result);

    }

    public void removeMember(int removeMemNo) {
        int result = mr.deleteMember(removeMemNo);
        if (result == 1) {
            System.out.println(removeMemNo + "번 회원 탈퇴를 성공하였습니다.");
        } else {
            System.out.println("회원 탈퇴를 실패했습니다.");
        }
    }
    // MemberService 클래스 안에 있는 MemberRepository는 서로 연결되어있다.
    // DB에 연결된 MemberRepository를 데려온 느낌

    }

