package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.pojo.Game;
import com.rros.logmeinbasicdeck.record.Deck;
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
class GameDeckServiceImplTest {

    public static final UUID RANDOM_DECK_UUID = UUID.randomUUID();
    @InjectMocks
    private GameDeckServiceImpl gameDeckService;

    @Mock
    private DeckService deckServiceMock;

    @Mock
    private Game gameMock;

    @Mock
    private Deck deckMock;

    @BeforeEach
    void setUp() {
        when(deckServiceMock.get(RANDOM_DECK_UUID)).thenReturn(deckMock);
    }

    @Test
    void add_nominal_flow() {
        gameDeckService.add(gameMock, RANDOM_DECK_UUID);

        verify(deckServiceMock).get(RANDOM_DECK_UUID);
        verify(gameMock).addDeck(deckMock);
    }
}