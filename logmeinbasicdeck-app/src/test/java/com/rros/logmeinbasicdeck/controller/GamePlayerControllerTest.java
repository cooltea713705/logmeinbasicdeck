package com.rros.logmeinbasicdeck.controller;

import com.rros.logmeinbasicdeck.pojo.Game;
import com.rros.logmeinbasicdeck.service.GamePlayerService;
import com.rros.logmeinbasicdeck.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GamePlayerControllerTest {

    public static final UUID RANDOM_GAME_UUID = UUID.randomUUID();
    public static final UUID RANDOM_PLAYER_UUID = UUID.randomUUID();

    @InjectMocks
    private GamePlayerController gamePlayerController;

    @Mock
    private GameService gameServiceMock;

    @Mock
    private GamePlayerService gamePlayerServiceMock;

    @Mock
    private Game gameMock;

    @BeforeEach
    void setUp() {
        when(gameServiceMock.get(RANDOM_GAME_UUID)).thenReturn(gameMock);
    }

    @Test
    void create_nominal_flow() {
        when(gamePlayerServiceMock.create(gameMock)).thenReturn(RANDOM_PLAYER_UUID);

        UUID uuid = gamePlayerController.create(RANDOM_GAME_UUID);

        verify(gameServiceMock).get(RANDOM_GAME_UUID);
        verify(gamePlayerServiceMock).create(gameMock);
        assertThat(uuid).isEqualTo(RANDOM_PLAYER_UUID);
    }

    @Test
    void delete_nominal_flow() {
        gamePlayerController.delete(RANDOM_GAME_UUID, RANDOM_PLAYER_UUID);

        verify(gamePlayerServiceMock).delete(gameMock, RANDOM_PLAYER_UUID);
    }
}