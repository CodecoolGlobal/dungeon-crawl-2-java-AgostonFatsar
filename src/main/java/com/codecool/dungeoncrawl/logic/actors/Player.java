package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;

public class Player extends Actor {

    private  ArrayList<Item> items = new ArrayList<Item>();
    private static int damage = 5;

    private String name = "test3";
    public Player(Cell cell) {
        super(cell, damage);
        super.setHealth(35);
    }



    //move should be abstract



    public void move(int dx, int dy, GameMap map) {
        Cell currenCell = getCell(getTileName(), map);
        Cell nextCell = currenCell.getNeighbor(map, dx, dy);
        if (super.nextCellIsEnemy(nextCell))
            confrontation(nextCell);
        else {
            if (!super.cannotStepOnNextCell(nextCell)) {
                super.moveActorToNextCell(nextCell, currenCell);
            }
        }
    }



    public String getTileName() {
        return "player";
    }

    public  ArrayList<Item> getItems() {
        return items;
    }

    public  void setItems(ArrayList<Item> items) {
    }

    public  void eraseItems() {
        items.clear();
    }

    public String getName() {
        return name;
    }
}
