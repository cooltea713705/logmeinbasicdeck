package logmeinbasicdeck.record;

public record Card(Deck deck, Suit<?> suit, CardValue<?> cardValue) {
}
