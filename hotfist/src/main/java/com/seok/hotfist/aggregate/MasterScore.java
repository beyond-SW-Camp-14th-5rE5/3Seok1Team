package com.seok.hotfist.aggregate;

// 고수들의 송판 격파 점수
public enum MasterScore {
    FIRST(10),
    SECOND(20),
    THIRD(30),
    FOURTH(40),
    FIFTH(50);

    private final int SCORE;

    MasterScore(int score) {
        SCORE = score;
    }

    public int getSCORE() {
        return SCORE;
    }
}
