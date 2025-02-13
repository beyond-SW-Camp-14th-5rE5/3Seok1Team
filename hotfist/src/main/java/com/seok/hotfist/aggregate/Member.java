package com.seok.hotfist.aggregate;


import java.io.Serializable;

public class Member implements Serializable {
    private int memNo;       //회원 번호
    private String memId;      //회원 아이디
    private String memPwd;        //회원 비밀번호
    private String memNick;       //회원 닉네임
    private Status memStatus;   //회원 탈퇴여부

    public Member() {
    }

    public Member(int memNo, String memId, String memPwd, String memNick, Status memStatus) {
        this.memNo = memNo;
        this.memId = memId;
        this.memPwd = memPwd;
        this.memNick = memNick;
        this.memStatus = memStatus;
    }

    public Member(String id, String pwd, String name) {
        this.memId = id;
        this.memPwd = pwd;
        this.memNick = name;
    }

    public int getMemNo() {
        return memNo;
    }

    public void setMemNo(int memNo) {
        this.memNo = memNo;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getMemPwd() {
        return memPwd;
    }

    public void setMemPwd(String memPwd) {
        this.memPwd = memPwd;
    }

    public String getMemNick() {
        return memNick;
    }

    public void setMemNick(String memNick) {
        this.memNick = memNick;
    }

    public Status getMemStatus() {
        return memStatus;
    }

    public void setMemStatus(Status memStatus) {
        this.memStatus = memStatus;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memNo=" + memNo +
                ", memId='" + memId + '\'' +
                ", memPwd='" + memPwd + '\'' +
                ", memNick='" + memNick + '\'' +
                ", memStatus=" + memStatus +
                '}';
    }
}
