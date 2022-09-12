package com.codecool.dungeoncrawl.logic.items.tools;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Potion extends Item {

    public Potion(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "potion";
    }

    @Override
    public void act(GameMap map) {
        cell.getActor().increaseHealth(2);
    }
}
