package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.dao.PlayerDao;
import com.codecool.dungeoncrawl.dao.PlayerDaoJdbc;
import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.*;
//import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.sql.DataSource;
import java.sql.Date;

public class Main extends Application {
    Scene SaveScene;
    Stage window;
    Stage saveScreen;
    GameMap map = MapLoader.loadMap(1);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label inventoryLabel = new Label();
    Button pickUpButton = new Button("Pick Up");

    GameDatabaseManager gameDatabaseManager;
    GameState gameState;
    PlayerModel playerModel;

    String currentMap = "map1";
    int playerId = 0;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        gameDatabaseManager = new GameDatabaseManager();
        gameDatabaseManager.setup();
        gameDatabaseManager.savePlayer( map.getPlayer());
        playerModel = gameDatabaseManager.getPlayerDao().get(map.getPlayer().getName());
        playerId = gameDatabaseManager.getPlayerDao().getId(map.getPlayer().getName());
        long millis=System.currentTimeMillis();
        java.sql.Date savedAt=new java.sql.Date(millis);
        gameDatabaseManager.saveGameState(map, savedAt, playerModel, playerId);
        window = primaryStage;
        Scene scene = createScene();

        pickUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Cell playerCell = map.getPlayer().getCell(map.getPlayer().getTileName(), map);
                if (playerCell.getItem() != null){
                    playerCell.getItem().act(map);

                    if (!(playerCell.getItem().getTileName().equals("Successfully saved Lion!")) &&
                            !(playerCell.getItem().getTileName().equals("Successfully saved Chameleon!")) &&
                            !(playerCell.getItem().getTileName().equals("Successfully saved Panda!"))) {
                        playerCell.setItem(null);
                    }
                }
                refresh();
            }
        });
        scene.setOnKeyPressed(this::onKeyPressed);
    }

    private Scene createScene() {

        GridPane ui = new GridPane();
        ui.setPrefWidth(360);

        ui.setPadding(new Insets(10));

        ui.add(new Label("Player Health: "), 0, 0);

        ui.add(healthLabel, 1, 0);
        ui.add(pickUpButton, 0, 3);
        ui.add(inventoryLabel, 0, 4);

        BorderPane borderPane = new BorderPane();


        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        window.setScene(scene);

        refresh();
        pickUpButton.setFocusTraversable(false);
        window.setTitle("Dungeon Crawl");
        window.show();
        return scene;
    }



    private void onKeyPressed(KeyEvent keyEvent) {
        KeyCombination keyCombination = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1, map);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1, map);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0, map);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0, map);
                refresh();
                break;
            case CONTROL:
                System.out.println("Hello");
                window.setScene(SaveScene);
                SaveScreen.display("SaveScreen", "Provide a name: ");
                break;
        }
    }

    private void refresh() {
        map.fillUpTrumps(map);
        moveActorsAndItems();
        drawCanvas();
        updateLabels();
        checkDoorPassing();
        checkQuit();
        checkIfNewMapNeeded(!(map.getPlayer().isAlive()), 0);
        checkIfNewMapNeeded(map.getPlayer().checkIfPlayerHasItem("newgame"), 1);
        checkIfNewMapNeeded(map.getPlayer().checkIfPlayerHasItem("nextlevel"), 2);
        PlayerModel playerModel = gameDatabaseManager.getPlayerDao().get(map.getPlayer().getName());
        updatePlayerTable(playerModel);
        gameState = gameDatabaseManager.getGameStateDao().get(gameDatabaseManager.getPlayerDao().getId(map.getPlayer().getName()), playerModel);
        gameState.setPlayer(playerModel);
        long millis=System.currentTimeMillis();
        java.sql.Date savedAt=new java.sql.Date(millis);
        gameState.setSavedAt(savedAt);
        gameDatabaseManager.getGameStateDao().update(gameState,playerId);

        /*GameState gameState = gameDatabaseManager.getGameStateDao().get(gameDatabaseManager.getPlayerDao().getId(map.getPlayer().getName()), playerModel); // TODO: Fix it, currently not working for different player ID-s
        gameState.setPlayer(playerModel);
        long millis=System.currentTimeMillis();
        java.sql.Date savedAt=new java.sql.Date(millis);
        gameState.setSavedAt(savedAt);*/
        /*playerModel.setHp(map.getPlayer().getHealth());
        playerModel.setX(map.getPlayer().getX());
        playerModel.setY(map.getPlayer().getY());
        playerModel.setPlayerName(map.getPlayer().getName());
        gameDatabaseManager.getPlayerDao().update(playerModel);*/



    }

    private String inventory(String cellType){
        StringBuilder sb = new StringBuilder()
                .append("\n" + "Inventory:");
        if (map.getPlayer().getItems() != null){
            for (Item item : map.getPlayer().getItems()){
                sb.append("\n" + item.getTileName());
            }
        }
        if (cellType.equals("closedDoor")){
            sb.append("\n");
            sb.append("\n");
            sb.append("\n");
            sb.append("Objectives: \n");
            sb.append("Save Panda! \n");
            sb.append("Save Chameleon! \n");
            sb.append("Save Lion! \n");
        }

        return sb.toString();
    }

    private void checkQuit() {
        if (map.getPlayer().checkIfPlayerHasItem("quit")) {
            System.exit(1);
        }
    }

    private void checkIfNewMapNeeded(Boolean hasItem, int mapNr) {
        if (hasItem) {

            String mapName = "map" + mapNr;
            gameState = gameDatabaseManager.getGameStateDao().get(gameDatabaseManager.getPlayerDao().getId(map.getPlayer().getName()), playerModel); // TODO: Fix it, currently not working for different player ID-s
            gameState.setCurrentMap(mapName);
            gameDatabaseManager.getGameStateDao().update(gameState,playerId);

            map.getPlayer().eraseItems();
            map = MapLoader.loadMap(mapNr);
        }
    }

    private void updateLabels() {
        healthLabel.setText("" + map.getPlayer().getHealth());
        String inventoryText = inventory(map.getPlayer().getCell(map.getPlayer().getTileName(), map).getNeighbor(map, 0,-1).getTileName());
        inventoryLabel.setText("" + inventoryText);
    }

    private void drawCanvas() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
    }

    private void moveActorsAndItems() {
        for (Trump trump : map.getTrumps()) {
            if (trump.isAlive()) {
                trump.move(map.getPlayer(), map);
            }
        }
        if (map.getAuto() != null) map.getAuto().move(map);
        if (map.getAuto() != null) map.getAuto().move(map);
        if (map.getChameleon() != null) map.getChameleon().moveChameleon(map);
        if (map.getPanda() != null) map.getPanda().movePanda(map);
    }

    private void updatePlayerTable(PlayerModel playerModel) {
        playerModel.setHp(map.getPlayer().getHealth());
        playerModel.setX(0);
        playerModel.setY(0);
        playerModel.setPlayerName(map.getPlayer().getName());
        gameDatabaseManager.getPlayerDao().update(playerModel);
    }

    private void checkDoorPassing() {
        if (map.getPlayer().getCell(map.getPlayer().getTileName(), map).getTileName().equals("closedDoor")){
            map.getPlayer().getCell(map.getPlayer().getTileName(), map).setType(CellType.OPENDOOR);
        }
    }
}

