package com.rros.logmeinbasicdeck.controller;

import com.rros.logmeinbasicdeck.service.DeckService;
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
class DeckControllerTest {
    public static final UUID RANDOM_DECK_UUID = UUID.randomUUID();
    @InjectMocks
    private DeckController deckController;

    @Mock
    private DeckService deckServiceMock;

    @Test
    void create_nominal_flow() {
        when(deckServiceMock.create()).thenReturn(RANDOM_DECK_UUID);
        UUID deckId = deckController.create();

        verify(deckServiceMock).create();
        assertThat(deckId).isEqualTo(RANDOM_DECK_UUID);
    }

    @Test
    void delete_nominal_flow() {
        deckController.delete(RANDOM_DECK_UUID);

        verify(deckServiceMock).delete(RANDOM_DECK_UUID);
    }
}
