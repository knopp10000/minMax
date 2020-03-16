package com.knopp10000;

import java.util.HashMap;
import java.util.Map;

public class Explorer {
    public static Map<Integer, Integer> explore(final OthelloBoard board, final Color color) {
        Map<Integer, Integer> possibleMoves = new HashMap<>();
        Set<Point> statePoints = board.getSquares(state);
        for (Point seed : statePoints) {
            for (Direction direction : Direction.values()) {
                if (shouldSearch(board, seed, direction)) {
                    Point nextPoint = direction.next(seed);
                    nextPoint = direction.next(nextPoint);
                    while (pointIsValid(nextPoint)) {
                        if (board.getSquareState(nextPoint) == state) {
                            break;
                        } else if (board.getSquareState(nextPoint) == SquareState.EMPTY) {
                            possibleMoves.add(nextPoint);
                            break;
                        }
                        nextPoint = direction.next(nextPoint);
                    }
                }
            }
        }
        return possibleMoves;
    }
}
