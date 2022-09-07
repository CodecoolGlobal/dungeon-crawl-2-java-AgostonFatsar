package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Lion extends Actor{

    private Cell cell = super.getCell();

    public Lion(Cell cell){
        super(cell);
    }

    @Override
    public String getTileName() {
        return "lion";
    }




}