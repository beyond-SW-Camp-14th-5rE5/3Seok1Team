package com.seok.hotfist.aggregate;

public class Member implements Serizable {
    //회원번호 닉네임 아이디 비밀번호
    private int memberNumber;           //회원번호
    private String memberName;          //닉네임
    private String id;                  //아이디
    private String pwd;                 //비밀번호

    public Member() {
    }

    public Member(int memberNumber, String memberName, String id, String pwd) {
        this.memberNumber = memberNumber;
        this.memberName = memberName;
        this.id = id;
        this.pwd = pwd;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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

    @java.lang.Override
    public java.lang.String toString() {
        return "Member{" +
                "memberNumber=" + memberNumber +
                ", memberName='" + memberName + '\'' +
                ", id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
