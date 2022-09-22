package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;

public class GameDatabaseManager {
    private  PlayerDao playerDao;

    private GameStateDao gameStateDao;

    public  void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource,playerDao);
    }

    public  void savePlayer(Player player, String name) {
        PlayerModel model = new PlayerModel(player);
        playerDao.add(model, name);
    }

    public  void saveGameState(GameMap currentMap, Date savedAt, PlayerModel player, int playerId) {
        String serializedMap = new Gson().toJson(currentMap);
        GameState model = new GameState(serializedMap, savedAt, player);
        gameStateDao.add(model, playerId);
    }

    public GameMap loadMap(int id) {
        PlayerModel playerModel = playerDao.get(id);
        GameState gameState = gameStateDao.get(id, playerModel);
        String serializedMap = gameState.getCurrentMap();
        return new Gson().fromJson(serializedMap, GameMap.class);
    }

    private  DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("PSQL_DB_NAME");
        String user = System.getenv("PSQL_USER_NAME");
        String password = System.getenv("PSQL_PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }

    public int getId(String playerName) {
        int Id = 0;
        for (PlayerModel playermodel : playerDao.getAll()) {
            if (playermodel.getPlayerName().equals(playerName)){
                Id = playermodel.getId();
            }
        }
        return Id;
    }

    public PlayerDao getPlayerDao() {
        return playerDao;
    }

    public GameStateDao getGameStateDao() {
        return gameStateDao;
    }
}
