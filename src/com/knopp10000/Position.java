package com.knopp10000;

import java.util.Objects;

public class Position {
    private int row, column;
    public Position(int r, int c) {
        this.row = r;
        this.column = c;
    }

    public int getRow() {
        return row;
    }

    public final int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return position.hashCode() == hashCode();

    }

    @Override
    public int hashCode() {
        int hash = row * 13;
        hash += column * 17;
        return hash;
    }
}
