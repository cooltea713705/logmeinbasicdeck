package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.pojo.Game;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    // TODO 2020-12-14 rosr replace with repo
    private final Set<Game> games = new HashSet<>();

    @Override
    public Set<UUID> get() {
        return games.stream().map(Game::getUuid).collect(Collectors.toSet());
    }

    @Override
    public UUID add() {
        Game game = new Game();
        boolean result = games.add(game);
        if (!result) {
            throw new IllegalStateException("Could not add game");
        }
        return game.getUuid();
    }

    @Override
    public void delete(UUID uuid) {
        boolean result = games.removeIf(game -> Objects.equals(game.getUuid(), uuid));
        if (!result) {
            throw new IllegalStateException("Could not remove game");
        }
    }
}
