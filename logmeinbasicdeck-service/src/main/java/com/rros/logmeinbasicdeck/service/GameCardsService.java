package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.dto.SuitCardValue;
import com.rros.logmeinbasicdeck.model.Card;
import com.rros.logmeinbasicdeck.model.CardValue;
import com.rros.logmeinbasicdeck.model.Game;
import com.rros.logmeinbasicdeck.model.Suit;

import java.util.List;
import java.util.SortedMap;

public interface GameCardsService {

    SortedMap<Suit<?>, Long> getNumberOfCardsBySuit(Game game);

    <U extends Suit<U>, V extends CardValue<V>> SortedMap<SuitCardValue<U, V>, Long> getNumberOfCardsBySuitAndByValue(Game game);

    List<Card> get(Game game);

    void shuffle(Game game);
}
