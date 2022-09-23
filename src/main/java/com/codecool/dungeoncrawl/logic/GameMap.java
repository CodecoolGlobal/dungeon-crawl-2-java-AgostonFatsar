package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.animals.Chameleon;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.animals.LionItem;
import com.codecool.dungeoncrawl.logic.items.animals.Panda;

import java.util.ArrayList;
import java.util.Objects;

public class GameMap {
    private final int width;
    private final int height;

    public Cell[][] getCells() {
        return cells;
    }

    private final Cell[][] cells;



    private Player player;


    public ArrayList<Actor> actors = new ArrayList<>();

    public ArrayList<Item> itemsThatMove = new ArrayList<>();

    private final ArrayList<Trump> trumps = new ArrayList<Trump>();
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
                cells[x][y] = new Cell( x, y, defaultCellType);
            }
        }
    }


    public void fillUpTrumps(GameMap map){
        for (Cell[] ListOfCells : map.getCells()) {
            for (Cell cell : ListOfCells) {
                if (cell.getActor() != null){
                    if (cell.getActor().getTileName().equals("skeleton"))
                        trumps.add((Trump) cell.getActor());
                }
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

    public ArrayList<Trump> getTrumps() {
        return trumps;
    }

    public  void addTrumps(Trump trump) {
        trumps.add(trump);
    }

    public void switchLion(Item item){
        player.getItems().add(item);
        for (Cell [] ListOfCells : cells){
            for(Cell cell : ListOfCells){
                if (cell.getActor() != null){
                    if(Objects.equals(cell.getActor().getTileName(), "lion")) {
                        cell.setActor(null);
                        cell.setItem(new LionItem(cell));
                    }
                }
            }
        }


    }
}
