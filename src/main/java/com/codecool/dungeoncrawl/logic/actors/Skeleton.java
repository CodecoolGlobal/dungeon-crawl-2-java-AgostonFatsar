package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Util;
import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {
    private Cell cell = super.getCell();
    private static int damage = 2;



    public Skeleton(Cell cell) {
        super(cell, damage);
        super.setHealth(10);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    /*public void moveEnemyHorizontal(Player player) {
        int x = player.getX();
        int y = player.getY();
        Cell originalCell = cell;
        int OwnX = this.getX();
        int OwnY = this.getY();
        if (isAlive) {
                if (OwnX > x) {
                    Cell nextCell = originalCell.getNeighbor(-1, 0);
                    checkCellAndMove(nextCell, originalCell);
                }else if (OwnX < x) {
                    Cell nextCell = originalCell.getNeighbor(+1, 0);
                    checkCellAndMove(nextCell, originalCell);
                }else if (OwnX == x) {
                    Cell nextCell = originalCell.getNeighbor(0, 0);
                    checkCellAndMove(nextCell, originalCell);
                }
            }
        }*/

    public void checkCellAndMove(Cell nextCell) {

        if (nextCell.getActor() != null && nextCell.getActor().getTileName().equals("player")){
            confrontation(nextCell);
        } else if (nextCell.getTileName().equals("wall") || (nextCell.getTileName().equals("closedDoor")) || nextCell.getItem() != null ||
                (nextCell.getActor() != null && nextCell.getActor().getTileName().equals("skeleton")) ||
                (nextCell.getActor() != null && nextCell.getActor().getTileName().equals("lion")) ||
                (nextCell.getActor() != null && nextCell.getActor().getTileName().equals("auto"))) {
        } else {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public void moveSkeleton() {
        Cell originalCell = getCell();

        int randomNumber = Util.generateRandomInteger(5);

        if(randomNumber == 0) {
            Cell nextCell = originalCell.getNeighbor(-1, 0);
            checkCellAndMove(nextCell);
        }else if(randomNumber == 1) {
            Cell nextCell = originalCell.getNeighbor(+1, 0);
            checkCellAndMove(nextCell);
        }else if(randomNumber == 2) {
            Cell nextCell = originalCell.getNeighbor(0, -1);
            checkCellAndMove(nextCell);
        }else if(randomNumber == 3) {
            Cell nextCell = originalCell.getNeighbor(0, +1);
            checkCellAndMove(nextCell);
        }
    }
}
