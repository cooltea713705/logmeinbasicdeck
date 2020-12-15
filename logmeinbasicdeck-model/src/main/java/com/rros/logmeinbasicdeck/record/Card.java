package com.rros.logmeinbasicdeck.record;

import java.util.UUID;

public record Card(UUID uuid, Deck deck, Suit<?> suit, CardValue<?> cardValue) {
    public Card(Deck deck, Suit<?> suit, CardValue<?> cardValue) {
        this(UUID.randomUUID(), deck, suit, cardValue);
    }
}
