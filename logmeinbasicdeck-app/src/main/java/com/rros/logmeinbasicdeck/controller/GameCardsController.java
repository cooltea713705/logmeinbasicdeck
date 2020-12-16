package com.rros.logmeinbasicdeck.controller;

import com.rros.logmeinbasicdeck.dto.SuitCardValue;
import com.rros.logmeinbasicdeck.model.CardValue;
import com.rros.logmeinbasicdeck.model.Game;
import com.rros.logmeinbasicdeck.model.Suit;
import com.rros.logmeinbasicdeck.service.GameCardsService;
import com.rros.logmeinbasicdeck.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
