package com.rros.logmeinbasicdeck.model;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Deck extends HashSet<Card> {
    private final UUID uuid;

    private Deck(UUID uuid, Set<? extends Suit<?>> suits, Set<? extends CardValue<?>> cardValues) {
        this.uuid = uuid;
        // TODO 2020-12-15 rosr make immutable
        suits.forEach(suit -> cardValues.forEach(cardValue -> this.add(new Card(this, suit, cardValue))));
    }

    public Deck(Set<? extends Suit<?>> suits, Set<? extends CardValue<?>> cardValues) {
        this(UUID.randomUUID(), suits, cardValues);
    }

    public Deck() {
        this(UUID.randomUUID(), EnumSet.allOf(StandardSuit.class), EnumSet.allOf(StandardCardValue.class));
    }

    public UUID getUuid() {
        return uuid;
    }
}
