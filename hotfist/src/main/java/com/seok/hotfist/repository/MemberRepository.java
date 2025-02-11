

import com.ohgiraffers.section04.testapp.aggregate.Member;

import java.io.*;
import java.util.ArrayList;

/* 설명. 데이터베이스 개념(Member 관련 파일)과 입출력(CRUD)을 위해 만들어지며
    성공데이터 또는 성공/실패 여부를 반환(java의 컬렉션 개념으로 데이터를 관리하기도 한다.) */
public class MemberRepository {

    /* 설명. 초기에 Member 파일이 없다면 파일을 만들어 더미데이터(dummy data)를 쌓는다. */
    private final ArrayList<Member> memberList = new ArrayList<>();
    private final File file =
            new File("src/main/java/com/seok/hotfist/db/test");

    /* 설명. 프로그램 구동시 MemberRepository 생성자가 호출되며 초기에 실행할 내용들 */
    public MemberRepository() {

        if (!file.exists()) {
            ArrayList<Member> defaultMembers = new ArrayList<>();
            defaultMembers.add(new Member(1, "김김김" ,"id01", "pass01"));

            defaultMembers.add(new Member(2, "박박박" ,"id02", "pass02"));

            defaultMembers.add(new Member(3, "이이이", "id03", "pass03"));

            defaultMembers.add(new Member(3, "최최최", "id04", "pass03"));

            saveMembers(defaultMembers);
        }

        loadMembers();
    }

    /* 설명. 파일로부터 회원 객체들을 읽어와서 memberList 컬렉션에 저장 */
    private void loadMembers() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(file)
                )

        )) {
            while (true) {
                memberList.add((Member) ois.readObject());
            }

        } catch (EOFException e) {
            System.out.println("회원 정보 입력 완료");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /* 설명. ArrayList<Member>를 받으면 파일로 출력하는 메소드(feat. 덮어씌우는 기능) */
    private void saveMembers(ArrayList<Member> inputMembers) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(file)
                    )
            );

            for (Member member : inputMembers) {
                oos.writeObject(member);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (oos != null) oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<Member> selectAllMembers() {
        return memberList;
    }

    public Member selectMemberBy(int memNo) {
        Member returnMember = null;

        for (Member member : memberList) {
            if (member.getMemNo() == memNo) {
                returnMember = member;
            }

        }

        return returnMember;
    }
}
