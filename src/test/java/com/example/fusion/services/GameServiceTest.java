package com.example.fusion.services;

import com.example.fusion.model.GameState;
import com.example.fusion.model.PlayerMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {
    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameService();
    }

    @Test
    void testInitialGameState() {
        GameState initialState = gameService.resetGame();
        assertNotNull(initialState);
        assertArrayEquals(new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, initialState.getBoard());
        assertEquals("Next turn: X", initialState.getStatus());
        assertFalse(initialState.isGameOver());
    }

    @Test
    void testMakeMove() {
        PlayerMove move = new PlayerMove();
        move.setIndex(0);
        GameState gameState = gameService.processMove(move);
        assertEquals('X', gameState.getBoard()[0]);
        assertEquals("Next turn: O", gameState.getStatus());
        assertFalse(gameState.isGameOver());
    }

    @Test
    void testWinCondition() {
        gameService.processMove(new PlayerMove(0)); // X
        gameService.processMove(new PlayerMove(3)); // O
        gameService.processMove(new PlayerMove(1)); // X
        gameService.processMove(new PlayerMove(4)); // O
        GameState gameState = gameService.processMove(new PlayerMove(2)); // X wins
        assertTrue(gameState.isGameOver());
        assertEquals("X wins!", gameState.getStatus());
    }

    @Test
    void testDrawCondition() {
        gameService.processMove(new PlayerMove(0)); // X
        gameService.processMove(new PlayerMove(1)); // O
        gameService.processMove(new PlayerMove(2)); // X
        gameService.processMove(new PlayerMove(4)); // O
        gameService.processMove(new PlayerMove(3)); // X
        gameService.processMove(new PlayerMove(5)); // O
        gameService.processMove(new PlayerMove(7)); // X
        gameService.processMove(new PlayerMove(6)); // O
        GameState gameState = gameService.processMove(new PlayerMove(8)); // X
        assertTrue(gameState.isGameOver());
        assertEquals("Draw!", gameState.getStatus());
    }

    @Test
    void testResetGame() {
        gameService.processMove(new PlayerMove(0)); // X
        gameService.processMove(new PlayerMove(1)); // O
        gameService.resetGame();
        GameState gameState = gameService.resetGame();
        assertArrayEquals(new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, gameState.getBoard());
        assertEquals("Next turn: X", gameState.getStatus());
        assertFalse(gameState.isGameOver());
    }


}