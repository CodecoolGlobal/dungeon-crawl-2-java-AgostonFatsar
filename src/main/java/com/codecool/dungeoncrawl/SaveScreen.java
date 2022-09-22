package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class SaveScreen {

    Scene LoadScreen;
    GameDatabaseManager gameDatabaseManager;
    GameState gameState;
    PlayerModel playerModel;

    int playerId = 0;
    public void display(String title, String message, GameMap map){
        Stage window  = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);
        Label label = new Label();
        label.setText(message);
        javafx.scene.control.Button buttonSave = new javafx.scene.control.Button();
        buttonSave.setText("Save");
        javafx.scene.control.Button buttonCancel = new javafx.scene.control.Button();
        buttonCancel.setText("Cancel");
        javafx.scene.control.Button buttonLoad = new javafx.scene.control.Button();
        buttonCancel.setText("Load");
        TextField playerName = new TextField();

        buttonLoad.setOnAction(e -> window.close());
        buttonCancel.setOnAction(e -> window.close());
        buttonSave.setOnAction(e -> {
            try {
                databaseHandler(map, playerName);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        VBox layout = new VBox();
        layout.getChildren().addAll(label, playerName, buttonSave, buttonCancel);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

//    public EventHandler<ActionEvent> loadMenu(Stage window){
//        window.setScene(LoadScreen);
//        return LoadScreen.display("Message");
//    }

    public void databaseHandler(GameMap map, TextField playerName) throws SQLException {
        String playerNameString = playerName.getText();
        gameDatabaseManager = new GameDatabaseManager();
        gameDatabaseManager.setup();
        boolean existingPlayerName = false;

        List<String> playerNameList = gameDatabaseManager.getPlayerDao().getAllPlayerNames();
        for ( String name: playerNameList){
            if (name.equals(playerNameString)){
                existingPlayerName = true;
                break;
            }
        }
        if(!existingPlayerName) {
            initializeDatabaseConnection(map, playerNameString);
        }
        else{
            PlayerModel playerModel = gameDatabaseManager.getPlayerDao().get(playerNameString);
            updatePlayerTable(playerModel, map, playerName);
            playerModel = gameDatabaseManager.getPlayerDao().get(playerNameString);
            gameState = gameDatabaseManager.getGameStateDao().get(gameDatabaseManager.getPlayerDao().getId(playerNameString), playerModel);
            updateGameStateTable(map, playerModel);
        }
    }

    private void updatePlayerTable(PlayerModel playerModel, GameMap map, TextField playerName) {
        String playerNameString = playerName.getText();
        playerModel.setHp(map.getPlayer().getHealth());
        playerModel.setX(0);
        playerModel.setY(0);
        playerModel.setPlayerName(playerNameString);
        gameDatabaseManager.getPlayerDao().update(playerModel, playerNameString);
    }

    private void initializeDatabaseConnection(GameMap map, String playerNameString ) throws SQLException {
        gameDatabaseManager.setup();
        gameDatabaseManager.savePlayer( map.getPlayer(), playerNameString);
        playerModel = gameDatabaseManager.getPlayerDao().get(playerNameString);
        // playerModel.setPlayerName(playerNameString);
        // gameDatabaseManager.getPlayerDao().update(playerModel, playerNameString);
        playerId = gameDatabaseManager.getPlayerDao().getId(playerNameString);
        long millis=System.currentTimeMillis();
        Date savedAt=new Date(millis);
        gameDatabaseManager.saveGameState(map, savedAt, playerModel, playerId);
    }

    private void updateGameStateTable(GameMap map, PlayerModel playerModel) {
        gameState.setPlayer(playerModel);
        long millis = System.currentTimeMillis();
        java.sql.Date savedAt = new java.sql.Date(millis);
        gameState.setSavedAt(savedAt);
        gameState.setCurrentMap(new Gson().toJson(map));
        gameDatabaseManager.getGameStateDao().update(gameState, playerId);
    }
}
