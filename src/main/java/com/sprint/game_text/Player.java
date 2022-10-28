package com.sprint.game_text;


public class Player extends Entity {

	private String name;

	public Player (String name, int [] position) {
		 super(position);
		 this.name = name;
		 this.setSprite('P');
	}
	
	


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	

}
