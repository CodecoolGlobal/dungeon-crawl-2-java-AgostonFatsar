package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import java.util.List;

public interface GameStateDao {
    void add(GameState state, int playerId);
    void update(GameState state, int playerId);
    GameState get(int id, PlayerModel playerModel);
    List<GameState> getAll();
}
