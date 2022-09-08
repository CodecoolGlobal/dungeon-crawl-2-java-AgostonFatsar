package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class NewGame extends Item{

    public NewGame(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "newgame";
    }

    @Override
    public void act(GameMap map) {

        Player.getItems().add(this);

    }
}
