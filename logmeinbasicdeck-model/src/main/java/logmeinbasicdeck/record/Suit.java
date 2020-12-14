package logmeinbasicdeck.record;

public interface Suit<T extends Suit<T>> extends Comparable<T> {
    String getName();
}
