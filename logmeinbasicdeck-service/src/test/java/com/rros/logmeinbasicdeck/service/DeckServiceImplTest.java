package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.model.Deck;
import com.rros.logmeinbasicdeck.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class DeckServiceImplTest {

    public static final UUID RANDOM_DECK_UUID = UUID.randomUUID();

    private DeckServiceImpl deckService;
    private Map<UUID, Deck> decks;
    private Map<UUID, Set<Game>> deckGames;

    @Mock
    private Deck deckMock;

    @Mock
    private Game gameMock;

    @BeforeEach
    void setUp() {
        decks = new HashMap<>();
        deckGames = new HashMap<>();
        deckService = new DeckServiceImpl(decks, deckGames);

        lenient().when(deckMock.getUuid()).thenReturn(RANDOM_DECK_UUID);
    }

    @Test
    void create_nominal_flow() {
        UUID deckId = deckService.create();

        assertThat(decks).containsKey(deckId);
    }

    @Test
    void get_single_nominal_flow() {
        decks = Collections.singletonMap(RANDOM_DECK_UUID, deckMock);
        deckService = new DeckServiceImpl(decks, deckGames);

        Deck actualDeck = deckService.get(RANDOM_DECK_UUID);

        assertThat(actualDeck).isEqualTo(deckMock);
    }

    @Test
    void get_nominal_flow() {
        decks = Collections.singletonMap(RANDOM_DECK_UUID, deckMock);
        deckService = new DeckServiceImpl(decks, deckGames);

        Set<UUID> uuids = deckService.get();

        assertThat(uuids).containsExactly(RANDOM_DECK_UUID);
    }

    @Test
    void delete_nominal_flow() {
        deckService.delete(RANDOM_DECK_UUID);

        assertThat(decks).doesNotContainKey(RANDOM_DECK_UUID);
    }

    @Test
    void delete_when_associated_throws_IllegalArgumentException() {
        deckGames.put(RANDOM_DECK_UUID, Collections.singleton(mock(Game.class)));

        assertThatThrownBy(() -> deckService.delete(RANDOM_DECK_UUID)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void associate_nominal_flow() {
        deckService.associate(deckMock, gameMock);

        assertThat(deckGames).containsEntry(RANDOM_DECK_UUID, Collections.singleton(gameMock));
    }

    @Test
    void dissociate_nominal_flow() {
        deckGames.put(RANDOM_DECK_UUID, new HashSet<>(Collections.singleton(gameMock)));

        deckService.dissociate(deckMock, gameMock);

        assertThat(deckGames).containsEntry(RANDOM_DECK_UUID, Collections.emptySet());
    }
}