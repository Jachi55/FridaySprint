package com.sprint.game_text;

public class Villager extends Entity{
	private String name;
	private int ScoreValue = 10;
	
	public Villager(int[] position, String name) {
		super(position);
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String new_name) {
		this.name = new_name;
	}
	
	public int getScoreValue() {
		return this.ScoreValue;
	}
	
}
