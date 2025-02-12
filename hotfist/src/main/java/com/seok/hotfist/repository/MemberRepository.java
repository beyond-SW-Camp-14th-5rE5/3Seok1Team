package com.seok.hotfist.repository;

import com.seok.hotfist.aggregate.Member;

import java.io.*;
import java.util.ArrayList;

public class MemberRepository {

    private final ArrayList<Member> memberList = new ArrayList<>();
    private final File file =
            new File("src/main/java/com/seok/hotfist/db/memberDB.dat");

    public MemberRepository() {

        if (!file.exists()) {
            ArrayList<Member> defaultMembers = new ArrayList<>();
            defaultMembers.add(new Member(1, "user01", "pass01", "성남초일짱혜민", 300));
            defaultMembers.add(new Member(2, "user02", "pass02", "동작구샹크스성민", 150));
            defaultMembers.add(new Member(3, "user03", "pass03", "디아블잠브윤석", 120));

            saveMembers(defaultMembers);
        }
    loadMembers();
    }

    private void loadMembers() {
        try (
                ObjectInputStream ois = new ObjectInputStream
                        (new BufferedInputStream
                                (new FileInputStream(file)
                                )
                        )) {
            while (true) {
                memberList.add((Member) ois.readObject());
            }
        } catch (EOFException e) {
            System.out.println("회원 정보 로드 완료");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch(ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

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
                if(oos != null) oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
