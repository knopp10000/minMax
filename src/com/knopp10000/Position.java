package com.knopp10000;

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
}
