package com.seok.hotfist.service;

import com.seok.hotfist.aggregate.Member;
import com.seok.hotfist.aggregate.Status;
import com.seok.hotfist.repository.MemberRepository;

import java.util.ArrayList;

public class MemberService {
    private static final MemberRepository mr = new MemberRepository(); // static으로 변경
    private static String loggedInUserId = null;
    private static int loggedInUserNo = -1;

    public void registMember(Member member) {
        int lastMemberNo = mr.selectLastMemberNo();
        member.setMemNo(lastMemberNo + 1);
        member.setMemStatus(Status.ACTIVE);

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

    public int loginMember(String id, String pwd) {
        ArrayList<Member> members = mr.getMemberList();
        for (Member member : members) {
            if (member.getMemId().equals(id) &&
                    member.getMemPwd().equals(pwd) &&
                    member.getMemStatus() == Status.ACTIVE) {

                loggedInUserId = id;
                loggedInUserNo = member.getMemNo();
                return loggedInUserNo;
            }
        }
        return -1;
    }


    public static String getLoggedInUserId() {
        return loggedInUserId;
    }

    public static int getLoggedInUserNo() {
        return loggedInUserNo;
    }

    public static String getLoggedInUserNick() {
        for (Member member : mr.getMemberList()) {
            if (member.getMemNo() == loggedInUserNo) {
                return member.getMemNick();
            }
        }
        return null;
    }

    public Member getLoggedInMember() {
        for (Member member : mr.getMemberList()) {
            if (member.getMemNo() == loggedInUserNo) {
                return member;
            }
        }
        return null;
    }

    public void findAllMembers() {
        ArrayList<Member> findMembers = mr.selectAllMembers();

        System.out.println("Service에서 조회 확인: ");
        for (Member member : findMembers) {
            System.out.println(member);
        }
    }
}