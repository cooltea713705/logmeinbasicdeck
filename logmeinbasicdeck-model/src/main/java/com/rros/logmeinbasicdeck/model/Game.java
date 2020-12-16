package com.rros.logmeinbasicdeck.model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    private final UUID uuid;
    private final List<Player> players;
    private final List<Card> gameDeck = Collections.synchronizedList(new ArrayList<>());

    public Game() {
        this(UUID.randomUUID(), new ArrayList<>(), new HashSet<>());
    }

    private Game(UUID gameId, List<Player> players, Set<Deck> decks) {
        this.uuid = gameId;
        this.players = players;
        decks.forEach(this::addDeck);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void addDeck(Deck deck) {
        gameDeck.addAll(deck);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
        // XXX 2020-12-15 rosr added player card back to game deck
        gameDeck.addAll(player.getCards());
    }

    public void shuffleGameDeck() {
        // XXX 2020-12-15 rosr https://www.baeldung.com/java-thread-local-random
        shuffleGameDeck(ThreadLocalRandom.current());
    }

    void shuffleGameDeck(Random random) {
        // XXX 2020-12-15 rosr synchronized so that shuffling needs to be completed before the gameDeck is made available
        synchronized (gameDeck) {
            Collections.shuffle(gameDeck, random);
        }
    }

    public List<Card> getGameDeck() {
        return new ArrayList<>(gameDeck);
    }

    List<Card> getInternalGameDeck() {
        return gameDeck;
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
}
