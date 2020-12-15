package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.record.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DeckServiceImplTest {

    public static final UUID RANDOM_DECK_UUID = UUID.randomUUID();

    private DeckServiceImpl deckService;
    private Map<UUID, Deck> decks;

    @Mock
    private Deck deckMock;

    @BeforeEach
    void setUp() {
        decks = new HashMap<>();
        deckService = new DeckServiceImpl(decks);
    }

    @Test
    void create_nominal_flow() {
        UUID deckId = deckService.create();

        assertThat(decks).containsKey(deckId);
    }

    @Test
    void get_nominal_flow() {
        decks = Collections.singletonMap(RANDOM_DECK_UUID, deckMock);
        deckService = new DeckServiceImpl(decks);

        Deck actualDeck = deckService.get(RANDOM_DECK_UUID);

        assertThat(actualDeck).isEqualTo(deckMock);
    }
}