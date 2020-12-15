package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.pojo.Game;
import com.rros.logmeinbasicdeck.record.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameServiceImpl implements GameService {

    // TODO 2020-12-14 rosr replace with repo
    private final Map<UUID, Game> games;
    private final DeckService deckService;

    @Autowired
    public GameServiceImpl(DeckService deckService) {
        this(deckService, new ConcurrentHashMap<>());
    }

    GameServiceImpl(DeckService deckService, Map<UUID, Game> games) {
        this.deckService = deckService;
        this.games = games;
    }

    @Override
    public Set<UUID> get() {
        return new HashSet<>(games.keySet());
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
    public void delete(UUID uuid) {
        Game result = games.remove(uuid);
        if (result == null) {
            throw new IllegalStateException("Could not remove game");
        }
    }

    // TODO 2020-12-15 rosr move to GameDeckService#add(Game game, UUID deck)?
    @Override
    public void add(UUID gameId, UUID deckId) {
        Game addDeckToGame = Objects.requireNonNull(games.get(gameId));

        Deck deck = deckService.get(deckId);

        // TODO 2020-12-15 rosr implement integrity constraints (check suits/cardValues)
        addDeckToGame.addDeck(deck);
    }
}
