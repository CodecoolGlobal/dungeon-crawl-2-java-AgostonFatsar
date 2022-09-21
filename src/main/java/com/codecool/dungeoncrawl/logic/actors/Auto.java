package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Auto extends Actor{
    private Cell cell = super.getCell();

    private static int damage = 100;

    public Auto(Cell cell) {
        super(cell, damage);
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


    public void move(GameMap map) {
        Cell nextCell = cell.getNeighbor(map, 1, 0);
        if (nextCellIsPlayer(nextCell)){
            confrontation(nextCell);
        }
        else if (nextCell.getTileName().equals("wall")) {
            teleportToStartCell(map);
        } else {
            moveActorToNextCell(nextCell);
        }
    }

    public void moveActorToNextCell(Cell nextCell){
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    private void teleportToStartCell(GameMap map) {
        cell.setActor(null);
        cell = startCell.getNeighbor(map, -1,0);
    }

    private static boolean nextCellIsPlayer(Cell nextCell) {
        return nextCell.getActor() != null && nextCell.getActor().getTileName().equals("player");
    }
}
