package com.rros.logmeinbasicdeck.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"game", "cards"})
public class Player {
    private final UUID uuid;
    private final Game game;
    private final List<Card> cards = new ArrayList<>();

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

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    /**
     * Sums the int value of the player's cards (as long).
     */
    public long getSumCardsIntValue() {
        return cards.stream().map(Card::cardValue).map(CardValue::getIntValue).reduce(0, Integer::sum);
    }

    public void dealCards(int nbCards) {
        List<Card> gameDeck = game.getInternalGameDeck();
        for (int i = 0; i < nbCards; i++) {
            if (gameDeck.isEmpty()) {
                return;
            }
            Card card = gameDeck.remove(0);
            cards.add(card);
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

    @Override
    public String toString() {
        return "Player{" +
                "uuid=" + uuid +
                '}';
    }
}
