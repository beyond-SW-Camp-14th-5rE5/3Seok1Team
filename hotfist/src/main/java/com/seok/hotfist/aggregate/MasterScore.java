package com.seok.hotfist.aggregate;

// 고수들의 송판 격파 점수
public enum MasterScore {
    STAGE_1("초보 격파왕 '강수지'", 10),
    STAGE_2("중급 격파왕 '고윤석'", 20),
    STAGE_3("숙련 격파왕 ‘곽우석'", 30),
    STAGE_4("고수 격파왕 ‘김성민'",40),
    STAGE_5("전설 격파왕 ‘불주먹 최혜민’", 50);

    private final String MASTERNAME;
    private final int POWER;

    MasterScore(String masterName, int power) {
        MASTERNAME = masterName;
        POWER = power;
    }

    public int getPOWER() {
        return POWER;
    }

    public String getMASTERNAME() {
        return MASTERNAME;
    }
}
