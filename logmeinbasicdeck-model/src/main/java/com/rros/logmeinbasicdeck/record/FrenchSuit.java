package com.rros.logmeinbasicdeck.record;

import java.util.Locale;

public enum FrenchSuit implements Suit<FrenchSuit> {
    HEARTS, SPADES, CLUBS, DIAMONDS;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
