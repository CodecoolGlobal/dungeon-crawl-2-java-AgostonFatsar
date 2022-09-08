package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.Chameleon;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Panda;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private Skeleton skeleton;
    private Panda panda;

    private Chameleon chameleon;

    private Lion lion;

    private Auto auto;


    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }


    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setChameleon(Chameleon chameleon) {
        this.chameleon = chameleon;
    }

    public Chameleon getChameleon() {
        return chameleon;
    }

    public Player getPlayer() {
        return player;
    }
    public Skeleton getSkeleton() {
        return skeleton;
    }

    public Lion getLion(){
        return lion;
    }

    public void setLion(Lion lion){
        this.lion = lion;
    }

    public Panda getPanda() {
        return panda;
    }

    public void setPanda(Panda panda) {
        this.panda = panda;
    }

    public void setSkeleton(Skeleton skeleton) {
        this.skeleton = skeleton;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
