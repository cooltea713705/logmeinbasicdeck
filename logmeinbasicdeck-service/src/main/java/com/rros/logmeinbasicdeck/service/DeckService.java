package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.model.Deck;
import com.rros.logmeinbasicdeck.model.Game;

import java.util.Set;
import java.util.UUID;

public interface DeckService {
    UUID create();

    Deck get(UUID deckId);

    Set<UUID> get();

    void delete(UUID deckId);

    void associate(Deck deck, Game game);

    void dissociate(Deck deck, Game game);
}
