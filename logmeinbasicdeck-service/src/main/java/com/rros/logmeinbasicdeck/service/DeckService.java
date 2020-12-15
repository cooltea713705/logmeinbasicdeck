package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.model.Deck;

import java.util.UUID;

public interface DeckService {
    UUID create();

    Deck get(UUID deckId);
}