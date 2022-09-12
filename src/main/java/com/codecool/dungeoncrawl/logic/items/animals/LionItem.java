package com.codecool.dungeoncrawl.logic.items.animals;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

public class LionItem extends Item {

    public LionItem (Cell cell){
        super(cell);

    }
    public String getTileName() {
        return "lion";
    }

    @Override
    public void act(GameMap map) {
        map.getPlayer().getItems().add(this);
        map.getPlayer().getItems().removeIf(item -> item.getTileName().equals("Tranquilizer Gun"));
    }
}
