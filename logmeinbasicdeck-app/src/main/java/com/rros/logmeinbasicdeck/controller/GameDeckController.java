package com.rros.logmeinbasicdeck.controller;

import com.rros.logmeinbasicdeck.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController("/games/{gameId}/decks")
public class GameDeckController {

    private final GameService gameService;

    @Autowired
    public GameDeckController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/{deckId}")
    public void add(@PathVariable("gameId") UUID gameId, @PathVariable("deckId") UUID deckId) {
        gameService.add(gameId, deckId);
    }
}
