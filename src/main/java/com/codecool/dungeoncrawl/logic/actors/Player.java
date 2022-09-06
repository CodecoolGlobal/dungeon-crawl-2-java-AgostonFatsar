package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;

public class Player extends Actor {

    private static ArrayList<Item> items = new ArrayList<Item>();
    public Player(Cell cell) {
        super(cell);
    }



    public String getTileName() {
        return "player";
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static void setItems(ArrayList<Item> items) {
        Player.items = items;
    }
}
