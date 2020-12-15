package com.rros.logmeinbasicdeck.controller;

import com.rros.logmeinbasicdeck.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

// TODO 2020-12-14 rosr exceptionController
@RestController
@RequestMapping("games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public Set<UUID> get() {
        return gameService.get();
    }

    @PostMapping
    public UUID add() {
        return gameService.add();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID uuid) {
        gameService.delete(uuid);
    }
}
