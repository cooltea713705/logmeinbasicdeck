package com.rros.logmeinbasicdeck.controller;

import com.rros.logmeinbasicdeck.service.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    public static final UUID RANDOM_UUID = UUID.randomUUID();

    @Mock
    private GameService gameServiceMock;

    @InjectMocks
    private GameController gameController;

    @Test
    void get_nominal_flow() {
        when(gameServiceMock.get()).thenReturn(Collections.singleton(RANDOM_UUID));

        Set<UUID> uuids = gameController.get();

        verify(gameServiceMock).get();
        assertThat(uuids).containsExactly(RANDOM_UUID);
    }

    @Test
    void add_nominal_flow() {
        when(gameServiceMock.create()).thenReturn(RANDOM_UUID);

        UUID uuid = gameController.add();

        verify(gameServiceMock).create();
        assertThat(uuid).isEqualTo(RANDOM_UUID);
    }

    @Test
    void delete_nominal_flow() {
        gameController.delete(RANDOM_UUID);

        verify(gameServiceMock).delete(RANDOM_UUID);
    }
}