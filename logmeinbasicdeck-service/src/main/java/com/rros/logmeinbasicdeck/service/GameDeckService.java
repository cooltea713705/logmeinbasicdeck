package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.pojo.Game;

import java.util.UUID;

public interface GameDeckService {
    void add(Game game, UUID deckId);
}
