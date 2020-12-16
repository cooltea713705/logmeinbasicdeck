package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.model.Deck;
import com.rros.logmeinbasicdeck.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DeckServiceImpl implements DeckService {

    private final Map<UUID, Deck> decks;
    private final Map<UUID, Set<Game>> deckGames;

    @Autowired
    public DeckServiceImpl() {
        this(new ConcurrentHashMap<>(), new ConcurrentHashMap<>());
    }

    DeckServiceImpl(Map<UUID, Deck> decks, Map<UUID, Set<Game>> deckGames) {
        this.decks = decks;
        this.deckGames = deckGames;
    }

    @Override
    public UUID create() {
        Deck deck = new Deck();
        Deck result = decks.put(deck.getUuid(), deck);
        if (result != null) {
            throw new IllegalStateException("Could not add deck");
        }
        return deck.getUuid();
    }

    @Override
    public Deck get(UUID deckId) {
        return Objects.requireNonNull(decks.get(deckId));
    }

    @Override
    public Set<UUID> get() {
        return new HashSet<>(decks.keySet());
    }

    @Override
    public void delete(UUID deckId) {
        Set<Game> games = deckGames.get(deckId);
        if (games != null && !games.isEmpty()) {
            throw new IllegalArgumentException("Cannot remove an associated deck");
        }
        deckGames.remove(deckId);
        decks.remove(deckId);
    }

    @Override
    public void associate(Deck deck, Game game) {
        deckGames.computeIfAbsent(deck.getUuid(), uuid -> new HashSet<>()).add(game);
    }

    @Override
    public void dissociate(Deck deck, Game game) {
        Set<Game> games = Objects.requireNonNull(deckGames.get(deck.getUuid()));
        boolean result = games.remove(game);
        if (!result) {
            throw new IllegalStateException("Could not dissociate game from deck");
        }
    }
}
