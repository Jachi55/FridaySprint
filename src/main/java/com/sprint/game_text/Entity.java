package com.sprint.game_text;

public class Entity {

	protected String race;
	protected int value = 0;
	protected int[] position;
	protected int[] prevPosition;
	private char sprite;

	public Entity(int[] position) {
		this.position = position;
		prevPosition = this.position.clone();
	}

	public void moveEntity(char dir, Grid grid) {
		char direct = Character.toLowerCase(dir);
		prevPosition = this.position.clone();
		boolean valid = true;
		

		if ((this.getPosition()[1] == grid.getColumns() - 1 && direct == 'd')
				|| this.getPosition()[1] <= 0 && direct == 'a'
				|| this.getPosition()[0] == grid.getRows() - 1 && direct == 's'
				|| this.getPosition()[0] <= 0 && direct == 'w') {

			System.out.println("Out of Bounds!!");
			valid = false;
		}

		if (valid) {

			switch (direct) {

			case 'w':
				this.position[0]--;
				System.out.println("Moved up one space");
				break;

			case 's':
				this.position[0]++;
				System.out.println("Moved down one space");
				break;

			case 'a':
				this.position[1]--;
				System.out.println("Moved left one space");
				break;

			case 'd':
				this.position[1]++;
				System.out.println("Moved right one space");
				break;

			}
		}
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

	public int getValue() {
		return value;
	}

	public String getRace() {
		return race;
	}
}
