package com.rros.logmeinbasicdeck.model;

import java.util.*;

public class Game {

    // TODO 2020-12-15 rosr consider whether having a GamePlayer association is relevant or not (dealCards(int nbCards) seem to be a method of that association)
    private final UUID uuid;
    private final List<Player> players;
    private final Set<Deck> decks = new HashSet<>();
    // TODO 2020-12-15 rosr handle concurrent accesses
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
        decks.add(deck);
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

    public synchronized void shuffleGameDeck() {
        // TODO 2020-12-15 rosr implement! make sure it's thread safe
        Collections.shuffle(gameDeck);
    }

    public List<Card> getGameDeck() {
        return new ArrayList<>(gameDeck);
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    List<Card> getInternalGameDeck() {
        return gameDeck;
    }
}
