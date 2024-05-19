package com.example.fusion.services;

import com.example.fusion.model.GameState;
import com.example.fusion.model.PlayerMove;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class GameService {

    private static final Set<int[]> WIN_PATTERNS;

    static {
        WIN_PATTERNS = new HashSet<>(Arrays.asList(
                new int[]{0, 1, 2}, new int[]{3, 4, 5}, new int[]{6, 7, 8},
                new int[]{0, 3, 6}, new int[]{1, 4, 7}, new int[]{2, 5, 8},
                new int[]{0, 4, 8}, new int[]{2, 4, 6}
        ));
    }

    private final char[] board = new char[9];
    private char currentPlayer = 'X';

    public GameService() {
        Arrays.fill(board, ' ');
    }

    public GameState processMove(PlayerMove move) {
        if (board[move.getIndex()] == ' ') {
            board[move.getIndex()] = currentPlayer;
            if (checkWin()) {
                return new GameState(board, currentPlayer + " wins!", true);
            }
            if (isDraw()) {
                return new GameState(board, "Draw!", true);
            }
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
        return new GameState(board, "Next turn: " + currentPlayer, false);
    }

    public GameState resetGame() {
        Arrays.fill(board, ' ');
        currentPlayer = 'X';
        return new GameState(board, "Next turn: X", false);
    }


    private boolean checkWin() {
        for (int[] pattern : WIN_PATTERNS) {
            if (board[pattern[0]] != ' ' && board[pattern[0]] == board[pattern[1]] && board[pattern[1]] == board[pattern[2]]) {
                return true;
            }
        }
        return false;
    }

    private boolean isDraw() {
        for (char cell : board) {
            if (cell == ' ') {
                return false;
            }
        }
        return true;
    }
}
