package com.sprint.game_text;


public class Player {

	private int[] position;
	private String name;
	private char sprite;

	public Player (String name, int [] position) {
		 this.setName(name);
		 this.position = position;
		 this.setSprite('P');
	}
	
	


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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
