package com.codecool.dungeoncrawl.logic.items.animals;

import com.codecool.dungeoncrawl.Util;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Panda extends Item {

    public Panda (Cell cell){
        super(cell);

    }
    public String getTileName() {
        return "panda";
    }

    @Override
    public void act(GameMap map) {
        map.getPlayer().getItems().add(this);
        map.setPanda(null);
    }

    public void movePanda(GameMap map) {

        Cell originalCell  = getCell(this.getTileName(), map);

        int randomNumber = Util.generateRandomInteger(3);

        int[] list = new int[3];
        list[0] = 1;
        list[1] = -1;
        list[2] = 0;

        Cell nextCell = originalCell.getNeighbor(map, 0, list[randomNumber]);
        checkCellAndMove(nextCell, map);




    }
    public void checkCellAndMove(Cell nextCell, GameMap map){
        if(nextCell.getTileName().equals("wall") || nextCell.getTileName().equals("closedDoor") || nextCell.getItem() != null || nextCell.getActor() != null) {}
        else {
            getCell(this.getTileName(), map).setItem(null);
            nextCell.setItem(this);
            }

        }
    }
