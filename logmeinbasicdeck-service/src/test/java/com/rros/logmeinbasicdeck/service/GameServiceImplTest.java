package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    public static final UUID RANDOM_DECK_UUID = UUID.randomUUID();
    public static final UUID RANDOM_GAME_UUID = UUID.randomUUID();

    @Mock
    private DeckService deckServiceMock;

    @Mock
    private Game gameMock;

    private GameServiceImpl gameService;

    private Map<UUID, Game> games;

    @BeforeEach
    void setUp() {
        games = new HashMap<>();
        gameService = new GameServiceImpl(deckServiceMock, games);
    }

    @Test
    void get_nominal_flow() {
        games = Collections.singletonMap(RANDOM_GAME_UUID, gameMock);
        gameService = new GameServiceImpl(deckServiceMock, games);

        Set<UUID> uuids = gameService.get();

        assertThat(uuids).containsExactly(RANDOM_GAME_UUID);
    }

    @Test
    void get_single_nominal_flow() {
        games = Collections.singletonMap(RANDOM_GAME_UUID, gameMock);
        gameService = new GameServiceImpl(deckServiceMock, games);

        Game game = gameService.get(RANDOM_GAME_UUID);

        assertThat(game).isEqualTo(gameMock);
    }

    @Test
    void create_nominal_flow() {
        UUID gameId = gameService.create();

        assertThat(games).containsKeys(gameId);
    }

    @Test
    void delete_nominal_flow() {
        games.put(RANDOM_GAME_UUID, gameMock);

        gameService.delete(RANDOM_GAME_UUID);

        assertThat(games).doesNotContainEntry(RANDOM_GAME_UUID, gameMock);
    }
}