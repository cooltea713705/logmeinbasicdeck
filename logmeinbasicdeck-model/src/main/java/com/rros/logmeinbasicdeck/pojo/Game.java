package com.rros.logmeinbasicdeck.pojo;

import com.rros.logmeinbasicdeck.record.*;

import java.util.*;

public class Game {

    // todo consider whether having a GamePlayer association is relevant or not (dealCards(int nbCards) seem to be a method of that association)
    private final UUID uuid;
    private final Map<Player, Set<Card>> players;
    private final Set<Deck> decks = new HashSet<>();
    private final Set<Card> gameDeck = new HashSet<>();
    private final Set<? extends Suit<?>> suits;
    private final Set<? extends CardValue<?>> cardValues;

    public Game() {
        this(UUID.randomUUID(), new HashMap<>(), new HashSet<>(), EnumSet.allOf(StandardSuit.class), EnumSet.allOf(StandardCardValue.class));
    }

    public Game(Set<Suit<?>> suits, Set<CardValue<?>> cardValues) {
        this(UUID.randomUUID(), new HashMap<>(), new HashSet<>(), suits, cardValues);
    }

    private Game(UUID uuid, Map<Player, Set<Card>> players, Set<Deck> decks, Set<? extends Suit<?>> suits, Set<? extends CardValue<?>> cardValues) {
        this.uuid = uuid;
        this.players = players;
        decks.forEach(this::addDeck);
        this.suits = suits;
        this.cardValues = cardValues;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Deck createDeck() {
        return new Deck(this, suits, cardValues);
    }

    public void addDeck(Deck deck) {
        if (!Objects.equals(this, deck.getGame())) {
            throw new IllegalArgumentException("Adding a deck from another game is illegal");
        }
        decks.add(deck);
        gameDeck.addAll(deck);
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
