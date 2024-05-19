package com.example.fusion.model;

public class GameState {
    private char[] board;
    private String status;
    private boolean gameOver;


    public GameState(char[] board, String status, boolean gameOver) {
        this.board = board;
        this.status = status;
        this.gameOver = gameOver;
    }


    public char[] getBoard() {
        return board;
    }

    public String getStatus() {
        return status;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
