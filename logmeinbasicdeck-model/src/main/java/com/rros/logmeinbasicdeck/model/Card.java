package com.rros.logmeinbasicdeck.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
import java.util.UUID;

// https://github.com/FasterXML/jackson-future-ideas/issues/46
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
// XXX 2020-12-15 rosr ignore deck to avoid issue with presentation
@JsonIgnoreProperties("deck")
public record Card(UUID uuid, Deck deck, Suit<?> suit, CardValue<?> cardValue) {
    public Card(Deck deck, Suit<?> suit, CardValue<?> cardValue) {
        this(UUID.randomUUID(), deck, suit, cardValue);
    }

    // XXX 2020-12-15 rosr have to override equals/hashCode to avoid StackOverflowError
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return deck.getUuid().equals(card.deck.getUuid()) && suit.equals(card.suit) && cardValue.equals(card.cardValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deck.getUuid(), suit, cardValue);
    }

    @Override
    public String toString() {
        return "Card{" +
                "uuid=" + uuid +
                ", suit=" + suit +
                ", cardValue=" + cardValue +
                '}';
    }
}
