package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.pojo.Game;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GameServiceImpl implements GameService {

    private final Set<Game> games = new HashSet<>();

    @Override
    public Set<Game> get() {
        return games;
    }

    @Override
    public boolean add(Game game) {
        // TODO 2020-12-14 rosr implement repo call
        return games.add(game);
    }

    @Override
    public boolean remove(UUID uuid) {
        return games.removeIf(game -> Objects.equals(game.getUuid(), uuid));
    }

    @Override
    public Game getGame(UUID uuid) {
        return games.stream().filter(game -> Objects.equals(game.getUuid(), uuid)).findAny().orElseThrow();
    }
}
