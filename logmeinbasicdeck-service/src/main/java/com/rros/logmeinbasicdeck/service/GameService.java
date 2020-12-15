package com.rros.logmeinbasicdeck.service;

import java.util.Set;
import java.util.UUID;

public interface GameService {
    Set<UUID> get();

    UUID create();

    void delete(UUID uuid);

    void add(UUID gameId, UUID deckId);
}
