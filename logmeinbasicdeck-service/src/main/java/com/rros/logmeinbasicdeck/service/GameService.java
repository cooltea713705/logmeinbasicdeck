package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.pojo.Game;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

public interface GameService {
    Set<UUID> get();

    UUID add();

    void delete(UUID uuid);
}
