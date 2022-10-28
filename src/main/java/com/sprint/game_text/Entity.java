package com.sprint.game_text;

public class Entity {

	protected int[] position;
	private char sprite;

	public Entity(int[] position) {
		this.position = position;
	}

	public int[] getPosition() {
		return this.position;
	}

	public void setPosition(int[] new_position) {
		this.position = new_position;
	}

	public char getSprite() {
		return sprite;
	}

	public void setSprite(char sprite) {
		this.sprite = sprite;
	}

}
