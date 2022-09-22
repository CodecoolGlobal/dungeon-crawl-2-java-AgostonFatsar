package com.codecool.dungeoncrawl;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.*;

import java.awt.*;

public class SaveScreen {
    public static void display(String title, String message){
        Stage window  = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(200);
        Label label = new Label();
        label.setText("message");
        javafx.scene.control.Button buttonSave = new javafx.scene.control.Button();
        buttonSave.setText("Save");
        javafx.scene.control.Button buttonCancel = new javafx.scene.control.Button();
        buttonCancel.setText("Cancel");

        buttonCancel.setOnAction(e -> window.close());

        VBox layout = new VBox();
        layout.getChildren().addAll(buttonSave, buttonCancel);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
