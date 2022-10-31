package com.sprint.game_text;

public class Enemy extends Entity {

	protected String race;

	public Enemy(int[] position) {
		super(position);
		this.setSprite('E');
	}

}
