package com.sprint.game_text;

public class Enemy extends Entity{
	
	protected static int instanceCounter = 0;

	public Enemy(int[] position) {
		super(position);
		this.setSprite('E');
		instanceCounter++;
	}

}
