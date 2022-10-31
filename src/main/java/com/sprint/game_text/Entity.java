package com.sprint.game_text;

public class Entity {

	protected int[] position;
	protected int[] prevPosition;
	private char sprite;
	
	public Entity(int [] position) {
		this.position = position;
		prevPosition = this.position.clone();
		
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

	public int[] getPrevPosition() {
		return prevPosition;
	}

	public void setPrevPosition(int[] prevPosition) {
		this.prevPosition = prevPosition;
	}
	

	
	
}
