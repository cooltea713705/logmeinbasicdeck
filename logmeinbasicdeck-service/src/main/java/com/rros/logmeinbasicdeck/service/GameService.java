package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.pojo.Game;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

public interface GameService {
    Set<Game> get();

    boolean add(Game game);

    boolean remove(UUID uuid);

    Game getGame(UUID uuid);
}
