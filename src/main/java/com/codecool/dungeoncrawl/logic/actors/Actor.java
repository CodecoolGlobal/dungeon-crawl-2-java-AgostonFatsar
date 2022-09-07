package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Actor implements Drawable {
    private Cell cell;

    private int health = 10;


    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if(nextCell.getTileName().equals("wall") || nextCell.getActor() != null ||
                (nextCell.getTileName().equals("closedDoor") && (!checkPlayerItem("Successfully saved Chameleon!") || !checkPlayerItem("Successfully saved Panda!") || !checkPlayerItem("Successfully saved Lion!"))) ||
                (nextCell.getItem() != null && nextCell.getItem().getTileName().equals("lion") && !checkPlayerItem("tranqgun")) ||
                (nextCell.getItem() != null && nextCell.getItem().getTileName().equals("Successfully saved Chameleon!") && !checkPlayerItem("chameleon")) ||
                (nextCell.getItem() != null && nextCell.getItem().getTileName().equals("Successfully saved Panda!") && !checkPlayerItem("panda")) ||
                (nextCell.getItem() != null && nextCell.getItem().getTileName().equals("Successfully saved Lion!") && !checkPlayerItem("lion"))){}
        else {
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;}
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public boolean checkPlayerItem(String requiredKey){
        boolean hasItem = false;
        Player player = (Player) this;
        for (Item item : player.getItems()) {
            if (item.getTileName().equals(requiredKey)){
                hasItem = true;
            }
        }
        return hasItem;
    }


}
