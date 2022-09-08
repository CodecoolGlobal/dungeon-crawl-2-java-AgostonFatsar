package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {
    private Cell cell = super.getCell();
    private static int damageTaken = 5;

    public Skeleton(Cell cell) {
        super(cell, damageTaken);
        super.setHealth(15);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    public void moveEnemy(Player player) {
        int x = player.getX();
        int y = player.getY();
        Cell originalCell = cell;
        int OwnX = this.getX();
        int OwnY = this.getY();
        if (isAlive) {
            if (OwnX > x) {
                Cell nextCell = originalCell.getNeighbor(-1, 0);
                checkCellAndMove(nextCell, originalCell);
            } else if (OwnX < x) {
                Cell nextCell = originalCell.getNeighbor(+1, 0);
                checkCellAndMove(nextCell, originalCell);
            } else if (OwnY > y) {
                Cell nextCell = originalCell.getNeighbor(0, -1);
                checkCellAndMove(nextCell, originalCell);
            } else if (OwnY < y) {
                Cell nextCell = originalCell.getNeighbor(0, +1);
                checkCellAndMove(nextCell, originalCell);
            }
        }
    }

    public void checkCellAndMove(Cell nextCell, Cell originalCell) {
        if (nextCell.getTileName().equals("wall") ||
                (nextCell.getActor() != null && nextCell.getActor().getTileName().equals("skeleton"))) {
        } else {
            originalCell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }
}
