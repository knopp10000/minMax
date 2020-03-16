package com.knopp10000;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ComputerPlayer extends Player {
    private static final int LIMIT = 5;

    public ComputerPlayer(Color color) {
        super(color);
    }

    @Override
    public Position move(OthelloBoardState state, HashSet<Position> legalPositions) {
        int depth = 1;
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.POSITIVE_INFINITY;

        Position maxPosition = null;

        for (Position position : legalPositions) {
            state.makeMove(position.getRow(), position.getColumn(), state.getCurrentColor());
            double v = minVal(, max, min, depth);
            if (max < v) {
                max = v;
                maxPosition = position;
            }
        }
        return maxPosition;
    }

    public double minVal(OthelloBoardState state, double a, double b, int depth) {
        // System.out.println(depth);
        HashSet<Position> legalMoves = state.getBoard().getAllLegalMoves(state.getCurrentColor());
        if (legalMoves.size() == 0 || depth > LIMIT) {
            return eval(board);
        }

        // action ordering
        Map<Integer, Position> newMoves = actOrderMin(state, legalMoves);
        Set<Position> newMoveSet = new HashSet<>(newMoves.values());

        double v = Double.POSITIVE_INFINITY;
        for (Position position : newMoveSet) {
            OthelloBoard newBoard = state.makeMove(position.getRow(), position.getColumn(), state.getCurrentColor());
            v = Math.min(v, maxVal(newBoard, a, b, depth + 1));

            // pruning
            if (v <= a) {
                return v;
            }
            b = Math.min(b, v);
        }
        return v;
    }
}
