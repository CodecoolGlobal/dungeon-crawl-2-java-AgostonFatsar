package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;

public class Player extends Actor {

    private  ArrayList<Item> items = new ArrayList<Item>();
    private static int damage = 5;

    private String name = "testketto";
    public Player(Cell cell) {
        super(cell, damage);
        super.setHealth(35);
    }

    public void move(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);
        if (super.nextCellIsEnemy(nextCell))
            confrontation(nextCell);
        else {
            if (!super.cannotStepOnNextCell(nextCell)) {
                super.moveActorToNextCell(nextCell);
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
