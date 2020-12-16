package com.rros.logmeinbasicdeck.controller;

import com.rros.logmeinbasicdeck.dto.SuitCardValue;
import com.rros.logmeinbasicdeck.model.Game;
import com.rros.logmeinbasicdeck.model.StandardCardValue;
import com.rros.logmeinbasicdeck.model.StandardSuit;
import com.rros.logmeinbasicdeck.model.Suit;
import com.rros.logmeinbasicdeck.service.GameCardsService;
import com.rros.logmeinbasicdeck.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameCardsControllerTest {

    public static final UUID RANDOM_GAME_UUID = UUID.randomUUID();
    public static final SortedMap<Suit<?>, Long> CARDS_BY_SUIT;
    public static final SortedMap<SuitCardValue<StandardSuit, StandardCardValue>, Long> CARDS_BY_SUIT_AND_BY_VALUE;

    static {
        CARDS_BY_SUIT = new TreeMap<>();
        CARDS_BY_SUIT.put(StandardSuit.DIAMONDS, 3L);

        CARDS_BY_SUIT_AND_BY_VALUE = new TreeMap<>();
        CARDS_BY_SUIT_AND_BY_VALUE.put(new SuitCardValue<>(StandardSuit.CLUBS, StandardCardValue.FIVE), 3L);
    }

    @InjectMocks
    private GameCardsController gameCardsController;

    @Mock
    private GameService gameServiceMock;

    @Mock
    private GameCardsService gameCardsServiceMock;

    @Mock
    private Game gameMock;

    @BeforeEach
    void setUp() {
        when(gameServiceMock.get(RANDOM_GAME_UUID)).thenReturn(gameMock);
    }

    @Test
    void getNumberOfCardsBySuit_nominal_flow() {
        when(gameCardsServiceMock.getNumberOfCardsBySuit(gameMock)).thenReturn(CARDS_BY_SUIT);

        SortedMap<Suit<?>, Long> nbCardsBySuit = gameCardsController.getNumberOfCardsBySuit(RANDOM_GAME_UUID);

        verify(gameServiceMock).get(RANDOM_GAME_UUID);
        verify(gameCardsServiceMock).getNumberOfCardsBySuit(gameMock);
        assertThat(nbCardsBySuit).isEqualTo(CARDS_BY_SUIT);
    }

    @Test
    void getNumberOfCardsBySuitAndByValue_nominal_flow() {
        when(gameCardsServiceMock.<StandardSuit, StandardCardValue>getNumberOfCardsBySuitAndByValue(gameMock)).thenReturn(CARDS_BY_SUIT_AND_BY_VALUE);

        SortedMap<SuitCardValue<StandardSuit, StandardCardValue>, Long> nbCardsBySuitAndByValue = gameCardsController.getNumberOfCardsBySuitAndByValue(RANDOM_GAME_UUID);

        verify(gameServiceMock).get(RANDOM_GAME_UUID);
        verify(gameCardsServiceMock).getNumberOfCardsBySuitAndByValue(gameMock);
        assertThat(nbCardsBySuitAndByValue).isEqualTo(CARDS_BY_SUIT_AND_BY_VALUE);
    }
}