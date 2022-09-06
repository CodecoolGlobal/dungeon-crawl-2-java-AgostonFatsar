package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Auto extends Actor{


    public Auto(Cell cell) {
        super(cell);
    }



    @Override
    public String getTileName() {
        return "auto";
    }

    public void moveCar() {
        super.move(1, 0);
    }


}
