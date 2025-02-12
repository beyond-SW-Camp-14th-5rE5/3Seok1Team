package com.seok.hotfist.service;


import com.seok.hotfist.aggregate.Member;
import com.seok.hotfist.aggregate.Member3;
import com.seok.hotfist.aggregate.Status;
import com.seok.hotfist.repository.MemberRepository;

import java.util.ArrayList;

// 비지니스 로직 처리 공간
public class MemberService {

    private final MemberRepository mr = new MemberRepository();

    public void registMember(Member3 member3) {

        //회원가입 시 입력받은 값 제외 번호,상태 입력
        int lastMemberNo = mr.selectLastMemberNo();
        member3.setMemNo(lastMemberNo + 1);

        member3.setMemStatus(Status.ACTIVE);

        // 모든 DML작업이 일어난 행의 갯수
        int result = mr.insertMember(member3);
        System.out.println("insert 성공실패 여부 : " + result);

    }
    // MemberService 클래스 안에 있는 MemberRepository는 서로 연결되어있다.
    // DB에 연결된 MemberRepository를 데려온 느낌

    }

