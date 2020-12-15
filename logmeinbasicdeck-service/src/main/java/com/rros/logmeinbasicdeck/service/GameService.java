package com.rros.logmeinbasicdeck.service;

import java.util.Set;
import java.util.UUID;

public interface GameService {
    Set<UUID> get();

    UUID add();

    void delete(UUID uuid);
}
