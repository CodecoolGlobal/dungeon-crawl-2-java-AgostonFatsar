package com.codecool.dungeoncrawl.logic.items.cages;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.animals.LionItem;

public class LionCage extends Item {

    public LionCage (Cell cell){
        super(cell);

    }
    public String getTileName() {
        return "Successfully saved Lion!";
    }

    @Override
    public void act(GameMap map) {
        map.getPlayer().getItems().add(this);
        map.getPlayer().getItems().removeIf(item -> item.getTileName().equals("lion"));
        Cell lionCell = map.getPlayer().getCell(map.getPlayer().getTileName(), map).getNeighbor(0,-1);
        lionCell.setItem(new LionItem(lionCell));
    }
}
