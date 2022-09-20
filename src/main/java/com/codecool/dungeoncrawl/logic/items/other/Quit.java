package com.codecool.dungeoncrawl.logic.items.other;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Quit extends Item {

    public Quit(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "quit";
    }

    @Override
    public void act(GameMap map) {
        map.getPlayer().getItems().add(this);
    }
}
