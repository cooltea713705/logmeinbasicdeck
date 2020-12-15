package com.rros.logmeinbasicdeck.model;

public interface CardValue<T extends CardValue<T>> extends Comparable<T> {
    String getFaceValue();

    int getIntValue();
}
