package com.rros.logmeinbasicdeck.model;

public enum StandardCardValue implements CardValue<StandardCardValue> {
    ACE("Ace", 1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK("Jack", 11), QUEEN("Queen", 12), KING("King", 13);

    private final String faceValue;
    private final int intValue;

    StandardCardValue(int intValue) {
        this(String.valueOf(intValue), intValue);
    }

    StandardCardValue(String faceValue, int intValue) {
        this.faceValue = faceValue;
        this.intValue = intValue;
    }

    @Override
    public String getFaceValue() {
        return faceValue;
    }

    @Override
    public int getIntValue() {
        return intValue;
    }
}
