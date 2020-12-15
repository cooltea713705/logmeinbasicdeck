package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.model.Game;
import com.rros.logmeinbasicdeck.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GamePlayerServiceImplTest {

    public static final int NB_CARDS = new Random().nextInt();
    public static final UUID RANDOM_PLAYER_UUID = UUID.randomUUID();
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
        UUID playerId = player.uuid();
        players.put(playerId, player);

        gamePlayerService.delete(gameMock, playerId);

        assertThat(players).doesNotContainEntry(playerId, player);
    }

    @Test
    void dealCards_nominal_flow() {
        Player playerMock = mock(Player.class);
        players.put(RANDOM_PLAYER_UUID, playerMock);
        gamePlayerService.dealCards(gameMock, RANDOM_PLAYER_UUID, NB_CARDS);

        verify(playerMock).dealCards(NB_CARDS);
    }
}