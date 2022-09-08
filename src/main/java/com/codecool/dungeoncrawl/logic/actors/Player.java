package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;

public class Player extends Actor {

    private  ArrayList<Item> items = new ArrayList<Item>();
    private static int damageTaken = 2;
    public Player(Cell cell) {
        super(cell, damageTaken);
        super.setHealth(5);
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
}
