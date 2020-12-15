package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.model.Game;

import java.util.Set;
import java.util.UUID;

public interface GameService {
    Set<UUID> get();

    Game get(UUID gameId);

    UUID create();

    void delete(UUID gameId);
}
