package com.rros.logmeinbasicdeck.controller;

import com.rros.logmeinbasicdeck.pojo.Game;
import com.rros.logmeinbasicdeck.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public Set<UUID> get() {
        return gameService.get().stream().map(Game::getUuid).collect(Collectors.toSet());
    }

    @PostMapping
    public UUID add() {
        Game game = new Game();
        boolean result = gameService.add(game);
        if (!result) {
            throw new IllegalStateException("Could not add game");
        }
        return game.getUuid();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID uuid) {
        boolean result = gameService.remove(uuid);
        if (!result) {
            throw new IllegalStateException("Could not remove game");
        }
    }

    @GetMapping("/{id}")
    public Game get(@PathVariable("id") UUID uuid) {
        return gameService.getGame(uuid);
    }

}
