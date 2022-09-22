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

public class LoadScreen {

    public void display(String message){
        Stage window  = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(400);
        Label label = new Label();
        javafx.scene.control.Button buttonSave = new javafx.scene.control.Button();
        buttonSave.setText("This is load");
        javafx.scene.control.Button buttonCancel = new javafx.scene.control.Button();
        buttonCancel.setText("This is load");


        VBox layout = new VBox();
        layout.getChildren().addAll(label, buttonSave, buttonCancel);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}
