package com.rros.logmeinbasicdeck.controller;

import com.rros.logmeinbasicdeck.dto.SuitCardValue;
import com.rros.logmeinbasicdeck.model.Card;
import com.rros.logmeinbasicdeck.model.CardValue;
import com.rros.logmeinbasicdeck.model.Game;
import com.rros.logmeinbasicdeck.model.Suit;
import com.rros.logmeinbasicdeck.service.GameCardsService;
import com.rros.logmeinbasicdeck.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.SortedMap;
import java.util.UUID;

/**
 * Controller for the game deck (as opposed to associating a deck to a game).
 *
 * <p>In this context the 'game deck' is referred to as the (non-dealt) cards of a game.
 */
@RestController
@RequestMapping("/games/{gameId}")
public class GameCardsController {

    private final GameService gameService;
    private final GameCardsService gameCardsService;

    @Autowired
    public GameCardsController(GameService gameService, GameCardsService gameCardsService) {
        this.gameService = gameService;
        this.gameCardsService = gameCardsService;
    }

    @Operation(summary = "Get the count of non-dealt cards per suit")
    @GetMapping("/cards-suits")
    public SortedMap<Suit<?>, Long> getNumberOfCardsBySuit(@PathVariable UUID gameId) {
        Game game = gameService.get(gameId);
        return gameCardsService.getNumberOfCardsBySuit(game);
    }

    @Operation(summary = "Get the count of non-dealt cards per suit and per value ordered by suit and descending value")
    @GetMapping("/cards-suits-values")
    public <U extends Suit<U>, V extends CardValue<V>> SortedMap<SuitCardValue<U, V>, Long> getNumberOfCardsBySuitAndByValue(@PathVariable UUID gameId) {
        Game game = gameService.get(gameId);
        return gameCardsService.getNumberOfCardsBySuitAndByValue(game);
    }

    @Operation(summary = "Get all non-dealt cards")
    @GetMapping("/cards")
    public List<Card> get(@PathVariable UUID gameId) {
        Game game = gameService.get(gameId);
        return gameCardsService.get(game);
    }

    @Operation(summary = "Shuffle non-dealt cards")
    @PostMapping("/shuffles")
    public void shuffle(@PathVariable("gameId") UUID gameId) {
        Game game = gameService.get(gameId);
        gameCardsService.shuffle(game);
    }
}
