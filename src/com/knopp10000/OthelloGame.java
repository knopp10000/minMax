package com.knopp10000;

import java.util.HashSet;

public class OthelloGame {
        Player p1, p2;
        OthelloBoard currentBoard = new OthelloBoard();
        Player currentPlayer;
        OthelloBoardState state;

        public OthelloGame(Player p1, Player p2) {
            this.p1 = p1;
            this.p2 = p2;
            currentPlayer = p2;
        }

        private void togglePlayer() {
            if (currentPlayer == p1){
                currentPlayer = p2;
            }else{
                currentPlayer = p1;
            }
        }

        public void run() {
            currentBoard.init();
            state = new OthelloBoardState(currentBoard, currentPlayer.getColor());

            boolean over = false;

            System.out.println(state.getBoard().toString());

            while(!over){
                System.out.println("CurrentPlayer is: " + (currentPlayer.getColor() == Color.BLACK ? "Black" : "White"));
                HashSet<Position> legalPositions = state.getBoard().getAllLegalMoves(currentPlayer.getColor());
                if (!legalPositions.isEmpty()){
//                    System.out.print("Legal positions are: ");
//                    for (Position pos: legalPositions){
//                        System.out.print(pos.getRow() + "-" + pos.getColumn() + ", ");
//                    }
//                    System.out.println();
                    Position move = currentPlayer.move(state, legalPositions);
                    state.makeMove(move.getRow(), move.getColumn(), currentPlayer.getColor());

                    System.out.println(currentPlayer.getColor() + " picked the position at: " + move.getRow() + "-" + move.getColumn());
                    System.out.println(state.getBoard());

                    togglePlayer();
                }else{
                    over = true;
                }
            }
            System.out.println(currentPlayer.getColor() + " has no more moves! Game Over!");
            if (state.getBoard().computeScore(currentPlayer.getColor()) > state.getBoard().computeScore(currentPlayer.getColor() == Color.BLACK? Color.WHITE : Color.BLACK)){
                System.out.println(currentPlayer.getColor() + " won with " + state.getBoard().computeScore(currentPlayer.getColor()) + " points!");
                System.out.println((currentPlayer.getColor() == Color.BLACK? Color.WHITE : Color.BLACK) + " lost with " + state.getBoard().computeScore((currentPlayer.getColor() == Color.BLACK? Color.WHITE : Color.BLACK)) + " points!");
            }else if(state.getBoard().computeScore(currentPlayer.getColor()) < state.getBoard().computeScore(currentPlayer.getColor() == Color.BLACK? Color.WHITE : Color.BLACK)){
                System.out.println((currentPlayer.getColor() == Color.BLACK? Color.WHITE : Color.BLACK) + " won with " + state.getBoard().computeScore((currentPlayer.getColor() == Color.BLACK? Color.WHITE : Color.BLACK)) + " points!");
                System.out.println(currentPlayer.getColor() + " lost with " + state.getBoard().computeScore(currentPlayer.getColor()) + " points!");
            }else{
                System.out.println("ITS A DRAAAWWWWW WITH " + state.getBoard().computeScore(currentPlayer.getColor()) + " points!");
            }
        }
}
