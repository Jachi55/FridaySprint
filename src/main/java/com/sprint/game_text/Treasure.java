package com.sprint.game_text;

public class Treasure extends Entity {

	protected static int instanceCounter = 0;

	public Treasure(int[] position) {
		super(position);
		this.setSprite('T');
		instanceCounter++;
	}

}
