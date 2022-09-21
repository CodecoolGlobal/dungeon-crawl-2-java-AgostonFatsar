package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.Currency;

public class Auto extends Actor{

    private static int damage = 100;

    public Auto(Cell cell) {
        super(cell, damage);
        setHealth(1000);
    }

    int startX = 1;
    int startY = 8;



    @Override
    public String getTileName() {
        return "auto";
    }


    public void move(GameMap map) {
        Cell currentCell = getCell(getTileName(), map);
        Cell nextCell = currentCell.getNeighbor(1, 0);
        if (nextCellIsPlayer(nextCell)){
            confrontation(nextCell);
        }
        else if(nextCell.getTileName().equals("wall")) {
            teleportToStartCell(currentCell, map);
        } else {
            moveActorToNextCell(nextCell, currentCell);
        }
    }

    private void teleportToStartCell(Cell currentCell, GameMap map) {
        Cell startCell = map.getCell(startX, startY);
        currentCell.setActor(null);
        currentCell = startCell;
        currentCell.setActor(this);
    }

    private static boolean nextCellIsPlayer(Cell nextCell) {
        return nextCell.getActor() != null && nextCell.getActor().getTileName().equals("player");
    }
}
