package com.rros.logmeinbasicdeck.record;

import com.rros.logmeinbasicdeck.pojo.Game;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Deck extends HashSet<Card> {
    private final UUID uuid;
    private final Game game;

    private Deck(UUID uuid, Game game, Set<? extends Suit<?>> suits, Set<? extends CardValue<?>> cardValues) {
        this.uuid = uuid;
        this.game = game;
        // todo make immutable
        suits.forEach(suit -> cardValues.forEach(cardValue -> this.add(new Card(this, suit, cardValue))));
    }

    public Deck(Game game, Set<? extends Suit<?>> suits, Set<? extends CardValue<?>> cardValues) {
        this(UUID.randomUUID(), game, suits, cardValues);
    }

    public UUID getUuid() {
        return uuid;
    }

    public Game getGame() {
        return game;
    }
}
