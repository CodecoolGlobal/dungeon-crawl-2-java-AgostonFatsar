package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.items.Item;

public abstract class Actor implements Drawable{
    protected Cell cell;

    protected int health;

    private final int damage;

    protected boolean isAlive;


    public Actor(Cell cell, int damage) {
        this.cell = cell;
        this.cell.setActor(this);
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

    public boolean isAlive() {
        return isAlive;
    }


    // HELPER METHODS FOR PLAYER MOVEMENT

    void moveActorToNextCell(Cell nextCell) {
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
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
