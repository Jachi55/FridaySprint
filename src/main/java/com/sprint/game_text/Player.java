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
	
	
	public void movePlayer(char dir) {
		char direct = Character.toLowerCase(dir);
	
		switch (direct){
			
			case 'w':
				this.position[1]++;
				break;
				
			case 's':
				this.position[1]--;
				break;
				
			case 'a':
				this.position[0]--;
				break;
				
			case 'd':
				this.position[0]++;
				break;
				
				
		}
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
