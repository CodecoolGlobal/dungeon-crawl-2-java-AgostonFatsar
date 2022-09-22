package com.codecool.dungeoncrawl.logic.items.animals;

import com.codecool.dungeoncrawl.Util;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Chameleon extends Item {

    public Chameleon (Cell cell){
        super(cell);

    }

    public String getTileName() {
        return "chameleon";
    }

    @Override
    public void act(GameMap map) {
        map.getPlayer().getItems().add(this);
        map.setChameleon(null);
    }

    public void moveChameleon(GameMap map) {

        Cell originalCell = getCell(this.getTileName(), map);

        int randomNumber = Util.generateRandomInteger(5);

        if(randomNumber == 0) {
            Cell nextCell = originalCell.getNeighbor(map, -1, 0);
            checkCellAndMove(nextCell, map);
        }else if(randomNumber == 1) {
            Cell nextCell = originalCell.getNeighbor(map, +1, 0);
            checkCellAndMove(nextCell, map);
        }else if(randomNumber == 2) {
            Cell nextCell = originalCell.getNeighbor(map, 0, -1);
            checkCellAndMove(nextCell, map);
        }else if(randomNumber == 3) {
            Cell nextCell = originalCell.getNeighbor(map, 0, +1);
            checkCellAndMove(nextCell, map);
        }




    }
    public void checkCellAndMove(Cell nextCell, GameMap map){
        if(nextCell.getTileName().equals("wall") || nextCell.getTileName().equals("closedDoor") || nextCell.getItem() != null || nextCell.getActor() != null) {}
        else {
            getCell(this.getTileName(), map).setItem(null);
            nextCell.setItem(this);
        }
    }
}
