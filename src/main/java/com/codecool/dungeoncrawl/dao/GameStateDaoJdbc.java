package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {

    private DataSource dataSource;
    private  PlayerDao playerDao;


    public GameStateDaoJdbc(DataSource dataSource,PlayerDao playerDao) {
        this.dataSource = dataSource;
        this.playerDao = playerDao;

    }

    @Override
    public void add(GameState state, int playerId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (current_map, saved_at, player_id) VALUES (?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, state.getCurrentMap());
            st.setDate(2, state.getSavedAt());
            st.setInt(3, playerId);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            state.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(GameState state, int playerId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE game_state SET current_map = ?, saved_at = ?, player_id = ? WHERE player_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, state.getCurrentMap());
            statement.setDate(2, state.getSavedAt());
            statement.setInt(3, playerId);
            statement.setInt(4, playerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameState get(int id, PlayerModel playerModel) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT current_map, saved_at, player_id FROM game_state WHERE player_id= ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            GameState gameState = new GameState(rs.getString(1), rs.getDate(2), playerModel);
            return gameState;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading player with id: " + id, e);
        }
    }

    @Override
    public List<GameState> getAll() {
        return null;
    }
}
