package com.rros.logmeinbasicdeck.pojo;

import com.rros.logmeinbasicdeck.record.*;

import java.util.*;

public class Game {

    // todo consider whether having a GamePlayer association is relevant or not (dealCards(int nbCards) seem to be a method of that association)
    private final UUID uuid;
    private final Map<Player, Set<Card>> players;
    private final Set<Deck> gameDeck;

    public Game() {
        this(UUID.randomUUID(), new HashMap<>(), new HashSet<>());
    }

    private Game(UUID uuid, Map<Player, Set<Card>> players, Set<Deck> gameDeck) {
        this.uuid = uuid;
        this.players = players;
        this.gameDeck = gameDeck;
    }

    public Deck createDeck(Set<Suit<?>> suits, Set<CardValue<?>> cardValues) {
        return new Deck(suits, cardValues);
    }

    public void addPlayer(Player player) {
        // TODO 2020-12-14 rosr handle duplicate player
        players.put(player, new HashSet<>());
    }

    public void removePlayer(Player player) {
        // TODO 2020-12-14 rosr decide what to do with dealt cards
        players.remove(player);
    }
}
