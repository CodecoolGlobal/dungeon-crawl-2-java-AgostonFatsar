package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Trump;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {
    GameMap map = new GameMap(3, 3, CellType.FLOOR);


    @Test
    void moveUpdatesCells() {
        Player player = new Player(map.getCell(1, 1));
        player.move(1, 0, map);

        assertEquals(2, player.getCell(player.getTileName(), map).getX());
        assertEquals(1, player.getCell(player.getTileName(), map).getY());
        assertEquals(null, map.getCell(1, 1).getActor());
        assertEquals(player, map.getCell(2, 1).getActor());
    }

    @Test
    void cannotMoveIntoWall() {
        map.getCell(2, 1).setType(CellType.WALL);
        Player player = new Player(map.getCell(1, 1));
        player.move(1, 0, map);

        assertEquals(1, player.getCell(player.getTileName(), map).getX());
        assertEquals(1, player.getCell(player.getTileName(), map).getY());
    }

    @Test
    void cannotMoveOutOfMap() {
        Player player = new Player(map.getCell(2, 1));
        player.move(1, 0, map);

        assertEquals(2, player.getCell(player.getTileName(), map).getX());
        assertEquals(1, player.getCell(player.getTileName(), map).getX());
    }

    @Test
    void cannotMoveIntoAnotherActor() {
        Player player = new Player(map.getCell(1, 1));
        Trump trump = new Trump(map.getCell(2, 1));
        player.move(1, 0, map);

        assertEquals(1, player.getCell(player.getTileName(), map).getX());
        assertEquals(1, player.getCell(player.getTileName(), map).getY());
        assertEquals(2, trump.getCell(player.getTileName(), map).getX());
        assertEquals(1, trump.getCell(player.getTileName(), map).getY());
        assertEquals(trump, map.getCell(2, 1).getActor());
    }
}