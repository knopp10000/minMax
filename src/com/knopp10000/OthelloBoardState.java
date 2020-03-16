package com.knopp10000;

public class OthelloBoardState {
    private OthelloBoard board;
    private Color currentColor;

    public OthelloBoardState(OthelloBoard board, Color color){
        this.board = board;
        this.currentColor = color;
    }

    public OthelloBoard makeMove(int r, int c, Color color){
        if (!board.isLegalMove(r, c, color)){ //could speed up code if this is removed
            return null;
        }
        OthelloBoard newOthelloBoard = new OthelloBoard();
        OthelloPiece[][] newBoard = board.getBoard().clone();

        int x, y;
        for (int m = -1; m <= 1; m++) {
            for (int n = -1; n <= 1; n++) {
                y = r + m;
                x = c + n;
                if (x < 0 || x >= OthelloBoard.BOARD_WIDTH || y < 0 || y >= OthelloBoard.BOARD_HEIGHT ||
                        newBoard[y][x] == null || newBoard[y][x].getColor() == color) {
                    continue;
                }
                int i = r, j = c;
                for(int t = 0; t < OthelloBoard.BOARD_WIDTH; t++) {
                    i += m;
                    j += n;
                    if (j < 0 || j >= OthelloBoard.BOARD_WIDTH || i < 0 || i >= OthelloBoard.BOARD_HEIGHT || newBoard[y][x] == null) {
                        break;
                    }
                    if (newBoard[i][j].getColor() == color) {
                        newBoard[r][c] = new OthelloPiece(color);
                        for (int k = 0; k <= t; k++){
                            newBoard[r+m*(k+1)][c+n*(k+1)].flip();
                        }
                        break;
                    } else {
                        continue;
                    }
                }
            }
        }
        togglePlayer();
        newOthelloBoard.setBoard(newBoard);
        return newOthelloBoard;
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

    public Color getCurrentColor() {
        return currentColor;
    }
}
