package com.knopp10000;

import java.util.*;

public class ComputerPlayer extends Player {
    private static final int LIMIT = 3;
    private Color opposingColor;

    public ComputerPlayer(Color color) {
        super(color);
        opposingColor = getColor() == Color.BLACK? Color.WHITE : Color.BLACK;
    }

    @Override
    public Position move(OthelloBoardState state, HashSet<Position> legalPositions) {
//        System.out.print("Legal positions are: ");
//        for (Position pos: legalPositions) {
//            System.out.print(pos.getRow() + "-" + pos.getColumn()+ ", ");
//        }
//        System.out.println();
        int depth = 1;
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.POSITIVE_INFINITY;

        Position maxPosition = null;

        OthelloBoard originalBoard = new OthelloBoard();
        originalBoard.setBoard(state.getBoard().getBoard());

        for (Position position : legalPositions) {
//            System.out.println("Testing: " + position.getRow() + "-" + position.getColumn());
            state.makeMove(position.getRow(), position.getColumn(), getColor());
            //System.out.println(state.getBoard());
            double responseValue = minVal(state, max, min, depth);

            if (max < responseValue) {
                //System.out.println("best pos so far: " + position.getRow() + "-" + position.getColumn() + " responseValue: " + responseValue);
                max = responseValue;
                maxPosition = position;
            }

            state.setBoard(originalBoard);
        }
        return maxPosition;
    }

    public double maxVal(OthelloBoardState state, double a, double b, int depth) {
        HashSet<Position> legalPositions = state.getBoard().getAllLegalMoves(getColor());
        if (legalPositions.size() == 0 || depth > LIMIT) {
            return state.getBoard().computeScore(getColor());
        }

        OthelloBoard originalBoard = new OthelloBoard();
        originalBoard.setBoard(state.getBoard().getBoard());

        double responseValue = Double.NEGATIVE_INFINITY;

        for (Position position : legalPositions) {
            state.makeMove(position.getRow(), position.getColumn(), getColor());
            responseValue = Math.max(responseValue, minVal(state, a, b, depth + 1));

            // pruning
            if (responseValue >= b) {
//                System.out.println("prune. WE would never pick: " + position.getRow() + "-" + position.getColumn());
                return responseValue;
            }
            a = Math.max(a, responseValue);

            state.setBoard(originalBoard);
        }
        return responseValue;
    }

    public double minVal(OthelloBoardState state, double a, double b, int depth) {
        HashSet<Position> legalPositions = state.getBoard().getAllLegalMoves(opposingColor);

        if (legalPositions.size() == 0 || depth > LIMIT) {
//            return eval(board);
//            System.out.println("deptht is reached or " + state.getCurrentColor() + " has no legalMoves left");
            return -(state.getBoard().computeScore(opposingColor));
        }

//        System.out.print(state.getCurrentColor() + " can place at: ");
//        for (Position pos: legalPositions) {
//            System.out.print(pos.getRow() + "-" + pos.getColumn()+ ", ");
//        }
//        System.out.println();

        OthelloBoard originalBoard = new OthelloBoard();
        originalBoard.setBoard(state.getBoard().getBoard());

        double responseValue = Double.POSITIVE_INFINITY;

        for (Position position : legalPositions) {
            //System.out.println("Testing: " + position.getRow() + "-" + position.getColumn());
            state.makeMove(position.getRow(), position.getColumn(), opposingColor);
            responseValue = Math.min(responseValue, maxVal(state, a, b, depth + 1));

//            System.out.println(position.getRow() + "-" + position.getColumn() + " ");

            // pruning
            if (responseValue <= a) {
//                System.out.println("prune. " + opposingColor +  " would never pick: " + position.getRow() + "-" + position.getColumn());
                return responseValue;
            }
            b = Math.min(b, responseValue);

            state.setBoard(originalBoard);
        }
        return responseValue;
    }
}
