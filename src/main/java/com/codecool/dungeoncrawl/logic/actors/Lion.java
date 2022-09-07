package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Lion extends Actor{

    private Cell cell = super.getCell();

    private static int damageTaken = 10;

    public Lion(Cell cell){
        super(cell, damageTaken);
        setHealth(1000);
    }

    @Override
    public String getTileName() {
        return "lion";
    }




}