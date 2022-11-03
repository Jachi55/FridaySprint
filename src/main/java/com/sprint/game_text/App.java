package com.sprint.game_text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class App {

	private static ArrayList<Enemy> enemy = new ArrayList<Enemy>();
	private static ArrayList<Villager> villager = new ArrayList<Villager>();
	private static ArrayList<Treasure> treasure = new ArrayList<Treasure>();
	
	public static void main(String[] args) {

		// ======================== GAME SETUP ======================== //
		Scanner scanner = new Scanner(System.in);
		char move;
		int noOfTreasure;
		int noOfEnemies;
		int noOfVillagers;

		System.out.println("Welcome to Gridlock!!");
		System.out.println("You can make this grid as big as you like");
		System.out.print("How many rows would you like? ");
		int rows = scanner.nextInt();

		System.out.print("How many columns would you like? ");
		int columns = scanner.nextInt();
		Grid gameGrid = new Grid(rows, columns);

		System.out.print("How many pieces of treasure would you like to find? ");
		noOfTreasure = scanner.nextInt();
		System.out.print("How many enemies would you like to spawn? ");
		noOfEnemies = scanner.nextInt();
		System.out.print("How many villagers would you like to spawn? ");
		noOfVillagers = scanner.nextInt();

		System.out.print("What is your name? ");
		String name = scanner.next();
		
		System.out.println("Who would you like to play as? \n"
				+ "Player, Goblin or Minotaur");
		String characterV = scanner.next();

		int[] playerLocation = generateCoordinates(rows, columns);
		
		Player player;
		
		if (characterV.equalsIgnoreCase("Goblin")) {
			player = new Player(name, playerLocation, "Goblin");
		}
		else if (characterV.equalsIgnoreCase("Minotaur")) {
			player = new Player(name, playerLocation, "Minotaur");
		}
		
		else player = new Player(name, playerLocation);
		
		System.out.print("Welcome " + name + "! You are playing as a " + player.getRace() + ", shown on the map by the letter: " + player.getSprite());
		
		// Spawn Objects

		spawnTreasure(noOfTreasure, playerLocation, treasure, gameGrid);
		spawnVillager(noOfVillagers, playerLocation, villager, gameGrid);
		spawnEnemy(noOfEnemies, playerLocation, enemy, gameGrid);

		gameGrid.updateTile(player.getPosition(), player);

		gameGrid.show();
		

		int[] pos;
		int d;

		// ======================== GAME LOOP ======================== //

		while (true) {

			enemyCollide(enemy, player);
			villagerCollide(villager, player);
			treasureCollide(treasure, player);

			if (player.isWin() || !player.isAlive()) {
				break;
			}

			d = (int) Math.sqrt((rows ^ 2) + (columns ^ 2));
			for (Treasure t : treasure) {
				d = (int) Math.min(getDistance(player, t), d);
			}

			System.out.println("You are " + d + "m away from the closest Treasure");
			System.out.println("\n Use 'wasd' to move!");
			move = scanner.next().charAt(0);
			pos = player.getPosition();

			// Boundary Control

			if ((pos[1] == columns - 1 && move == 'd') || pos[1] <= 0 && move == 'a'
					|| pos[0] == rows - 1 && move == 's' || pos[0] <= 0 && move == 'w') {

				System.out.println("Out of Bounds!!");
			}

			else {

				player.movePlayer(move);

				updateTreasureTile(treasure, gameGrid);
				updateEnemyTile(enemy, gameGrid);
				updateVillagerTile(villager, gameGrid);
				gameGrid.updateTile(player.getPosition(), player);

				gameGrid.show();

			}
		}

		scanner.close();

	}

	// PUBLIC FUNCTIONS

	public static int[] generateCoordinates(int max_x, int max_y) {
		double x = Math.random();
		double y = Math.random();
		x = x * max_x;
		y = y * max_y;

		int[] coords = { (int) x, (int) y };

		return coords;
	}

	// Calculates Distance
	public static double getDistance(Entity e1, Entity e2) {

		int[] e1Position = e1.getPosition();
		int[] e2Position = e2.getPosition();

		double[] distanceVector = { e1Position[0] - e2Position[0], e1Position[1] - e2Position[1] };
		double absDistance = Math
				.sqrt((distanceVector[0] * distanceVector[0]) + (distanceVector[1] * distanceVector[1]));


		return absDistance;
	}
	
	public static int[] generateDirectionVector(int entityStepSize) {
		// Generate random position
		int xy_direction;
		int pm;
		int stepSize = entityStepSize;
		int[] newDirection = {0,0};
		
		// Generate new direction
		xy_direction = (int) (Math.random()*2); // Picks x or y
		pm = (int) (Math.random()*2); // Picks positive or negative
		int step = ((int) Math.pow(-1, pm)) * stepSize; // calculates the step
		newDirection[xy_direction] = step; // Updates new direction with the random x/y step
		
		return newDirection;
	}
	
	
	public static void randomMove(Entity e, Grid g, int attempts) {
		
		// Generate random position
		int maxTries = attempts;
		int[] newDirection = {0,0};
		int[] initialPos = e.getPosition();
		int[] newPos = {0,0};
		
		int calcCount = 0;
		
		boolean isValid = false;
		while (calcCount <= maxTries && !isValid) {
			System.out.println("Attempt " + calcCount + "/" + maxTries);
			
			isValid = true;
			
			// Generate new direction
			int[] dv = App.generateDirectionVector(1);
			newDirection[0] = dv[0];
			newDirection[1] = dv[1];
			
			//System.out.println("New direction is: " + newDirection[0] + ", " + newDirection[1]);
			
			// Add the direction to the current position
			newPos[0] = initialPos[0];
			newPos[1] = initialPos[1];
			
			newPos[0] += newDirection[0];
			newPos[1] += newDirection[1];
			
			//System.out.println("New Position is: " + newPos[0] + ", " + newPos[1]);
			
			// Run checks
			
			// Check position is valid
			// Boundary Check
			if (newPos[0] <= 0 || newPos[0] >= g.getRows()-1 || newPos[1] <= 0 || newPos[1] >= g.getColumns()-1) {
				//System.out.println("Boundary Check Failed");
				isValid = false;
			}
			
			// Collision Check with treasure
			for (Treasure t : treasure) {
				if (newPos == t.getPosition()) {
					//System.out.println("Treasure Collision");
					isValid = false;
					break;
				}
			}
			
			// Collision Check with villager
			for (Villager v : villager) {
				if (newPos == v.getPosition()) {
					//System.out.println("Villager Collision");
					isValid = false;
					break;
				}
			}
			
			// Collision Check with enemy
			for (Enemy en : enemy) {
				if (newPos == en.getPosition()) {
					//System.out.println("Enemy Collision");
					isValid = false;
					break;
				}
			}
			
			// Check if the position leads to any collisions
			if (isValid) {
				break;
			}
			
			calcCount++;
			
		}
		
		// Move entity
		e.setPrevPosition(e.getPosition());
		e.setPosition(newPos);
		
		// Update map position
		g.updateTile(e.getPosition(), e);
		
		//System.out.println("oldPos: " + e.getPosition()[0] + "," + e.getPosition()[1]);
		//System.out.println("newPos: " + newPos[0] + "," + newPos[1]);
		//System.out.println("Move G from: " + e.getPrevPosition()[0] + "," + e.getPrevPosition()[1] + " to: " + e.getPosition()[0] + "," + e.getPosition()[1]);

	}

// Spawn Objects

	public static void spawnEnemy(int noOfObjects, int[] playerLocation, ArrayList<Enemy> e, Grid grid) {
		int i = 0;
		int[] location;
		while (i < noOfObjects) {
			location = generateCoordinates(grid.getRows(), grid.getColumns());

			while (Arrays.equals(playerLocation, location)) {
				location = generateCoordinates(grid.getRows(), grid.getColumns());
			}
			e.add(new Enemy(location));
			grid.updateTile(e.get(i).getPosition(), e.get(i));
			i++;
		}
	}

	public static void spawnTreasure(int noOfObjects, int[] playerLocation, ArrayList<Treasure> e, Grid grid) {
		int i = 0;
		int[] location;
		while (i < noOfObjects) {
			location = generateCoordinates(grid.getRows(), grid.getColumns());

			while (Arrays.equals(playerLocation, location)) {
				location = generateCoordinates(grid.getRows(), grid.getColumns());
			}
			e.add(new Treasure(location));
			grid.updateTile(e.get(i).getPosition(), e.get(i));
			i++;
		}
	}

	public static void spawnVillager(int noOfObjects, int[] playerLocation, ArrayList<Villager> e, Grid grid) {
		int i = 0;
		int[] location;
		while (i < noOfObjects) {
			location = generateCoordinates(grid.getRows(), grid.getColumns());

			while (Arrays.equals(playerLocation, location)) {
				location = generateCoordinates(grid.getRows(), grid.getColumns());
			}
			e.add(new Villager(location));
			grid.updateTile(e.get(i).getPosition(), e.get(i));
			i++;
		}
	}

	// Check Player Collision

	public static void treasureCollide(ArrayList<Treasure> treasure, Player player) {
		for (Treasure t : treasure) {

			if (Arrays.equals(player.getPosition(), t.getPosition())) {

				player.setScore(player.getScore() + t.value);

				if (treasure.size() != 1) {
					treasure.remove(t);
					System.out.println("Congratulations you have found the treasure! There are " + treasure.size()
							+ " pieces left on the map!");
					break;
				} else {
					System.out.println("Congratulations you have found all the treasure! YOU WIN!");
					System.out.println(player.getName() + "'s score is: " + player.getScore());
					player.setWin(true);
					break;
				}
			}
		}
	}

	public static void enemyCollide(ArrayList<Enemy> enemy, Player player) {
		for (Enemy e : enemy) {

			if (Arrays.equals(player.getPosition(), e.getPosition())) {

				System.out.println("An Enemy has caught you!! You Lose!!");

				player.setAlive(false);
				break;

			}
		}
	}

	public static void villagerCollide(ArrayList<Villager> villager, Player player) {
		for (Villager v : villager) {

			if (Arrays.equals(player.getPosition(), v.getPosition())) {

				player.setScore(player.getScore() + v.value);

				System.out.println("You have saved a villager!!");
				villager.remove(v);
				break;

			}
		}
	}

	// Update Tiles

	public static void updateTreasureTile(ArrayList<Treasure> treasure, Grid grid) {
		for (Treasure t : treasure) {
			grid.updateTile(t.getPosition(), t);
		}
	}

	public static void updateEnemyTile(ArrayList<Enemy> enemy, Grid grid) {
		for (Enemy e : enemy) {
			grid.updateTile(e.getPosition(), e);
		}
	}

	public static void updateVillagerTile(ArrayList<Villager> villager, Grid grid) {
		for (Villager v : villager) {
			grid.updateTile(v.getPosition(), v);
		}
	}

}
