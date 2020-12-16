package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.dto.SuitCardValue;
import com.rros.logmeinbasicdeck.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.SortedMap;

import static com.rros.logmeinbasicdeck.model.StandardCardValue.ACE;
import static com.rros.logmeinbasicdeck.model.StandardCardValue.FIVE;
import static com.rros.logmeinbasicdeck.model.StandardSuit.CLUBS;
import static com.rros.logmeinbasicdeck.model.StandardSuit.DIAMONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameCardsServiceImplTest {

    @InjectMocks
    private GameCardsServiceImpl gameCardsService;

    @Mock
    private Game gameMock;

    @Mock
    private Deck deckMock;

    @Mock
    private Deck deck2Mock;

    @BeforeEach
    void setUp() {
        when(gameMock.getGameDeck()).thenReturn(
                Arrays.asList(
                        new Card(deckMock, DIAMONDS, ACE),
                        new Card(deckMock, DIAMONDS, FIVE),
                        new Card(deck2Mock, DIAMONDS, FIVE),
                        new Card(deckMock, CLUBS, FIVE)));
    }

    @Test
    void getNumberOfCardsBySuit_nominal_flow() {
        SortedMap<Suit<?>, Long> numberOfCardsBySuit = gameCardsService.getNumberOfCardsBySuit(gameMock);

        assertThat(numberOfCardsBySuit.keySet()).containsExactly(CLUBS, DIAMONDS);
        assertThat(numberOfCardsBySuit.get(CLUBS)).isGreaterThanOrEqualTo(1);
        assertThat(numberOfCardsBySuit.get(DIAMONDS)).isGreaterThanOrEqualTo(2);
    }

    @Test
    void getNumberOfCardsBySuitAndByValue_nominal_flow() {
        SortedMap<SuitCardValue<StandardSuit, StandardCardValue>, Long> numberOfCardsBySuitAndByValue = gameCardsService.getNumberOfCardsBySuitAndByValue(gameMock);

        assertThat(numberOfCardsBySuitAndByValue.keySet()).containsExactly(new SuitCardValue<>(CLUBS, FIVE), new SuitCardValue<>(DIAMONDS, FIVE), new SuitCardValue<>(DIAMONDS, ACE));
        assertThat(numberOfCardsBySuitAndByValue.get(new SuitCardValue<>(CLUBS, FIVE))).isGreaterThanOrEqualTo(1);
        assertThat(numberOfCardsBySuitAndByValue.get(new SuitCardValue<>(DIAMONDS, FIVE))).isGreaterThanOrEqualTo(2);
        assertThat(numberOfCardsBySuitAndByValue.get(new SuitCardValue<>(DIAMONDS, ACE))).isGreaterThanOrEqualTo(1);
    }
}