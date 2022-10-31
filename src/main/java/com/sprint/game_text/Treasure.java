package com.sprint.game_text;

public class Treasure extends Entity {

	public Treasure(int[] position) {
		super(position);
		this.setSprite('T');
		this.value = 100;
	}

}
