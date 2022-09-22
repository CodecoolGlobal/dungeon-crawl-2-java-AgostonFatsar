package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import java.util.List;

public interface PlayerDao {
    void add(PlayerModel player, String name);
    void update(PlayerModel player, String name);
    PlayerModel get(int id);
    int getId(String name);
    PlayerModel get(String name);
    List<PlayerModel> getAll();

    List<String> getAllPlayerNames();

}
