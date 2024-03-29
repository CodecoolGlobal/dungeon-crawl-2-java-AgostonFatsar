package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Trump extends Actor {
    private Cell cell = super.getCell();
    private static int damage = 2;



    public Trump(Cell cell) {
        super(cell, damage);
        super.setHealth(10);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }



    public void move(Player player) {
        int y = player.getY();
        Cell originalCell = getCell();
        int OwnY = this.getY();
        int x = player.getX();
        int OwnX = this.getX();
        if (isAlive) {
            checkNextCell(OwnX > x, originalCell, -1, 0);
            checkNextCell(OwnX < x, originalCell, +1, 0);
            checkNextCell(OwnY > y, originalCell, 0, -1);
            checkNextCell(OwnY < y, originalCell, 0, +1);
        }
    }

    private void checkNextCell(boolean OwnX, Cell originalCell, int dx, int dy) {
        if (OwnX) {
            Cell nextCell = originalCell.getNeighbor(dx, dy);
            checkCellAndMove(nextCell);
        }
    }

    public void checkCellAndMove(Cell nextCell) {

        if (nextCell.getActor() != null && nextCell.getActor().getTileName().equals("player")){
            confrontation(nextCell);
        } else if (nextCell.getTileName().equals("wall") || (nextCell.getTileName().equals("closedDoor")) || nextCell.getItem() != null ||
                (nextCell.getActor() != null && nextCell.getActor().getTileName().equals("skeleton")) ||
                (nextCell.getActor() != null && nextCell.getActor().getTileName().equals("lion")) ||
                (nextCell.getActor() != null && nextCell.getActor().getTileName().equals("auto"))) {
        } else {
            getCell().setActor(null);
            nextCell.setActor(this);
            setCell(nextCell);
        }
    }


}
