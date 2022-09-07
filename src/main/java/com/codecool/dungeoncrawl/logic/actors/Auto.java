package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Auto extends Actor {
    private Cell cell = super.getCell();

    private static int damageTaken = 0;

    public Auto(Cell cell) {
        super(cell, damageTaken);
        startCell = cell;
        setHealth(1000);
    }

    int startX = 3;
    int startY = 1;

    Cell startCell;

    @Override
    public String getTileName() {
        return "auto";
    }


    public void moveCar() {
        Cell nextCell = cell.getNeighbor(1, 0);
        if (nextCell.getTileName().equals("wall")) {
            cell.setActor(null);
            cell = startCell.getNeighbor(-1,0);
        } else {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }


    }
}
