package com.seok.hotfist.repository;

import com.seok.hotfist.aggregate.Member;
import com.seok.hotfist.aggregate.Member3;
import com.seok.hotfist.aggregate.Status;
import com.seok.hotfist.stream.MyObjectOutput;

import java.io.*;
import java.util.ArrayList;

public class MemberRepository {
// 입출력 스트림 (통로)
// 역할 : DB와 입출력(CRUD) , 성공데이터 또는 실패여부를 Service에게 반환한다. 판단은 Service가 한다
    // 초기 Member에 더미데이값 넣기.
    private final ArrayList<Member3> memberList = new ArrayList<>();
    private final File file = new File(
            "src/main/java/com/seok/hotfist/db/memberDB.dat");

    // 프로그램 구동시 MemberRepository가 생성되며 실행된다.
    public MemberRepository() {

        if (!file.exists()) {           // 파일이 없다면 아래 로직이 진행됨
            ArrayList<Member3> defaultMemberList = new ArrayList<>();
            //파일이 존재하지 않을 때 한번 생성
            defaultMemberList.add(new Member3(1,"id01","pwd01","강철권고윤석",Status.ACTIVE ));
            defaultMemberList.add(new Member3(2,"id02","pwd02","폭풍신곽우석",Status.ACTIVE));
            defaultMemberList.add(new Member3(3,"id03","pwd03","맹수혼최혜민",Status.ACTIVE));
            defaultMemberList.add(new Member3(4,"id04","pwd04","전광축강수지",Status.ACTIVE));
            defaultMemberList.add(new Member3(5,"id05","pwd05","암흑룡김석희",Status.ACTIVE));
            defaultMemberList.add(new Member3(6,"id06","pwd06","섬광참김성민",Status.ACTIVE));

            saveMembers(defaultMemberList);
        }
         loadMembers();


    }

    private void loadMembers() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(file)
                )))
        {
            while (true){
                memberList.add((Member3)ois.readObject());
            }

        } catch(EOFException e) {
            System.out.println("회원 정보 읽어오기 완료");
        }   catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //ArrayList<Member3>를 받으면 배열로 출력하는 메소드 (덮어씌우는 기능)
    private void saveMembers(ArrayList<Member3> inputMemberList) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(file)

                    )
            );
            for (Member3 member3 : inputMemberList) {
                oos.writeObject(member3);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(oos != null) oos.close();     // 통로가 열려있다면 (null이 아니면) 닫는다
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public int selectLastMemberNo() {
        Member3 lastMember3 = memberList.get(memberList.size() -1);
        return lastMember3.getMemNo();
    }

    public int insertMember(Member3 member3) {
        //헤더가 추가되지 않는 ObjectOutputStream 클래스 정의(MyOutputStream
        MyObjectOutput moo = null;
        int result = 0;

        try {
            moo = new MyObjectOutput(
                    new BufferedOutputStream(
                            new FileOutputStream(file,true)
                    )
            );
            //파일로 신규회원 추가하기
            moo.writeObject(member3);

            //컬렉션에도 신규회원 추가하기
            memberList.add(member3);

            result = 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(moo!=null) moo.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }
}






