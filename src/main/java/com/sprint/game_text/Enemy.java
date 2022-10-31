package com.sprint.game_text;

public abstract class Enemy extends Entity{
	
	protected static int instanceCounter = 0;
	protected String race;

	public Enemy(int[] position) {
		super(position);
		this.setSprite('E');
		instanceCounter++;
	}

}
