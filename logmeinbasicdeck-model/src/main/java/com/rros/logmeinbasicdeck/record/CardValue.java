package com.rros.logmeinbasicdeck.record;

public interface CardValue<T extends CardValue<T>> extends Comparable<T> {
    String getFaceValue();
    int getIntValue();
}
