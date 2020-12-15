package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.pojo.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    private GameServiceImpl gameService;

    private Set<Game> games;

    @BeforeEach
    void setUp() {
        games = new HashSet<>();
        gameService = new GameServiceImpl(games);
    }

    @Test
    void get_nominal_flow() {
        Game game = new Game();
        games = Collections.singleton(game);
        gameService = new GameServiceImpl(games);

        Set<UUID> uuids = gameService.get();

        assertThat(uuids).containsExactly(game.getUuid());
    }

    @Test
    void add_nominal_flow() {
        UUID uuid = gameService.add();

        assertThat(games).map(Game::getUuid).contains(uuid);
    }

    @Test
    void delete_nominal_flow() {
        Game game = new Game();
        games.add(game);

        gameService.delete(game.getUuid());

        assertThat(games).doesNotContain(game);
    }
}