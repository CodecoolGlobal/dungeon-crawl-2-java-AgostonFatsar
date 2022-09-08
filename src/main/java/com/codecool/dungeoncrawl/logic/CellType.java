package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    CLOSEDDOOR("closedDoor"),
    OPENDOOR("openDoor"),
    MUD("mud"),
    CONCRETE("concrete"),
    CONCRETE_EDGE("concreteEdge"),
    GRASS("grass"),
    WALL("wall");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
