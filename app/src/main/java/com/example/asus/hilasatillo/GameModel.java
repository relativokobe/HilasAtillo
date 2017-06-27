package com.example.asus.hilasatillo;

/**
 * Created by asus on 09/06/2017.
 */

public class GameModel {
    String team1_player1;
    String team1_player2;
    String team1_player3;
    String team1_player4;
    String team1_player5;

    String team2_player1;
    String team2_player2;
    String team2_player3;
    String team2_player4;
    String team2_player5;

    String status;
    int winner;

    public GameModel(String team1_player1, String team1_player2, String team1_player3, String team1_player4, String team1_player5,
                     String team2_player1, String team2_player2, String team2_player3, String team2_player4, String team2_player5, String status, int winner) {
        this.team1_player1 = team1_player1;
        this.team1_player2 = team1_player2;
        this.team1_player3 = team1_player3;
        this.team1_player4 = team1_player4;
        this.team1_player5 = team1_player5;
        this.team2_player1 = team2_player1;
        this.team2_player2 = team2_player2;
        this.team2_player3 = team2_player3;
        this.team2_player4 = team2_player4;
        this.team2_player5 = team2_player5;
        this.status = status;
        this.winner = winner;
    }

    public String getTeam1_player1() {
        return team1_player1;
    }

    public void setTeam1_player1(String team1_player1) {
        this.team1_player1 = team1_player1;
    }

    public String getTeam1_player2() {
        return team1_player2;
    }

    public void setTeam1_player2(String team1_player2) {
        this.team1_player2 = team1_player2;
    }

    public String getTeam1_player3() {
        return team1_player3;
    }

    public void setTeam1_player3(String team1_player3) {
        this.team1_player3 = team1_player3;
    }

    public String getTeam1_player4() {
        return team1_player4;
    }

    public void setTeam1_player4(String team1_player4) {
        this.team1_player4 = team1_player4;
    }

    public String getTeam1_player5() {
        return team1_player5;
    }

    public void setTeam1_player5(String team1_player5) {
        this.team1_player5 = team1_player5;
    }

    public String getTeam2_player1() {
        return team2_player1;
    }

    public void setTeam2_player1(String team2_player1) {
        this.team2_player1 = team2_player1;
    }

    public String getTeam2_player2() {
        return team2_player2;
    }

    public void setTeam2_player2(String team2_player2) {
        this.team2_player2 = team2_player2;
    }

    public String getTeam2_player3() {
        return team2_player3;
    }

    public void setTeam2_player3(String team2_player3) {
        this.team2_player3 = team2_player3;
    }

    public String getTeam2_player4() {
        return team2_player4;
    }

    public void setTeam2_player4(String team2_player4) {
        this.team2_player4 = team2_player4;
    }

    public String getTeam2_player5() {
        return team2_player5;
    }

    public void setTeam2_player5(String team2_player5) {
        this.team2_player5 = team2_player5;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }
}
