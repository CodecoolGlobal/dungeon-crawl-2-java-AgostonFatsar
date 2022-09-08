package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.Util;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class Panda extends Item{

    public Panda (Cell cell){
        super(cell);

    }
    public String getTileName() {
        return "panda";
    }

    @Override
    public void act(GameMap map) {
        Player.getItems().add(this);
    }

    public void movePanda() {

        Cell originalCell = getCell();

        int randomNumber = Util.generateRandomInteger(2);

        int[] list = new int[2];
        list[0] = 1;
        list[1] = -1;

        Cell nextCell = originalCell.getNeighbor(0, list[randomNumber]);
        checkCellAndMove(nextCell);




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
