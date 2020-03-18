package com.knopp10000;

public class OthelloBoardState {
    private OthelloBoard board;
    private Color currentColor;

    public OthelloBoardState(OthelloBoard board, Color color){
        this.board = board;
        this.currentColor = color;
    }

    public void makeMove(int r, int c, Color color){
        if (!board.isLegalMove(r, c, color)){ //could speed up code if this is removed
            System.out.println("move was not legal!!! NANI!!!");
            return;
        }
        OthelloBoard newOthelloBoard = new OthelloBoard();
        OthelloPiece[][] newBoard = board.getBoard().clone();

        int x, y;
        for (int m = -1; m <= 1; m++) {
            for (int n = -1; n <= 1; n++) {
                y = r + m;
                x = c + n;
                //System.out.println("place wa null desu: " + (newBoard[y][x] == null? "Ya":"Na"));
                if (x < 0 || x >= OthelloBoard.BOARD_WIDTH || y < 0 || y >= OthelloBoard.BOARD_HEIGHT ||
                        newBoard[y][x] == null || newBoard[y][x].getColor() == color) {
                    continue;
                }
                int i = r, j = c;
                for(int t = 0; t < OthelloBoard.BOARD_WIDTH; t++) {
                    i += m;
                    j += n;
                    if (j < 0 || j >= OthelloBoard.BOARD_WIDTH || i < 0 || i >= OthelloBoard.BOARD_HEIGHT || newBoard[i][j] == null) {
                        //System.out.println(i + "-" + j + " is Null so we stop checking this direction");
                        break;
                    }
                    //System.out.println("");
                    if (newBoard[i][j].getColor() == color) {
                        //System.out.println("found other pice to flip to: " + i + "-" + j);
                        newBoard[r][c] = new OthelloPiece(color);
                        for (int k = 1; k <= t; k++){
                            int tX = r+(m*k);
                            int tY = c+(n*k);
                            //System.out.println("flipping: " + tX + "-" + tY);
                            newBoard[r+m*k][c+n*k] = new OthelloPiece(color);
//                            newBoard[r+m*k][c+n*k].setColor(color); doesnt work for some fuckin reason
                        }
                        break;
                    } else {
                        continue;
                    }
                }
                //System.out.println("One direction flip done");
            }
        }
        togglePlayer();
        board.setBoard(newBoard);
    }

    private void togglePlayer() {
        if (currentColor == Color.BLACK){
            currentColor = Color.WHITE;
        }else{
            currentColor = Color.BLACK;
        }
    }

    public OthelloBoard getBoard() {
        return board;
    }

    public void setBoard(OthelloBoard board) {
        this.board.setBoard(board.getBoard());
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    @Override
    protected OthelloBoardState clone() {
        OthelloBoardState clone = new OthelloBoardState(new OthelloBoard(), getCurrentColor());
        clone.setBoard(getBoard());
        return clone;
    }
}
