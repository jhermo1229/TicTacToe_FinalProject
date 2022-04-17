package com.example.finalproject;

import java.time.LocalDateTime;

public class Player {

    private Integer id;
    private String playerOneName;
    private String playerTwoName;
    private int playerOneWin;
    private int playerTwoWin;
    private String currentTimeStamp;

    public Player(Integer id, String playerOneName, String playerTwoName, int playerOneWin, int playerTwoWin,
                  String currentTimeStamp) {
        this.setId(id);
        this.setPlayerOneName(playerOneName);
        this.setPlayerTwoName(playerTwoName);
        this.setPlayerOneWin(playerOneWin);
        this.setPlayerTwoWin(playerTwoWin);
//        this.setCurrentTimeStamp(currentTimeStamp);
    }

    public Player() {

    }


    public String getPlayerOneName() {
        return playerOneName;
    }

    public void setPlayerOneName(String playerOneName) {
        this.playerOneName = playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public void setPlayerTwoName(String playerTwoName) {
        this.playerTwoName = playerTwoName;
    }

    public int getPlayerOneWin() {
        return playerOneWin;
    }

    public void setPlayerOneWin(int playerOneWin) {
        this.playerOneWin = playerOneWin;
    }

    public int getPlayerTwoWin() {
        return playerTwoWin;
    }

    public void setPlayerTwoWin(int playerTwoWin) {
        this.playerTwoWin = playerTwoWin;
    }


    public String getCurrentTimeStamp() {
        return currentTimeStamp;
    }

    public void setCurrentTimeStamp(String currentDate) {
        this.currentTimeStamp = currentDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
