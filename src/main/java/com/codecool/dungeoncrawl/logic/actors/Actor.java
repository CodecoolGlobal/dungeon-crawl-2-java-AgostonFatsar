package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.animals.LionItem;

import java.util.Objects;

public abstract class Actor implements Drawable{

    protected int health;

    private final int damage;

    protected boolean isAlive;


    public Actor(Cell cell, int damage) {
        cell.setActor(this);
        isAlive = true;
        this.damage = damage;

    }

    //GETTER AND SETTERS

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void increaseHealth(int increment){
        this.health += increment;
    }







    public boolean isAlive() {
        return isAlive;
    }


    // HELPER METHODS FOR PLAYER MOVEMENT

    public Cell getCell(String actorName, GameMap map) {
        Cell currentCell = null;
        for (Cell[] ListOfCells : map.getCells()) {
            for (Cell cell : ListOfCells) {
                if (cell.getActor() != null){
                    if (Objects.equals(cell.getActor().getTileName(), actorName)) {
                        currentCell = cell;
                    }
                }
            }
        }
        return currentCell;
    }

    void moveActorToNextCell(Cell nextCell, Cell currentCell) {
        currentCell.setActor(null);
        nextCell.setActor(this);
    }

    protected void confrontation(Cell nextCell) {

        nextCell.getActor().beingAttacked(this);
        if (!nextCell.getActor().isAlive) {
            nextCell.setActor(null);
        } else {
            this.beingAttacked(nextCell.getActor());
        }
    }

    public void beingAttacked(Actor attacker) {
        health -= attacker.getDamage();
        if (health < 1) {
            isAlive = false;
        }
    }

    protected boolean cannotStepOnNextCell(Cell nextCell) {
        return nextCell.getTileName().equals("wall") || nextCell.getTileName().equals("mud") || nextCell.getTileName().equals("road") || (nextCell.getActor() != null && nextCell.getActor().getTileName().equals("lion")) ||
                (nextCell.getTileName().equals("closedDoor") && (!checkIfPlayerHasItem("Successfully saved Chameleon!") ||
                        !checkIfPlayerHasItem("Successfully saved Panda!") || !checkIfPlayerHasItem("Successfully saved Lion!"))) ||
                (nextCell.getItem() != null && nextCell.getItem().getTileName().equals("lion") && !checkIfPlayerHasItem("Tranquilizer Gun")) ||
                (nextCell.getItem() != null && nextCell.getItem().getTileName().equals("Successfully saved Chameleon!") && !checkIfPlayerHasItem("chameleon")) ||
                (nextCell.getItem() != null && nextCell.getItem().getTileName().equals("Successfully saved Panda!") && !checkIfPlayerHasItem("panda")) ||
                (nextCell.getItem() != null && nextCell.getItem().getTileName().equals("Successfully saved Lion!") && !checkIfPlayerHasItem("lion"));
    }

    public boolean checkIfPlayerHasItem(String requiredKey) {
        boolean hasItem = false;
        Player player = (Player) this;
        for (Item item : player.getItems()) {
            if (item.getTileName().equals(requiredKey)) {
                hasItem = true;
            }
        }
        return hasItem;
    }

    protected static boolean nextCellIsEnemy(Cell nextCell) {
        return nextCell.getActor() != null && nextCell.getActor().getTileName().equals("skeleton") ||
                nextCell.getActor() != null && nextCell.getActor().getTileName().equals("auto") ||
                nextCell.getActor() != null && nextCell.getActor().getTileName().equals("lion");
    }





}
