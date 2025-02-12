package com.seok.hotfist.aggregate;

import java.io.Serializable;

public class GameLog implements Serializable {
    private int gameNo;
    private int memNo;
    private int score;
    private java.time.LocalDateTime dateTime;
    private Status gameLogStatus;           // 활성화여부

    public GameLog() {
    }

    public GameLog(int gameNo, int memNo, int score) {
        this.gameNo = gameNo;
        this.memNo = memNo;
        this.score = score;
    }
    public GameLog(int gameNo, int memNo, int score, java.time.LocalDateTime dateTime, Status gameLogStatus) {
        this.gameNo = gameNo;
        this.memNo = memNo;
        this.score = score;
        this.dateTime = dateTime;
        this.gameLogStatus = gameLogStatus;
    }

    public int getMemNo() {
        return memNo;
    }

    public void setMemNo(int memNo) {
        this.memNo = memNo;
    }

    public int getGameNo() {
        return gameNo;
    }

    public void setGameNo(int gameNo) {
        this.gameNo = gameNo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public java.time.LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(java.time.LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Status getGameLogStatus() {
        return gameLogStatus;
    }

    public void setGameLogStatus(Status gameLogStatus) {
        this.gameLogStatus = gameLogStatus;
    }

    @Override
    public String toString() {
        return "GameLog{" +
                "gameNo=" + gameNo +
                ", memNo=" + memNo +
                ", score=" + score +
                ", dateTime=" + dateTime +
                ", gameLogStatus=" + gameLogStatus +
                '}';
    }
}
