package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Potion extends Item{

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
