package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class Quit extends Item{

    public Quit(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "quit";
    }

    @Override
    public void act(GameMap map) {
        Player.getItems().add(this);
    }
}
