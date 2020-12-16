package com.rros.logmeinbasicdeck.controller;

import com.rros.logmeinbasicdeck.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Operation(summary = "List all games")
    @GetMapping
    public Set<UUID> get() {
        return gameService.get();
    }

    @Operation(summary = "Create a game")
    @PostMapping
    public UUID create() {
        return gameService.create();
    }

    @Operation(summary = "Delete a game")
    @DeleteMapping("/{gameId}")
    public void delete(@PathVariable("gameId") UUID gameId) {
        gameService.delete(gameId);
    }
}
