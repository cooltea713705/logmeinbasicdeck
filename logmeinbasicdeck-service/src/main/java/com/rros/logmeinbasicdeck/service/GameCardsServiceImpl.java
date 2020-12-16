package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.dto.SuitCardValue;
import com.rros.logmeinbasicdeck.model.Card;
import com.rros.logmeinbasicdeck.model.CardValue;
import com.rros.logmeinbasicdeck.model.Game;
import com.rros.logmeinbasicdeck.model.Suit;
import org.springframework.stereotype.Service;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class GameCardsServiceImpl implements GameCardsService {

    @Override
    public SortedMap<Suit<?>, Long> getNumberOfCardsBySuit(Game game) {
        return game.getGameDeck().stream().collect(Collectors.groupingBy(Card::suit, TreeMap::new, Collectors.counting()));
    }

    @Override
    public <U extends Suit<U>, V extends CardValue<V>> SortedMap<SuitCardValue<U, V>, Long> getNumberOfCardsBySuitAndByValue(Game game) {
        // TODO 2020-12-15 rosr generify Deck to correctly support this (Game generics is determined at runtime though)
        return game.getGameDeck().stream().collect(Collectors.groupingBy(card -> new SuitCardValue<>((U) card.suit(), (V) card.cardValue()), TreeMap::new, Collectors.counting()));
    }
}
