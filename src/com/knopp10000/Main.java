package com.knopp10000;

public class Main {

    public static void main(String[] args) {
	    Player p1 = new ComputerPlayer(Color.BLACK, 2);
		Player p2 = new HumanPlayer(Color.WHITE);

		new Game(p1, p2).run();
    }

}
