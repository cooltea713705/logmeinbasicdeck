package com.rros.logmeinbasicdeck.record;

import com.rros.logmeinbasicdeck.pojo.Game;

import java.util.UUID;

public record Player(UUID uuid, Game game) {
    public Player(Game game) {
        this(UUID.randomUUID(), game);
        game.addPlayer(this);
    }
}
