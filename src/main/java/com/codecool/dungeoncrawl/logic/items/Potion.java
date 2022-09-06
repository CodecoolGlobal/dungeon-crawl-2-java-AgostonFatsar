package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Potion extends Item{

    public Potion(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "potion";
    }

    @Override
    public void act() {

       int currentHealth = getCell().getActor().getHealth();
        getCell().getActor().setHealth(currentHealth+2);

    }
}
