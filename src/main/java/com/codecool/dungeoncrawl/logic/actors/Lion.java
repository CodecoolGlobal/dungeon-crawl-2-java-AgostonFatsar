package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Lion extends Actor{

    private Cell cell = super.getCell();

    private static int damage = 3;

    public Lion(Cell cell){
        super(cell, damage);
        setHealth(10000);
    }

    @Override
    public String getTileName() {
        return "lion";
    }


}