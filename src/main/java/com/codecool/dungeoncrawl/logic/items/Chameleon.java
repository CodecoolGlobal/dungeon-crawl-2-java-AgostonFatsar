package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class Chameleon extends Item{

    public Chameleon (Cell cell){
        super(cell);

    }
    public String getTileName() {
        return "chameleon";
    }

    @Override
    public void act(GameMap map) {
        map.getPlayer().getItems().add(this);
    }
}
