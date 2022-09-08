package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public class NextLevel extends Item{

    public NextLevel(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "nextlevel";
    }

    @Override
    public void act(GameMap map) {

        map.getPlayer().getItems().add(this);

    }
}
