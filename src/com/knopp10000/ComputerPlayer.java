package com.knopp10000;

import java.util.*;
import java.util.concurrent.BlockingDeque;

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
        Color originalColor = state.getCurrentColor();

        for (Position position : legalPositions) {
            System.out.println("Testing: " + position.getRow() + "-" + position.getColumn());
            state.makeMove(position.getRow(), position.getColumn(), state.getCurrentColor());
            //System.out.println(state.getBoard());
            double responseValue = minVal(state, max, min, depth);

            if (max < responseValue) {
                System.out.println("best pos so far: " + position.getRow() + "-" + position.getColumn() + " responseValue: " + responseValue);
                max = responseValue;
                maxPosition = position;
            }

            state.setBoard(originalBoard);
            state.setCurrentColor(originalColor);
        }
        return maxPosition;
    }

    public double maxVal(OthelloBoardState state, double a, double b, int depth) {
        HashSet<Position> legalPositions = state.getBoard().getAllLegalMoves(state.getCurrentColor());
        if (legalPositions.size() == 0 || depth > LIMIT) {
//            return eval(state);
            return state.getBoard().computeScore(getColor());
        }

        // action ordering
        Map<Integer, Position> newMoves = actOrderMax(state, legalPositions);
        Set<Position> newMoveSet = new HashSet<>(newMoves.values());

        OthelloBoard originalBoard = new OthelloBoard();
        originalBoard.setBoard(state.getBoard().getBoard());
        Color originalColor = state.getCurrentColor();

        double responseValue = Double.NEGATIVE_INFINITY;

        for (Position position : newMoveSet) {
            state.makeMove(position.getRow(), position.getColumn(), getColor());
            responseValue = Math.max(responseValue, minVal(state, a, b, depth + 1));

            // pruning
            if (responseValue >= b) {
                System.out.println("prune. WE would never pick: " + position.getRow() + "-" + position.getColumn());
                return responseValue;
            }
            a = Math.max(a, responseValue);

            state.setBoard(originalBoard);
            state.setCurrentColor(originalColor);
        }
        return responseValue;
    }

    public double minVal(OthelloBoardState state, double a, double b, int depth) {
        // System.out.println(depth);
        HashSet<Position> legalPositions = state.getBoard().getAllLegalMoves(state.getCurrentColor());

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

        // action ordering
        Map<Integer, Position> newMoves = actOrderMin(state, legalPositions);
        Set<Position> newMoveSet = new HashSet<>(newMoves.values());

        OthelloBoard originalBoard = new OthelloBoard();
        originalBoard.setBoard(state.getBoard().getBoard());
        Color originalColor = state.getCurrentColor();

        double responseValue = Double.POSITIVE_INFINITY;

        for (Position position : newMoveSet) {
            //System.out.println("Testing: " + position.getRow() + "-" + position.getColumn());
            state.makeMove(position.getRow(), position.getColumn(), state.getCurrentColor());
            responseValue = Math.min(responseValue, maxVal(state, a, b, depth + 1));

//            System.out.println(position.getRow() + "-" + position.getColumn() + " ");

            // pruning
            if (responseValue <= a) {
                System.out.println("prune. " + opposingColor.toString() +  " would never pick: " + position.getRow() + "-" + position.getColumn());
                return responseValue;
            }
            b = Math.min(b, responseValue);

            state.setBoard(originalBoard);
            state.setCurrentColor(originalColor);
        }
        return responseValue;
    }

    public Map<Integer, Position> actOrderMax(OthelloBoardState state, HashSet<Position> legalPositions) {
        TreeMap<Integer, Position> tempMoveSet = new TreeMap<>();

//        OthelloBoard originalBoard = new OthelloBoard();
//        originalBoard.setBoard(state.getBoard().getBoard());
//        Color originalColor = state.getCurrentColor();

        OthelloBoardState originalState = state.clone();

        for (Position position : legalPositions) {
            state.makeMove(position.getRow(), position.getColumn(), getColor());
            int val = state.getBoard().computeScore(getColor());
//            System.out.println("(Max)Pos: " + position.getRow() + "-" + position.getColumn() + " with Score: " + val);
            //System.out.println(state.getBoard());
            tempMoveSet.put(val, position);

            state.setBoard(originalState.getBoard());
            state.setCurrentColor(originalState.getCurrentColor());
        }

        Map<Integer, Position> newMoveSet = new TreeMap<>(Collections.reverseOrder());
        newMoveSet.putAll(tempMoveSet);
        return newMoveSet;
    }

    public Map<Integer, Position> actOrderMin(OthelloBoardState state, HashSet<Position> legalPositions) {
        TreeMap<Integer, Position> tempMoveSet = new TreeMap<>();
        //System.out.println("time to order the moves!!!!");

//        OthelloBoard originalBoard = new OthelloBoard();
//        originalBoard.setBoard(state.getBoard().getBoard());
//        Color originalColor = state.getCurrentColor();

        OthelloBoardState originalState = state.clone();

        for (Position position : legalPositions) {
            state.makeMove(position.getRow(), position.getColumn(), state.getCurrentColor());
            int val = -(state.getBoard().computeScore(state.getCurrentColor()));

            //System.out.println("(Min)Pos: " + position.getRow() + "-" + position.getColumn() + " with Score: " + val);
            tempMoveSet.put(val, position);

            state.setBoard(originalState.getBoard());
            state.setCurrentColor(originalState.getCurrentColor());
        }
        System.out.println("best move for white is(?): " + tempMoveSet.firstEntry().getValue().getRow() + "-" + tempMoveSet.firstEntry().getValue().getColumn());
        System.out.println("worst move for white is(?): " + tempMoveSet.lastEntry().getValue().getRow() + "-" + tempMoveSet.lastEntry().getValue().getColumn());
        return tempMoveSet;
    }
}
