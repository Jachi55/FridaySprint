package com.sprint.game_text;

public class Villager extends Entity{
	private String name;
	
	public Villager(int[] position, String name) {
		super(position);
		this.name = name;
		this.value = 20;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String new_name) {
		this.name = new_name;
	}
	
	
	public void randomMove(Grid g) {
		// Generate random position
		int direction = (int) Math.random(); // Picks x or y
		int step = (int) ((Math.random() * 2) - 1); // Random number between -1 and 1
		this.setPrevPosition(this.getPosition());
		int[] newPos = this.getPosition();
		newPos[direction] += step; // Updates new position with random x/y step
		this.setPosition(newPos);
		
		g.updateTile(this.getPosition(), this);
		
	}
	
}