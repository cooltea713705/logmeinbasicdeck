package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.pojo.Game;

import java.util.UUID;

public interface GamePlayerService {
    UUID create(Game game);

    void delete(Game game, UUID uuid);
}
