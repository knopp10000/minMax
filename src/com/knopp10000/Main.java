package com.knopp10000;

public class Main {

    public static void main(String[] args) {
	    Player p1 = new ComputerPlayer(Color.WHITE);
	    Player p2 = new ComputerPlayer(Color.BLACK);

	    new Game(p1, p2).run();
    }

}
