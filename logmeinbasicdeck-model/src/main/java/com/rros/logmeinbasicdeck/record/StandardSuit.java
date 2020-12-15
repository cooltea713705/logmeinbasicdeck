package com.rros.logmeinbasicdeck.record;

import java.util.Locale;

public enum StandardSuit implements Suit<StandardSuit> {
    // XXX 2020-12-14 rosr ordering given by problem statement
    HEARTS, SPADES, CLUBS, DIAMONDS;

    @Override
    public String getName() {
        // XXX 2020-12-14 rosr representation given problem statement
        return name().toLowerCase(Locale.ROOT);
    }
}
