package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class LionItem extends Item{

    public LionItem (Cell cell){
        super(cell);

    }
    public String getTileName() {
        return "lion";
    }

    @Override
    public void act(GameMap map) {
        Player.getItems().add(this);
        Player.getItems().removeIf(item -> item.getTileName().equals("tranqgun"));
    }
}
