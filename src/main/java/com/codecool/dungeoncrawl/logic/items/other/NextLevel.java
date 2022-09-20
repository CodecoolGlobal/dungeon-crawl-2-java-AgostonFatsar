package com.codecool.dungeoncrawl.logic.items.other;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Item;

public class NextLevel extends Item {

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
