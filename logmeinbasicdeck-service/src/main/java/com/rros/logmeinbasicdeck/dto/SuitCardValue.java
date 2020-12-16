package com.rros.logmeinbasicdeck.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.rros.logmeinbasicdeck.model.CardValue;
import com.rros.logmeinbasicdeck.model.Suit;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record SuitCardValue<U extends Suit<U>, V extends CardValue<V>>(U suit,
                                                                       V cardValue) implements Comparable<SuitCardValue<U, V>> {

    @Override
    public int compareTo(SuitCardValue<U, V> o) {
        int i = suit.compareTo(o.suit);
        if (i == 0) {
            // XXX 2020-12-15 rosr descending card value order
            return -1 * cardValue.compareTo(o.cardValue);
        }
        return i;
    }
}