package com.example.fusion.controller;

import com.example.fusion.model.GameState;
import com.example.fusion.model.PlayerMove;
import com.example.fusion.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @MessageMapping("/move")
    @SendTo("/topic/updates")
    public GameState makeMove(PlayerMove move) throws Exception {
        return gameService.processMove(move);
    }

    @MessageMapping("/reset")
    @SendTo("/topic/updates")
    public GameState resetGame() throws Exception {
        return gameService.resetGame();
    }
}