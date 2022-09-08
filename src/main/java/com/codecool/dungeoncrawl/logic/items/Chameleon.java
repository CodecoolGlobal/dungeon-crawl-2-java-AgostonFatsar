package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.Util;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class Chameleon extends Item{

    public Chameleon (Cell cell){
        super(cell);
        startCell = cell;

    }

    Cell startCell;
    public String getTileName() {
        return "chameleon";
    }

    @Override
    public void act(GameMap map) {
        map.getPlayer().getItems().add(this);
        map.setChameleon(null);
    }

    public void moveChameleon() {

        Cell originalCell = getCell();

        int randomNumber = Util.generateRandomInteger(4);

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
    public void checkCellAndMove(Cell nextCell){
        if(nextCell.getTileName().equals("wall") || nextCell.getTileName().equals("closedDoor") || nextCell.getItem() != null || nextCell.getActor() != null) {}
        else {
            getCell().setItem(null);
            nextCell.setItem(this);
            setCell(nextCell);
        }
    }
}
