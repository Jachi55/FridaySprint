package com.sprint.game_text;

public class Enemy extends Entity {
	
	private boolean playable;


	public Enemy(int[] position) {
		super(position);
		this.setSprite('e');
		playable = false;
	}
	
	public Enemy(int[] position, boolean playable) {
		super(position);
		this.setSprite('E');
		this.playable = playable;
	}
	

	public boolean isPlayable() {
		return playable;
	}

	public void setPlayable(boolean playable) {
		this.playable = playable;
	}
	

}
