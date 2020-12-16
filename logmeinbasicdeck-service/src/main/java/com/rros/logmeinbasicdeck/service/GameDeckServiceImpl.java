package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.model.Deck;
import com.rros.logmeinbasicdeck.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameDeckServiceImpl implements GameDeckService {

    private final DeckService deckService;

    @Autowired
    public GameDeckServiceImpl(DeckService deckService) {
        this.deckService = deckService;
    }

    @Override
    public void add(Game game, UUID deckId) {
        Deck deck = deckService.get(deckId);

        // TODO 2020-12-15 rosr implement integrity constraints (make sure suits and card values are all matching)
        game.addDeck(deck);
    }
}
