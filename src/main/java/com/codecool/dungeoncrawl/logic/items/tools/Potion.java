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
        getCell(this.getTileName(), map).getActor().increaseHealth(2);

        //lounge lizard - incident on lounge street
    }
}
