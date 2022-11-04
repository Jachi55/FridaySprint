package com.sprint.game_text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

		int maxNumber = (gameGrid.getRows() * gameGrid.getColumns())-1;
		
		
		System.out.print("How many pieces of treasure would you like to find?\n"
				+ "Enter a number between: " + "1 and " + maxNumber);
		noOfTreasure = scanner.nextInt();
		System.out.print("How many enemies would you like to spawn?\n"
				+ "Enter a number between: " + "0 and " + (maxNumber - noOfTreasure));
		noOfEnemies = scanner.nextInt();
		System.out.print("How many villagers would you like to spawn?\n"
				+ "Enter a number between: " + "0 and " + (maxNumber - noOfTreasure - noOfEnemies));
		noOfVillagers = scanner.nextInt();

		System.out.print("What is your name? ");
		String name = scanner.next();

		int[] playerLocation = generateCoordinates(rows, columns);


		Player player = new Player(name, playerLocation);
		
		// TEST
		//int[] gobTestLoc = generateCoordinates(rows, columns);
		//Goblin gTest = new Goblin(gobTestLoc);
		//gTest.setSprite('G');
		//gameGrid.updateTile(gobTestLoc, gTest);
		// TEST

		// Add Playable Enemy to Map

		if (noOfEnemies != 0) {
			System.out.println("Do you want to play as an enemy as well? y/n");
			char ans = scanner.next().charAt(0);
			ans = Character.toLowerCase(ans);
			if (ans == 'y') {
				int[] ePlayerLocation = generateCoordinates(rows, columns);
				Enemy playEnemy = new Enemy(ePlayerLocation, true);

				enemy.add(playEnemy);
				gameGrid.updateTile(playEnemy.getPosition(), playEnemy);
			}
		}


		spawnTreasure(noOfTreasure, playerLocation, treasure, gameGrid);
		spawnVillager(noOfVillagers, playerLocation, villager, gameGrid);
		spawnEnemy(noOfEnemies, playerLocation, enemy, gameGrid);

		gameGrid.updateTile(player.getPosition(), player);

		gameGrid.show();

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
			System.out.println("\n You are controlling " + player.getName() + " 'wasd' to move!");
			move = scanner.next().charAt(0);

			if ((player.getPosition()[1] == columns - 1 && move == 'd') || player.getPosition()[1] <= 0 && move == 'a'
					|| player.getPosition()[0] == rows - 1 && move == 's'
					|| player.getPosition()[0] <= 0 && move == 'w') {

				System.out.println("Out of Bounds!!");
			}

			else {

				player.movePlayer(move);
				
				// TEST
				//App.randomMove(gTest, gameGrid, 5); // TEST
				//App.pathFind(gTest, player, gameGrid); // TEST
				// TEST
				
				updateTreasureTile(treasure, gameGrid);
				updateEnemyTile(enemy, gameGrid);
				updateVillagerTile(villager, gameGrid);
				gameGrid.updateTile(player.getPosition(), player);

				// Check for playable enemies and move accordingly
				for (Enemy e : enemy) {
					if (e.isPlayable()) {
						System.out.println("\n You are controlling an Enemy use 'wasd' to move!");
						char eMove = scanner.next().charAt(0);


						e.moveEntity(eMove, gameGrid);
					} else {
						pathFind(e, player, gameGrid);
					}
				}
				for (Villager v : villager) {
					
					randomMove(v, gameGrid, 5);
				}

			}

			// for each Villager v in villager , randomMove();

			updateTreasureTile(treasure, gameGrid);
			updateVillagerTile(villager, gameGrid);
			updateEnemyTile(enemy, gameGrid);
			gameGrid.updateTile(player.getPosition(), player);


			gameGrid.show();

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
		int[] newDirection = { 0, 0 };

		// Generate new direction
		xy_direction = (int) (Math.random() * 2); // Picks x or y
		pm = (int) (Math.random() * 2); // Picks positive or negative
		int step = ((int) Math.pow(-1, pm)) * stepSize; // calculates the step
		newDirection[xy_direction] = step; // Updates new direction with the random x/y step

		return newDirection;

	}
	
	public static void pathFind(Entity finder, Entity target, Grid g) {
		
		// Get starting position of finder
		int[] finderCurrentPosition = finder.getPosition();
		
		// Generate new positions for each direction
		// Direction vectors
		int[] up = {0, -1};
		int[] down = {0, 1};
		int[] left = {-1, 0};
		int[] right = {1, 0};
		
		int[][] directions = new int[4][2];
		
		directions[0][0] = up[0];
		directions[0][1] = up[1];
		
		directions[1][0] = down[0];
		directions[1][1] = down[1];
		
		directions[2][0] = left[0];
		directions[2][1] = left[1];
		
		directions[3][0] = right[0];
		directions[3][1] = right[1];
		
		// New positions
		HashMap<Integer, int[]> validPositions = new HashMap<Integer, int[]>();
		
		// Loop through and run validity checks in parallel
		for (int i = 0; i < 4; i++) {
			// Calculate the new position
			int[] newPos = {0,0};
			newPos[0] = finderCurrentPosition[0] + directions[i][0];
			newPos[1] = finderCurrentPosition[1] + directions[i][1];
			
			// Check if it is valid
			if (App.isPositionValid(newPos, g)) {
				validPositions.put(i, newPos);
			}
			
		}
		
		if (validPositions.size() == 0) {
			return;
		}
		
		// Check the distances from each new position to the entity to find
		double[] distances = new double[validPositions.size()];
		Entity mockFinder = new Entity(validPositions.get(0));
		double shortestDistance = Math.max((double) g.getColumns(), (double) g.getRows()); // Start with a big value
		int shortestIndex = 0;
		
		for (int i = 0; i < distances.length; i++) {
			
			mockFinder.setPosition(validPositions.get(i));
			double dist = App.getDistance(mockFinder, target);
			distances[i] = dist;
			
			if (dist <= shortestDistance) {
				shortestDistance = dist;
				shortestIndex = i;
			}
			
		}
		
		// Select the position which is closest to the target and move the finder
		finder.setPrevPosition(finder.getPosition());
		finder.setPosition(validPositions.get(shortestIndex));
				
		// Update finder map position
		g.updateTile(finder.getPosition(), finder);
	}
	
	public static boolean isPositionValid(int[] pos, Grid g) {
		
		// Boundary Check
		if (pos[0] < 0 || pos[0] > g.getRows()-1 || pos[1] < 0 || pos[1] > g.getColumns()-1) {
			//System.out.println("Boundary Check Failed");
			return false;
		}
		
		// Collision Check with treasure
		for (Treasure t : treasure) {
			if (pos == t.getPosition()) {
				//System.out.println("Treasure Collision");
				return false;
			}
		}
					
		// Collision Check with villager
		for (Villager v : villager) {
			if (pos == v.getPosition()) {
				//System.out.println("Villager Collision");
				return false;
			}
		}
					
		// Collision Check with enemy
		for (Enemy en : enemy) {
			if (pos == en.getPosition()) {
				//System.out.println("Enemy Collision");
				return false;
			}
		}
		
		return true;
	}
	

	public static void randomMove(Entity e, Grid g, int attempts) {

		// Generate random position
		int maxTries = attempts;
		int[] newDirection = { 0, 0 };
		int[] initialPos = e.getPosition();
		int[] newPos = { 0, 0 };

		int calcCount = 0;

		boolean isValid = false;
		while (calcCount <= maxTries && !isValid) {
			// System.out.println("Attempt " + calcCount + "/" + maxTries);

			isValid = true;

			// Generate new direction
			int[] dv = App.generateDirectionVector(1);
			newDirection[0] = dv[0];
			newDirection[1] = dv[1];

			// System.out.println("New direction is: " + newDirection[0] + ", " +
			// newDirection[1]);

			// Add the direction to the current position
			newPos[0] = initialPos[0];
			newPos[1] = initialPos[1];

			newPos[0] += newDirection[0];
			newPos[1] += newDirection[1];

			// System.out.println("New Position is: " + newPos[0] + ", " + newPos[1]);

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
					// System.out.println("Treasure Collision");
					isValid = false;
					break;
				}
			}

			// Collision Check with villager
			for (Villager v : villager) {
				if (newPos == v.getPosition()) {
					// System.out.println("Villager Collision");
					isValid = false;
					break;
				}
			}

			// Collision Check with enemy
			for (Enemy en : enemy) {
				if (newPos == en.getPosition()) {
					// System.out.println("Enemy Collision");
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

		// System.out.println("oldPos: " + e.getPosition()[0] + "," +
		// e.getPosition()[1]);
		// System.out.println("newPos: " + newPos[0] + "," + newPos[1]);
		// System.out.println("Move G from: " + e.getPrevPosition()[0] + "," +
		// e.getPrevPosition()[1] + " to: " + e.getPosition()[0] + "," +
		// e.getPosition()[1]);

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
