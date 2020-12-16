package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.model.Game;
import com.rros.logmeinbasicdeck.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    public static final UUID RANDOM_GAME_UUID = UUID.randomUUID();
    public static final UUID RANDOM_PLAYER_UUID = UUID.randomUUID();

    @Mock
    private Game gameMock;

    @Mock
    private GamePlayerService gamePlayerServiceMock;

    private GameServiceImpl gameService;

    private Map<UUID, Game> games;

    @BeforeEach
    void setUp() {
        games = new HashMap<>();
        gameService = new GameServiceImpl(games, gamePlayerServiceMock);
    }

    @Test
    void get_nominal_flow() {
        games = Collections.singletonMap(RANDOM_GAME_UUID, gameMock);
        gameService = new GameServiceImpl(games, gamePlayerServiceMock);

        Set<UUID> uuids = gameService.get();

        assertThat(uuids).containsExactly(RANDOM_GAME_UUID);
    }

    @Test
    void get_single_nominal_flow() {
        games = Collections.singletonMap(RANDOM_GAME_UUID, gameMock);
        gameService = new GameServiceImpl(games, gamePlayerServiceMock);

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
        Player playerMock = mock(Player.class);
        when(gameMock.getPlayers()).thenReturn(Collections.singletonList(playerMock));
        when(playerMock.uuid()).thenReturn(RANDOM_PLAYER_UUID);

        gameService.delete(RANDOM_GAME_UUID);

        assertThat(games).doesNotContainEntry(RANDOM_GAME_UUID, gameMock);
        verify(gameMock).getPlayers();
        verify(gamePlayerServiceMock).delete(gameMock, RANDOM_PLAYER_UUID);
    }
}