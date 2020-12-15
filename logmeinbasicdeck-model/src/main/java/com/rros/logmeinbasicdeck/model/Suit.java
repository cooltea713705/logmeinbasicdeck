package com.rros.logmeinbasicdeck.model;

public interface Suit<T extends Suit<T>> extends Comparable<T> {
    String getName();
}
