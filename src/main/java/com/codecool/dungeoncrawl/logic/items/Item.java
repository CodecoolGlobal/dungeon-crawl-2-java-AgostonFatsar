package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.Objects;

public abstract class Item implements Drawable {


    public Item(Cell cell) {
        cell.setItem(this);
    }

    public abstract void act(GameMap map);


    public Cell getCell(String actorName, GameMap map) {
        Cell currentCell = null;
        for (Cell[] ListOfCells : map.getCells()) {
            for (Cell cell : ListOfCells) {
                if (cell.getItem() != null) {
                    if (Objects.equals(cell.getItem().getTileName(), actorName)) {
                        currentCell = cell;
                    }
                }
            }
        }
        return currentCell;
    }

}
