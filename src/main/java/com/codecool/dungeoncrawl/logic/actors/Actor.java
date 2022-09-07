package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.items.Item;

public abstract class Actor implements Drawable {
    private Cell cell;

    protected int health;
    private int damageTaken;

    protected boolean isAlive;

    public Actor(Cell cell, int damageTaken) {
        this.cell = cell;
        this.cell.setActor(this);
        isAlive = true;
        this.damageTaken = damageTaken;

    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getTileName().equals("wall") || (nextCell.getActor() != null && nextCell.getActor().getTileName().equals("lion")) ||
                (nextCell.getTileName().equals("closedDoor") && (!checkPlayerItem("Successfully saved Chameleon!") ||
                !checkPlayerItem("Successfully saved Panda!") || !checkPlayerItem("Successfully saved Lion!"))) ||
                (nextCell.getItem() != null && nextCell.getItem().getTileName().equals("lion") && !checkPlayerItem("tranqgun")) ||
                (nextCell.getItem() != null && nextCell.getItem().getTileName().equals("Successfully saved Chameleon!") && !checkPlayerItem("chameleon")) ||
                (nextCell.getItem() != null && nextCell.getItem().getTileName().equals("Successfully saved Panda!") && !checkPlayerItem("panda")) ||
                (nextCell.getItem() != null && nextCell.getItem().getTileName().equals("Successfully saved Lion!") && !checkPlayerItem("lion"))) {
        } else if (nextCell.getActor() != null && nextCell.getActor().getTileName().equals("skeleton"))
            confrontation(nextCell);
        else {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    private void confrontation(Cell nextCell) {

        nextCell.getActor().beingAttacked();
        if (!nextCell.getActor().isAlive) {
            nextCell.setActor(null);
        } else {
            this.beingAttacked();
        }
/*        int enemyHealth = enemy.getHealth();
        health -= 2;
        enemy.setHealth(enemyHealth - 5);
        enemyHealth = enemy.getHealth();
        if (enemyHealth < 1) {
            enemy.getCell().setActor(null);
        } else if (health < 1) this.getCell().setActor(null);*/
    }

    public void beingAttacked() {
        health -= damageTaken;
        if (health < 1) {
            isAlive = false;
        }
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

    public boolean checkPlayerItem(String requiredKey) {
        boolean hasItem = false;
        Player player = (Player) this;
        for (Item item : player.getItems()) {
            if (item.getTileName().equals(requiredKey)) {
                hasItem = true;
            }
        }
        return hasItem;
    }


}
