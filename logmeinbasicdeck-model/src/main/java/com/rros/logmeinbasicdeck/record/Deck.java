package com.rros.logmeinbasicdeck.record;

import java.util.HashSet;
import java.util.Set;

public class Deck extends HashSet<Card> {
    public Deck(Set<Suit<?>> suits, Set<Value> values) {
        // todo make immutable
        suits.forEach(suit -> values.forEach(value -> this.add(new Card(this, suit, value))));
    }
}
