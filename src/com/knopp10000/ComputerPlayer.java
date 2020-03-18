package com.knopp10000;

import java.util.*;

public class ComputerPlayer extends Player {
    private int depthLimit;
    private Color opposingColor;

    public ComputerPlayer(Color color, int depthLimit) {
        super(color);
        opposingColor = getColor() == Color.BLACK? Color.WHITE : Color.BLACK;
        this.depthLimit = depthLimit;
    }

    @Override
    public Position move(BoardState state, HashSet<Position> legalPositions) {
        int depth = 1;
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.POSITIVE_INFINITY;

        Position maxPosition = null;

        Board originalBoard = new Board();
        originalBoard.setBoard(state.getBoard().getBoard());

        for (Position position : legalPositions) {
            state.makeMove(position.getRow(), position.getColumn(), getColor());
            double responseValue = minVal(state, max, min, depth);

            if (max < responseValue) {
                max = responseValue;
                maxPosition = position;
            }

            state.setBoard(originalBoard);
        }
        return maxPosition;
    }

    public double maxVal(BoardState state, double a, double b, int depth) {
        HashSet<Position> legalPositions = state.getBoard().getAllLegalMoves(getColor());
        if (legalPositions.size() == 0 || depth > depthLimit) {
            return state.getBoard().computeScore(getColor());
        }

        Board originalBoard = new Board();
        originalBoard.setBoard(state.getBoard().getBoard());

        double responseValue = Double.NEGATIVE_INFINITY;

        for (Position position : legalPositions) {
            state.makeMove(position.getRow(), position.getColumn(), getColor());
            responseValue = Math.max(responseValue, minVal(state, a, b, depth + 1));

            //prune
            if (responseValue >= b) {
                return responseValue;
            }
            a = Math.max(a, responseValue);

            state.setBoard(originalBoard);
        }
        return responseValue;
    }

    public double minVal(BoardState state, double a, double b, int depth) {
        HashSet<Position> legalPositions = state.getBoard().getAllLegalMoves(opposingColor);

        if (legalPositions.size() == 0 || depth > depthLimit) {
            return -(state.getBoard().computeScore(opposingColor));
        }

        Board originalBoard = new Board();
        originalBoard.setBoard(state.getBoard().getBoard());

        double responseValue = Double.POSITIVE_INFINITY;

        for (Position position : legalPositions) {
            state.makeMove(position.getRow(), position.getColumn(), opposingColor);
            responseValue = Math.min(responseValue, maxVal(state, a, b, depth + 1));

            //prune
            if (responseValue <= a) {
                return responseValue;
            }
            b = Math.min(b, responseValue);

            state.setBoard(originalBoard);
        }
        return responseValue;
    }
}
