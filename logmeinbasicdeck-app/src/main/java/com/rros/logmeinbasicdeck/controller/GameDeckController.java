package com.rros.logmeinbasicdeck.controller;

import com.rros.logmeinbasicdeck.model.Game;
import com.rros.logmeinbasicdeck.service.GameDeckService;
import com.rros.logmeinbasicdeck.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController("/games/{gameId}/decks")
public class GameDeckController {

    private final GameService gameService;
    private final GameDeckService gameDeckService;

    @Autowired
    public GameDeckController(GameService gameService, GameDeckService gameDeckService) {
        this.gameService = gameService;
        this.gameDeckService = gameDeckService;
    }

    @PostMapping("/{deckId}")
    public void add(@PathVariable("gameId") UUID gameId, @PathVariable("deckId") UUID deckId) {
        Game game = gameService.get(gameId);
        gameDeckService.add(game, deckId);
    }
}
