package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameServiceImpl implements GameService {

    private final Map<UUID, Game> games;

    private final GamePlayerService gamePlayerService;
    private final DeckService deckService;

    @Autowired
    public GameServiceImpl(GamePlayerService gamePlayerService, DeckService deckService) {
        this(new ConcurrentHashMap<>(), gamePlayerService, deckService);
    }

    GameServiceImpl(Map<UUID, Game> games, GamePlayerService gamePlayerService, DeckService deckService) {
        this.games = games;
        this.gamePlayerService = gamePlayerService;
        this.deckService = deckService;
    }

    @Override
    public Set<UUID> get() {
        return new HashSet<>(games.keySet());
    }

    @Override
    public Game get(UUID gameId) {
        return Objects.requireNonNull(games.get(gameId));
    }

    @Override
    public UUID create() {
        Game game = new Game();
        Game result = games.put(game.getUuid(), game);
        if (result != null) {
            throw new IllegalStateException("Could not add game");
        }
        return game.getUuid();
    }

    @Override
    public void delete(UUID gameId) {
        Game result = games.remove(gameId);
        if (result == null) {
            throw new IllegalArgumentException("Could not remove game");
        }
        result.getPlayers().forEach(player -> gamePlayerService.delete(result, player.uuid()));
        result.getDecks().forEach(deck -> deckService.dissociate(deck, result));
    }
}
