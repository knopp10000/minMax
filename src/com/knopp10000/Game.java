package com.knopp10000;

import java.util.HashSet;

public class Game {
        Player p1, p2;
        Board currentBoard = new Board();
        Player currentPlayer;
        BoardState state;

        public Game(Player p1, Player p2) {
            this.p1 = p1;
            this.p2 = p2;
            currentPlayer = p1;
        }

        private void togglePlayer() {
            if (currentPlayer == p1){
                currentPlayer = p2;
            }else if(currentPlayer == p2){
               currentPlayer = p1;
            }else{
                System.out.println("togglePlayer() does not work");
                System.exit(42);
            }
        }

        public void run() {
            currentBoard.init();
            state = new BoardState(currentBoard, currentPlayer.getColor());

            System.out.println(state.getBoard());

            while(true){
                System.out.println("CurrentPlayer is: " + currentPlayer.getColor());
                HashSet<Position> legalPositions = state.getBoard().getAllLegalMoves(currentPlayer.getColor());
                if (!legalPositions.isEmpty()){
                    System.out.print("Legal positions are: ");
                    for (Position pos: legalPositions){
                        System.out.print(pos.getRow() + "-" + pos.getColumn() + ", ");
                    }
                    System.out.println();
                    Position move = currentPlayer.move(state, legalPositions);
                    state.makeMove(move.getRow(), move.getColumn(), currentPlayer.getColor());

                    System.out.println(currentPlayer.getColor() + " picked the position at: " + move.getRow() + "-" + move.getColumn());
                    System.out.println(state.getBoard());

                    togglePlayer();
                }else{
                    endGame();
                    break;
                }
            }
        }

    private void endGame() {
        System.out.println(currentPlayer.getColor() + " has no more moves! Game Over!");

        int p1Score = state.getBoard().computeScore(p1.getColor());
        int p2Score = state.getBoard().computeScore(p2.getColor());

        if (p1Score > p2Score){
            System.out.println(p1.getColor() + " won with " + p1Score + " points!");
            System.out.println(p2.getColor() + " lost with " + p2Score + " points!");
        }else if(p1Score < p2Score){
            System.out.println(p2.getColor() + " won with " + p2Score + " points!");
            System.out.println(p1.getColor() + " lost with " + p1Score + " points!");
        }else{
            System.out.println("ITS A DRAAAWWWWW WITH " + p1Score + " points!");
        }
    }
}
