package com.rros.logmeinbasicdeck.service;

import com.rros.logmeinbasicdeck.model.Card;
import com.rros.logmeinbasicdeck.model.Game;
import com.rros.logmeinbasicdeck.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GamePlayerServiceImpl implements GamePlayerService {

    private final Map<UUID, Player> players;

    @Autowired
    public GamePlayerServiceImpl() {
        this(new ConcurrentHashMap<>());
    }

    GamePlayerServiceImpl(Map<UUID, Player> players) {
        this.players = players;
    }

    @Override
    public UUID create(Game game) {
        Player player = new Player(game);
        Player result = players.put(player.uuid(), player);
        if (result != null) {
            // TODO this pattern is incorrect (no transaction, the original player has been removed)
            throw new IllegalStateException("Could not add player");
        }
        return player.uuid();
    }

    @Override
    public void delete(Game game, UUID playerId) {
        Player player = players.remove(playerId);
        if (player == null) {
            throw new IllegalArgumentException("Could not remove player");
        }
        game.removePlayer(player);
    }

    @Override
    public void dealCards(Game game, UUID playerId, int nbCards) {
        Player player = Objects.requireNonNull(players.get(playerId));
        player.dealCards(nbCards);
    }

    @Override
    public List<Card> getCards(Game game, UUID playerId) {
        Player player = Objects.requireNonNull(players.get(playerId));
        return player.getCards();
    }

    @Override
    public List<Player> get(Game game) {
        return game.getPlayers();
    }
}
