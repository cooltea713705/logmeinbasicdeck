package com.rros.logmeinbasicdeck.record;

import java.util.*;

public class Deck extends HashSet<Card> {
    private final UUID uuid;

    private Deck(UUID uuid, Set<? extends Suit<?>> suits, Set<? extends CardValue<?>> cardValues) {
        this.uuid = uuid;
        // todo make immutable
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
