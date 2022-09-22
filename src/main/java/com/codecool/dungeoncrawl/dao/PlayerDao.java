package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import java.util.List;

public interface PlayerDao {
    void add(PlayerModel player);
    void update(PlayerModel player);
    PlayerModel get(int id);
    int getId(String name);
    PlayerModel get(String name);
    List<PlayerModel> getAll();

}
