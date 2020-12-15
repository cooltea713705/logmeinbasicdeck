package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.model.Game;
import com.rros.logmeinbasicdeck.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GamePlayerServiceImplTest {

    public static final int NB_CARDS = new Random().nextInt();
    public static final UUID RANDOM_PLAYER_UUID = UUID.randomUUID();
    private GamePlayerServiceImpl gamePlayerService;
    private HashMap<UUID, Player> players;

    @Mock
    private Game gameMock;

    @Mock
    private Player playerMock;

    @BeforeEach
    void setUp() {
        players = new HashMap<>();
        players.put(RANDOM_PLAYER_UUID, playerMock);
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
        gamePlayerService.delete(gameMock, RANDOM_PLAYER_UUID);

        assertThat(players).doesNotContainEntry(RANDOM_PLAYER_UUID, playerMock);
    }

    @Test
    void get_nominal_flow() {
        List<Player> players = Collections.singletonList(playerMock);
        when(gameMock.getPlayers()).thenReturn(players);

        List<Player> actualPlayers = gamePlayerService.get(gameMock);

        verify(gameMock).getPlayers();
        assertThat(actualPlayers).isEqualTo(players);
    }

    @Test
    void dealCards_nominal_flow() {
        gamePlayerService.dealCards(gameMock, RANDOM_PLAYER_UUID, NB_CARDS);

        verify(playerMock).dealCards(NB_CARDS);
    }

    @Test
    void getCards_nominal_flow() {
        gamePlayerService.getCards(gameMock, RANDOM_PLAYER_UUID);

        verify(playerMock).getCards();
    }
}