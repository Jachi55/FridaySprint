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
		prevPosition = this.position.clone();
	
		switch (direct){
			
			case 'w':
				this.position[0]--;
				System.out.println(name + " moved up one space");
				break;
				
			case 's':
				this.position[0]++;
				System.out.println(name + " moved down one space");
				break;
				
			case 'a':
				this.position[1]--;
				System.out.println(name + " moved left one space");
				break;
				
			case 'd':
				this.position[1]++;
				System.out.println(name + " moved right one space");
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
