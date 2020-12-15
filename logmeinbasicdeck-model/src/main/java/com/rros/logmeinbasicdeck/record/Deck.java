package com.rros.logmeinbasicdeck.record;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Deck extends HashSet<Card> {
    private final UUID uuid;

    private Deck(UUID uuid, Set<Suit<?>> suits, Set<CardValue<?>> cardValues) {
        this.uuid = uuid;
        // todo make immutable
        suits.forEach(suit -> cardValues.forEach(cardValue -> this.add(new Card(this, suit, cardValue))));
    }

    public Deck(Set<Suit<?>> suits, Set<CardValue<?>> cardValues) {
        this(UUID.randomUUID(), suits, cardValues);
    }

    public UUID getUuid() {
        return uuid;
    }
}
