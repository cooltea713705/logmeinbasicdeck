package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.model.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DeckServiceImpl implements DeckService {

    private final Map<UUID, Deck> decks;

    @Autowired
    public DeckServiceImpl() {
        this(new ConcurrentHashMap<>());
    }

    DeckServiceImpl(Map<UUID, Deck> decks) {
        this.decks = decks;
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
}
