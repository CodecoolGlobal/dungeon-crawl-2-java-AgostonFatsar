package com.codecool.dungeoncrawl.logic.items.tools;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

public class TranquilizerGun extends Item {
    public TranquilizerGun(Cell cell) {
            super(cell);
        }

    public String getTileName() {
            return "Tranquilizer Gun";
        }

    @Override
    public void act(GameMap map) {
        map.switchLion(this);
    }
}

