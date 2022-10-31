package com.sprint.game_text;

import java.util.Random;

public abstract class Enemy extends Entity {

	protected static int instanceCounter = 0;
	protected String race;

	public Enemy(int[] position) {
		super(position);
		this.setSprite('E');
		instanceCounter++;
	}

	public void enemyMove(Grid grid) {

		int rnd;
		int rows = grid.getRows();
		int columns = grid.getColumns();
		
		int x = position[0];
		int y = position[1];
		if ((x == 1) || (x==rows)) {
			rnd = 1;
		}
		else if((y==1) || (y==columns)){
			rnd = 0; 
		}
		else {
			rnd = new Random().nextInt(position.length) -1;
		}
		int positionChange = position[rnd];
		int randomOfTwoInts = new Random().nextBoolean() ? 1 : -1;
		
		this.setPrevPosition(this.getPosition());
		this.position[rnd] = (positionChange + randomOfTwoInts);
		
		grid.updateTile(position, this);
	}

}
