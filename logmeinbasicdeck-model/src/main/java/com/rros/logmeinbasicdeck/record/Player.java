package com.rros.logmeinbasicdeck.record;

import java.util.UUID;

public record Player(UUID uuid, String name) {
    public Player(String name) {
        this(UUID.randomUUID(), name);
    }
}
