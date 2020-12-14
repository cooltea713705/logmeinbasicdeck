package logmeinbasicdeck.pojo;

import logmeinbasicdeck.record.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Game {

    // todo consider whether having a GamePlayer association is relevant or not (dealCards(int nbCards) seem to be a method of that association)
    private final Map<Player, Set<Card>> players;
    private final Set<Deck> gameDeck;

    public Game() {
        this(new HashMap<>(), new HashSet<>());
    }

    private Game(Map<Player, Set<Card>> players, Set<Deck> gameDeck) {
        this.players = players;
        this.gameDeck = gameDeck;
    }

    public Deck createDeck(Set<Suit<?>> suits, Set<CardValue<?>> cardValues) {
        return new Deck(suits, cardValues);
    }

    public void addPlayer(Player player) {
        // todo handle duplicate player
        players.put(player, new HashSet<>());
    }

    public void removePlayer(Player player) {
        // todo decide what to do with dealt cards
        players.remove(player);
    }
}
