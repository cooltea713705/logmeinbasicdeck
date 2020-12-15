package com.rros.logmeinbasicdeck.model;

import java.util.*;

public class Game {

    // TODO 2020-12-15 rosr consider whether having a GamePlayer association is relevant or not (dealCards(int nbCards) seem to be a method of that association)
    private final UUID uuid;
    private final Map<Player, Set<Card>> players;
    private final Set<Deck> decks = new HashSet<>();
    // TODO 2020-12-15 rosr handle concurrent accesses
    private final List<Card> gameDeck = new ArrayList<>();

    public Game() {
        this(UUID.randomUUID(), new HashMap<>(), new HashSet<>());
    }

    private Game(UUID gameId, Map<Player, Set<Card>> players, Set<Deck> decks) {
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
        players.put(player, new HashSet<>());
    }

    public void removePlayer(Player player) {
        // TODO 2020-12-14 rosr decide what to do with dealt cards
        players.remove(player);
    }

    List<Card> getGameDeck() {
        return gameDeck;
    }

    public Map<Player, Set<Card>> getPlayers() {
        return players;
    }
}
