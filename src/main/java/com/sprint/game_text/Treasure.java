package com.sprint.game_text;

public class Treasure {
	
	int[] treasureLocation;
	
	public Treasure(int[] treasureLocation) {
		this.treasureLocation = treasureLocation;
	}
	
	public void getTreasureLocation() {
		System.out.println("The treasure is at sqaure: " + treasureLocation[0] + ", " + treasureLocation[1]);
	}	
}
