package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.model.Card;
import com.rros.logmeinbasicdeck.model.Game;
import com.rros.logmeinbasicdeck.model.Player;

import java.util.List;
import java.util.UUID;

public interface GamePlayerService {
    UUID create(Game game);

    void delete(Game game, UUID playerId);

    void dealCards(Game game, UUID playerId, int nbCards);

    List<Card> getCards(Game game, UUID playerId);

    List<Player> get(Game game);
}
