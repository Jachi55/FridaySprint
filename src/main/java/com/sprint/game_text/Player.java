package com.sprint.game_text;

public class Player extends Entity {

	private int playerScore;
	private String name;
	private boolean isAlive;
	private boolean win;

	public Player(String name, int[] position) {
		super(position);
		this.name = name;
		setAlive(true);
		setWin(false);
		this.setSprite('P');
	}

	public void movePlayer(char dir) {
		prevPosition = this.position.clone();
		char direct = Character.toLowerCase(dir);

		switch (direct) {

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

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public int getScore() {
		return playerScore;
	}

	public void setScore(int newScore) {
		this.playerScore = newScore;
	}
}
