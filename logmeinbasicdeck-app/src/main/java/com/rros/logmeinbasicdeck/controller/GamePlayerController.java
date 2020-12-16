package com.rros.logmeinbasicdeck.controller;

import com.rros.logmeinbasicdeck.model.Card;
import com.rros.logmeinbasicdeck.model.Game;
import com.rros.logmeinbasicdeck.model.Player;
import com.rros.logmeinbasicdeck.service.GamePlayerService;
import com.rros.logmeinbasicdeck.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Operation(summary = "Add a player to a game")
    @PostMapping
    public UUID create(@PathVariable("gameId") UUID gameId) {
        Game game = gameService.get(gameId);
        return gamePlayerService.create(game);
    }

    @Operation(summary = "Delete a player from a game")
    @DeleteMapping("/{playerId}")
    public void delete(@PathVariable("gameId") UUID gameId, @PathVariable("playerId") UUID playerId) {
        Game game = gameService.get(gameId);
        gamePlayerService.delete(game, playerId);
    }

    @Operation(summary = "Get the player and their card values ordered by descending value")
    @GetMapping
    public List<Map.Entry<UUID, Integer>> get(@PathVariable("gameId") UUID gameId) {
        Game game = gameService.get(gameId);
        List<Player> players = gamePlayerService.get(game);
        Map<UUID, Integer> collect = players.stream().collect(
                Collectors.toMap(
                        Player::uuid,
                        // TODO 2020-12-15 rosr move to player (getCardsIntValue())
                        player -> player.getCards().stream().map(card -> card.cardValue().getIntValue()).reduce(0, Integer::sum)));
        // TODO 2020-12-15 rosr move to service? (where should aggregation be handled?)
        return collect.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());
    }

    @Operation(summary = "Deal cards to a player")
    @PostMapping("/{playerId}/dealCards")
    public void dealCards(@PathVariable("gameId") UUID gameId, @PathVariable("playerId") UUID playerId, @RequestBody int nbCards) {
        Game game = gameService.get(gameId);
        gamePlayerService.dealCards(game, playerId, nbCards);
    }

    @Operation(summary = "Get the cards of a player")
    @GetMapping("/{playerId}/cards")
    public List<Card> getCards(@PathVariable("gameId") UUID gameId, @PathVariable("playerId") UUID playerId) {
        Game game = gameService.get(gameId);
        return gamePlayerService.getCards(game, playerId);
    }
}
