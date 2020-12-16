package com.rros.logmeinbasicdeck.controller;

import com.rros.logmeinbasicdeck.dto.ShuffleStatus;
import com.rros.logmeinbasicdeck.dto.SuitCardValue;
import com.rros.logmeinbasicdeck.model.Card;
import com.rros.logmeinbasicdeck.model.CardValue;
import com.rros.logmeinbasicdeck.model.Game;
import com.rros.logmeinbasicdeck.model.Suit;
import com.rros.logmeinbasicdeck.service.GameCardsService;
import com.rros.logmeinbasicdeck.service.GameService;
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

    @GetMapping("/cards")
    public List<Card> get(@PathVariable UUID gameId) {
        Game game = gameService.get(gameId);
        return gameCardsService.get(game);
    }

    @GetMapping("/cards-suits")
    public SortedMap<Suit<?>, Long> getNumberOfCardsBySuit(@PathVariable UUID gameId) {
        Game game = gameService.get(gameId);
        return gameCardsService.getNumberOfCardsBySuit(game);
    }

    @GetMapping("/cards-suits-values")
    public <U extends Suit<U>, V extends CardValue<V>> SortedMap<SuitCardValue<U, V>, Long> getNumberOfCardsBySuitAndByValue(@PathVariable UUID gameId) {
        Game game = gameService.get(gameId);
        return gameCardsService.getNumberOfCardsBySuitAndByValue(game);
    }

    @PostMapping("/shuffles")
    public UUID shuffle(@PathVariable("gameId") UUID gameId) {
        Game game = gameService.get(gameId);
        return gameCardsService.shuffle(game);
    }

    @GetMapping("/shuffles/{shuffleId}")
    public ShuffleStatus shuffle(@PathVariable("gameId") UUID gameId, @PathVariable("shuffleId") UUID shuffleId) {
        Game game = gameService.get(gameId);
        return gameCardsService.getShuffle(shuffleId);
    }
}
