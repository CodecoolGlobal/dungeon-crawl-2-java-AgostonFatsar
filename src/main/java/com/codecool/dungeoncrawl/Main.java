package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.dao.PlayerDao;
import com.codecool.dungeoncrawl.dao.PlayerDaoJdbc;
import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.*;
//import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.items.Item;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.sql.DataSource;
import java.sql.Date;

public class Main extends Application {
    GameMap map = MapLoader.loadMap(1);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label monsterHealthLabel = new Label();
    Label inventoryLabel = new Label();
    Button pickUpButton = new Button("Pick Up");

    GameDatabaseManager gameDatabaseManager;

    String currentMap = "map1";



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        gameDatabaseManager = new GameDatabaseManager();
        gameDatabaseManager.setup();
        gameDatabaseManager.savePlayer( map.getPlayer());
        PlayerModel playerModel = gameDatabaseManager.getPlayerDao().get(map.getPlayer().getName());
        long millis=System.currentTimeMillis();
        java.sql.Date savedAt=new java.sql.Date(millis);
        gameDatabaseManager.saveGameState(currentMap,savedAt, playerModel);
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
        primaryStage.setScene(scene);
        map.fillUpActorsList();
        map.fillUpItemsList();
        refresh();
        pickUpButton.setFocusTraversable(false);
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();

        pickUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Cell playerCell = map.getPlayer().getCell();
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

       /* PlayerModel playerModel = gameDatabaseManager.getPlayerDao().get(map.getPlayer().getName());
        playerModel.setHp(map.getPlayer().getHealth());
        playerModel.setX(map.getPlayer().getX());
        playerModel.setY(map.getPlayer().getY());
        playerModel.setPlayerName(map.getPlayer().getName());
        gameDatabaseManager.getPlayerDao().update(gameDatabaseManager.getPlayerDao().get(map.getPlayer().getName()));
   */
    }



    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                refresh();
                break;
        }
    }

    private void refresh() {
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
            map.getPlayer().eraseItems();
            map = MapLoader.loadMap(mapNr);
        }
    }

    private void updateLabels() {
        healthLabel.setText("" + map.getPlayer().getHealth());
        String inventoryText = inventory(map.getPlayer().getCell().getNeighbor(0,-1).getTileName());
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
        if (map.getSkeleton() != null) {
            for (Trump trump : map.getTrumps()) {
                if (trump.isAlive()) {
                    trump.move(map.getPlayer());
                    trump.move(map.getPlayer());
                }
            }
        }
        if (map.getAuto() != null) map.getAuto().move();
        if (map.getAuto() != null) map.getAuto().move();
        if (map.getChameleon() != null) map.getChameleon().moveChameleon();
        if (map.getPanda() != null) map.getPanda().movePanda();
    }

    private void updatePlayerTable(PlayerModel playerModel) {
        playerModel.setHp(map.getPlayer().getHealth());
        playerModel.setX(map.getPlayer().getX());
        playerModel.setY(map.getPlayer().getY());
        playerModel.setPlayerName(map.getPlayer().getName());
        gameDatabaseManager.getPlayerDao().update(playerModel);
    }

    private void checkDoorPassing() {
        if (map.getPlayer().getCell().getTileName().equals("closedDoor")){
            map.getPlayer().getCell().setType(CellType.OPENDOOR);
        }
    }
}

