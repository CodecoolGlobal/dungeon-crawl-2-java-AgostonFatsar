package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Trump extends Actor {
    private static int damage = 2;



    public Trump(Cell cell) {
        super(cell, damage);
        super.setHealth(10);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }



    public void move(Player player, GameMap map) {
        int playerX = player.getCell(player.getTileName(), map).getX();
        int playerY = player.getCell(player.getTileName(), map).getY();
        int OwnX = getCell(this.getTileName(), map).getX();
        int OwnY = getCell(this.getTileName(), map).getY();
        Cell currentCell = getCell(player.getTileName(), map);
        if (isAlive) {
            checkNextCell(OwnX > playerX, currentCell, -1, 0, currentCell, map);
            checkNextCell(OwnX < playerY, currentCell, +1, 0, currentCell, map);
            checkNextCell(OwnY > playerX, currentCell, 0, -1, currentCell, map);
            checkNextCell(OwnY < playerY, currentCell, 0, +1, currentCell, map);
        }
    }

    private void checkNextCell(boolean OwnX, Cell originalCell, int dx, int dy, Cell currentCell, GameMap map) {
        if (OwnX) {
            Cell nextCell = originalCell.getNeighbor(map, dx, dy);
            checkCellAndMove(nextCell, currentCell);
        }
    }

    public void checkCellAndMove(Cell nextCell, Cell currentCell) {

        if (nextCell.getActor() != null && nextCell.getActor().getTileName().equals("player")){
            confrontation(nextCell);
        } else if (nextCell.getTileName().equals("wall") || (nextCell.getTileName().equals("closedDoor")) || nextCell.getItem() != null ||
                (nextCell.getActor() != null && nextCell.getActor().getTileName().equals("skeleton")) ||
                (nextCell.getActor() != null && nextCell.getActor().getTileName().equals("lion")) ||
                (nextCell.getActor() != null && nextCell.getActor().getTileName().equals("auto"))) {
        } else {
            moveActorToNextCell(nextCell, currentCell);
        }
    }


}
