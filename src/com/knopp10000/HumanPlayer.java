package com.knopp10000;

import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class HumanPlayer extends Player {

    private Scanner sc = new Scanner(System.in);

    public HumanPlayer(Color color) {
        super(color);
    }

    @Override
    public Position move(OthelloBoardState state, HashSet<Position> legalPositions) {
        while(true){
            System.out.println("Enter your new moves coordinates.");
            System.out.println("Enter Row:");
            int r = sc.nextInt();
            System.out.println("Enter Columns:");
            int c = sc.nextInt();
//            System.out.println("You picked the position at: " + r + "-" + c);
            Position pos = new Position(r, c);
            if (!legalPositions.contains(pos)){
                return pos;
            }
        }
    }
}
