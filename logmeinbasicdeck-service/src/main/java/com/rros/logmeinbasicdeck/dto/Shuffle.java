package com.rros.logmeinbasicdeck.dto;

import com.rros.logmeinbasicdeck.model.Game;

import java.util.UUID;

public class Shuffle {
    private final UUID uuid = UUID.randomUUID();
    private final Game game;
    private ShuffleStatus shuffleStatus = ShuffleStatus.PENDING;

    public Shuffle(Game game) {
        this.game = game;
    }

    public UUID uuid() {
        return uuid;
    }

    public ShuffleStatus getShuffleStatus() {
        return shuffleStatus;
    }

    public void markCompleted() {
        this.shuffleStatus = ShuffleStatus.COMPLETED;
    }
}
