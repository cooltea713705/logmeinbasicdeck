package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.dto.ShuffleStatus;
import com.rros.logmeinbasicdeck.dto.SuitCardValue;
import com.rros.logmeinbasicdeck.model.Card;
import com.rros.logmeinbasicdeck.model.CardValue;
import com.rros.logmeinbasicdeck.model.Game;
import com.rros.logmeinbasicdeck.model.Suit;

import java.util.List;
import java.util.SortedMap;
import java.util.UUID;

public interface GameCardsService {

    SortedMap<Suit<?>, Long> getNumberOfCardsBySuit(Game game);

    <U extends Suit<U>, V extends CardValue<V>> SortedMap<SuitCardValue<U, V>, Long> getNumberOfCardsBySuitAndByValue(Game game);

    UUID shuffle(Game game);

    ShuffleStatus getShuffle(UUID shuffleId);

    List<Card> get(Game game);
}
