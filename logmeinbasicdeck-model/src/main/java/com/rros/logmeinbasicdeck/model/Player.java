package com.rros.logmeinbasicdeck.model;

import java.util.*;

public class Player {
    private final UUID uuid;
    private final Game game;
    private final Set<Card> hand = new HashSet<>();

    private Player(UUID uuid, Game game) {
        this.uuid = uuid;
        this.game = game;
        game.addPlayer(this);
    }

    public Player(Game game) {
        this(UUID.randomUUID(), game);
    }

    public UUID uuid() {
        return uuid;
    }

    public Game game() {
        return game;
    }

    public void dealCards(int nbCards) {
        List<Card> gameDeck = game.getGameDeck();
        for (int i = 0; i < nbCards; i++) {
            if (gameDeck.isEmpty()) {
                return;
            }
            Card card = gameDeck.remove(0);
            hand.add(card);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Player) obj;
        return Objects.equals(this.uuid, that.uuid) &&
                Objects.equals(this.game, that.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, game);
    }
}
