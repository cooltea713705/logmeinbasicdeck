package com.rros.logmeinbasicdeck.controller;

import com.rros.logmeinbasicdeck.service.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameDeckControllerTest {

    private static final UUID GAME_ID = UUID.randomUUID();
    private static final UUID DECK_ID = UUID.randomUUID();

    @InjectMocks
    private GameDeckController gameDeckController;

    @Mock
    private GameService gameServiceMock;

    @Test
    void add_nominal_flow() {
        gameDeckController.add(GAME_ID, DECK_ID);

        verify(gameServiceMock).add(GAME_ID, DECK_ID);
    }
}