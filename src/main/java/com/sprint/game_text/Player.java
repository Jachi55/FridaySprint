package com.sprint.game_text;


public class Player extends Entity {

	private String name;

	public Player (String name, int [] position) {
		 super(position);
		 this.name = name;
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
	

}
