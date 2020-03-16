package com.knopp10000;

public class OthelloPiece {

    private boolean isWhite = false;
    private Color color;

    public OthelloPiece(Color color){
        if (color == Color.WHITE){
            this.isWhite = true;
        }
        this.color = color;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void flip() {
        isWhite = !isWhite;
        if (isWhite){
            color = Color.BLACK;
        }else{
            color = Color.WHITE;
        }
    }

    public Color getColor() {
        return color;
    }
}
