package com.sprint.game_text;

public class Treasure {
	
	int[] treasureLocation;
	private char sprite = 'T';
	
	public Treasure(int[] treasureLocation) {
		this.treasureLocation = treasureLocation;
	}
	
	public void getTreasureLocationString() {
		System.out.println("The treasure is at sqaure: " + treasureLocation[0] + ", " + treasureLocation[1]);
	}	
	
	public int[] getTreasurePosition() {
		return treasureLocation;
	}
	

	public char getSprite() {
		return sprite;
	}

	public void setSprite(char sprite) {
		this.sprite = sprite;
	}
}
