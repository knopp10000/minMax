package com.knopp10000;

import java.util.HashSet;
import java.util.Map;

public abstract class Player {
    private Color color;

    public Player(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    abstract public Position move(OthelloBoardState state, HashSet<Position> legalPositions);
}
