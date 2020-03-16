package com.knopp10000;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.knopp10000.Color.BLACK;
import static com.knopp10000.Color.WHITE;

public class OthelloBoard {
    public final static int BOARD_WIDTH = 8;
    public final static int BOARD_HEIGHT = 8;

    private OthelloPiece[][] board = new OthelloPiece[BOARD_HEIGHT][BOARD_WIDTH];
    private Color currentPlayerColor;

    public OthelloPiece[][] getBoard(){
        return board;
    }

    public void setBoard(OthelloPiece[][] board) {
        this.board = board;
    }

    void init(){
        board[3][3] = new OthelloPiece(WHITE);
        board[3][4] = new OthelloPiece(BLACK);
        board[4][3] = new OthelloPiece(BLACK);
        board[4][4] = new OthelloPiece(WHITE);
    }

    public boolean isFull() {
        for (int i = 0; i<BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++)
                if (board[i][j] == null) {
                    return false;
                }
        }
        return true;
    }

    public int computeScore(Color color) {
        int score = 0;
        for (int i = 0; i<BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++)
                if (board[i][j].getColor() == color) {
                    score++;
                }
        }
        return score;
    }

    public HashSet<Position> getAllLegalMoves(Color color) {
        HashSet<Position> moves = new HashSet<>();
        for (int i = 0; i<BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++)
                if (isLegalMove(i, j, color)){
                    moves.add(new Position(i, j));
                }
        }
        return moves;
    }

    public boolean isLegalMove(int r, int c, Color color) {
        if (board[r][c] != null) {
            return false; //if not empty
        }
        int x, y;
        //check adjacent positions
        for (int m = -1; m <= 1; m++) {
            for (int n = -1; n <= 1; n++) {
                y = r + m;
                x = c + n;
                if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT ||
                        board[y][x] == null || board[y][x].getColor() == color) {
                    continue; //if adjacent position is not opposing players piece
                }
                for(;;) {
                    y += m;
                    x += n;
                    if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT || board[y][x] == null) {
                        break;
                    }
                    if (board[y][x].getColor() == color) {
                        return true;
                    }else{
                        continue; //we found another opposing piece.
                    }
                }
            }
        }
        return false;
    }
}
