package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class LionCage extends Item{

    public LionCage (Cell cell){
        super(cell);

    }
    public String getTileName() {
        return "Successfully saved Lion!";
    }

    @Override
    public void act(GameMap map) {
        Player.getItems().add(this);
        Player.getItems().removeIf(item -> item.getTileName().equals("lion"));
    }
}
