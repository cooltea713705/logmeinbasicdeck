package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.pojo.Game;
import com.rros.logmeinbasicdeck.record.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GamePlayerServiceImplTest {

    private GamePlayerServiceImpl gamePlayerService;
    private HashMap<UUID, Player> players;

    @Mock
    private Game gameMock;

    @BeforeEach
    void setUp() {
        players = new HashMap<>();
        gamePlayerService = new GamePlayerServiceImpl(players);
    }

    @Test
    void create_nominal_flow() {
        UUID uuid = gamePlayerService.create(gameMock);

        assertThat(players).containsKey(uuid);
        ArgumentCaptor<Player> playerArgumentCaptor = ArgumentCaptor.forClass(Player.class);
        verify(gameMock).addPlayer(playerArgumentCaptor.capture());
        Player player = playerArgumentCaptor.getValue();
        assertThat(player.game()).isEqualTo(gameMock);
        assertThat(player.uuid()).isEqualTo(uuid);
    }

    @Test
    void delete_nominal_flow() {
        Player player = new Player(gameMock);
        UUID uuid = player.uuid();
        players.put(uuid, player);

        gamePlayerService.delete(gameMock, uuid);

        assertThat(players).doesNotContainEntry(uuid, player);
    }
}