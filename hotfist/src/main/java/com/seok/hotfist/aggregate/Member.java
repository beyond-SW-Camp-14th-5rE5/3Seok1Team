package com.seok.hotfist.aggregate;

import java.io.Serializable;

public class Member implements Serializable {
    private int memberNo;
    private String id;
    private String pwd;
    private String nickname;
    private int score;

    public Member() {
    }

    public Member(int memberNo, String id, String pwd, String nickname, int score) {
        this.memberNo = memberNo;
        this.id = id;
        this.pwd = pwd;
        this.nickname = nickname;
        this.score = score;         //
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberNo=" + memberNo +
                ", id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nickname='" + nickname + '\'' +
                ", score=" + score +
                '}';
    }
}
