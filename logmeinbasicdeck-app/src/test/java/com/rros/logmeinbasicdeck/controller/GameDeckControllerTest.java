package com.rros.logmeinbasicdeck.controller;

import com.rros.logmeinbasicdeck.model.Game;
import com.rros.logmeinbasicdeck.service.GameDeckService;
import com.rros.logmeinbasicdeck.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameDeckControllerTest {

    private static final UUID GAME_ID = UUID.randomUUID();
    private static final UUID DECK_ID = UUID.randomUUID();

    @InjectMocks
    private GameDeckController gameDeckController;

    @Mock
    private GameService gameServiceMock;

    @Mock
    private GameDeckService gameDeckServiceMock;

    @Mock
    private Game gameMock;

    @BeforeEach
    void setUp() {
        when(gameServiceMock.get(GAME_ID)).thenReturn(gameMock);
    }

    @Test
    void add_nominal_flow() {
        gameDeckController.add(GAME_ID, DECK_ID);

        verify(gameServiceMock).get(GAME_ID);
        verify(gameDeckServiceMock).add(gameMock, DECK_ID);
    }
}