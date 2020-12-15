package com.rros.logmeinbasicdeck.controller;

import com.rros.logmeinbasicdeck.model.Game;
import com.rros.logmeinbasicdeck.service.GamePlayerService;
import com.rros.logmeinbasicdeck.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/games/{gameId}/players")
public class GamePlayerController {

    private final GameService gameService;
    private final GamePlayerService gamePlayerService;

    @Autowired
    public GamePlayerController(GameService gameService, GamePlayerService gamePlayerService) {
        this.gameService = gameService;
        this.gamePlayerService = gamePlayerService;
    }

    @PostMapping
    public UUID create(@PathVariable("gameId") UUID gameId) {
        Game game = gameService.get(gameId);
        return gamePlayerService.create(game);
    }

    @DeleteMapping("/{playerId}")
    public void delete(@PathVariable("gameId") UUID gameId, @PathVariable("playerId") UUID playerId) {
        Game game = gameService.get(gameId);
        gamePlayerService.delete(game, playerId);
    }

    @PostMapping("/{playerId}/dealCards")
    public void dealCards(@PathVariable("gameId") UUID gameId, @PathVariable("playerId") UUID playerId, @RequestBody int nbCards) {
        Game game = gameService.get(gameId);
        gamePlayerService.dealCards(game, playerId, nbCards);
    }
}
