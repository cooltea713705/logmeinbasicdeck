package logmeinbasicdeck.record;

import java.util.HashSet;
import java.util.Set;

public class Deck extends HashSet<Card> {
    public Deck(Set<Suit<?>> suits, Set<CardValue<?>> cardValues) {
        // todo make immutable
        suits.forEach(suit -> cardValues.forEach(cardValue -> this.add(new Card(this, suit, cardValue))));
    }
}
