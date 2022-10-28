package com.sprint.game_text;

import java.util.Scanner;

public class Player {
	private int[] position = {0,0};
	private String name;
	private char sprite;
	Scanner input = new Scanner(System.in);
	
	public Player (String name, int x, int y) {
		 this.setName(name);
		 this.position[0] = x;
		 this.position[1] = y;
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
