package com.knopp10000;

public class BoardState {
    private Board board;
    private Color currentColor;

    public BoardState(Board board, Color color){
        this.board = board;
        this.currentColor = color;
    }

    /**
     * Performs a single move in Othello/Reversi.
     * This method checks every direction around our position {@parama r},{@parama c}
     * to try and find another piece of the same color.
     * When it does it sets all the opposing colored pieces in between to the {@param color}.
     * <P>
     *  After the move is finished we call the togglePlayer function.
     *
     * @param  r the row that the piece should be placed at in the board
     * @param  c the column that the piece should be placed at in the board
     * @param  color the color of player who makes the move
     */
    public void makeMove(int r, int c, Color color){
        if (!board.isLegalMove(r, c, color)){ //could speed up code if this is removed
            System.out.println("move was not legal!!");
            System.exit(66);
            return;
        }
        Piece[][] newBoard = board.getBoard().clone();

        int x, y;
        for (int m = -1; m <= 1; m++) {
            for (int n = -1; n <= 1; n++) {
                y = r + m;
                x = c + n;
                if (x < 0 || x >= Board.BOARD_WIDTH || y < 0 || y >= Board.BOARD_HEIGHT ||
                        newBoard[y][x] == null || newBoard[y][x].getColor() == color) {
                    continue;
                }
                int i = r, j = c;
                for(int t = 0; t < Board.BOARD_WIDTH; t++) {
                    i += m;
                    j += n;
                    if (j < 0 || j >= Board.BOARD_WIDTH || i < 0 || i >= Board.BOARD_HEIGHT || newBoard[i][j] == null) {
                        //System.out.println(i + "-" + j + " is Null so we stop checking this direction");
                        break;
                    }
                    if (newBoard[i][j].getColor() == color) {
                        //System.out.println("found other pice to flip to: " + i + "-" + j);
                        newBoard[r][c] = new Piece(color);
                        for (int k = 1; k <= t; k++){
                            int tX = r+(m*k);
                            int tY = c+(n*k);
                            //System.out.println("flipping: " + tX + "-" + tY);
                            newBoard[r+m*k][c+n*k] = new Piece(color);
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
        togglePlayer(); //not in use
        board.setBoard(newBoard);
    }

    //not in use
    private void togglePlayer() {
        if (currentColor == Color.BLACK){
            currentColor = Color.WHITE;
        }else{
            currentColor = Color.BLACK;
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board.setBoard(board.getBoard());
    }
}
