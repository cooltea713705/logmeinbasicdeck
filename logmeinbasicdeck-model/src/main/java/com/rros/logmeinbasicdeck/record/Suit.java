package com.rros.logmeinbasicdeck.record;

public interface Suit<T extends Suit<?>> extends Comparable<T> {
    String getName();
}
