package com.sprint.game_text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class App {
	
	public static int score = 0;

	private static ArrayList<Entity> enemy = new ArrayList<Entity>();

	public static void main(String[] args) {

		// ======================== GAME SETUP ======================== //
		Scanner scanner = new Scanner(System.in);
		char move;
		int noOfTreasure;
		int noOfEnemies;
		int noOfVillagers;
		ArrayList<Entity> villager = new ArrayList<Entity>();
		ArrayList<Entity> treasure = new ArrayList<Entity>();

		System.out.println("Welcome to Gridlock!!");
		System.out.println("You can make this grid as big as you like");
		System.out.println("How many rows would you like?");
		int rows = scanner.nextInt();

		System.out.println("How many columns would you like?");
		int columns = scanner.nextInt();
		Grid gameGrid = new Grid(rows, columns);

		System.out.println("How many pieces of treasure would you like to find?");
		noOfTreasure = scanner.nextInt();
		System.out.println("How many enemies would you like to spawn?");
		noOfEnemies = scanner.nextInt();
		System.out.println("How many villagers would you like to spawn?");
		noOfVillagers = scanner.nextInt();

		System.out.println("What is your name?");
		String name = scanner.next();

		int[] playerLocation = generateCoordinates(rows, columns);
		Player player = new Player(name, playerLocation);

		// Spawn Objects

		spawnEntity(noOfTreasure, playerLocation, treasure, gameGrid);
		spawnEntity(noOfVillagers, playerLocation, villager, gameGrid);
		spawnEntity(noOfEnemies, playerLocation, enemy, gameGrid);

		gameGrid.updateTile(player.getPosition(), player);

		gameGrid.show();

		int[] pos;
		int d;

		// ======================== GAME LOOP ======================== //

		while (true) {

			
			enemyCollide(enemy,player);
			villagerCollide(villager,player);
			treasureCollide(treasure,player);
			
			if (player.isWin() || !player.isAlive()) {
				break;
			}

			d = (int) Math.sqrt((rows ^ 2) + (columns ^ 2));
			for (Entity t : treasure) {
				d = Math.min(getDistance(player, t), d);
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
				

				updateEntityTile(treasure, gameGrid);
				updateEntityTile(enemy, gameGrid);
				updateEntityTile(villager, gameGrid);
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
	public static int getDistance(Entity e1, Entity e2) {

		int[] e1Position = e1.getPosition();
		int[] e2Position = e2.getPosition();

		double[] distanceVector = { e1Position[0] - e2Position[0], e1Position[1] - e2Position[1] };
		double absDistance = Math
				.sqrt((distanceVector[0] * distanceVector[0]) + (distanceVector[1] * distanceVector[1]));

		return (int) absDistance;
	}
	
	
	public static void randomMove(Entity e, Grid g) {
		
		// Generate random position
		int direction;
		int step;
		int[] newPos;
		
		while (true) {
			direction = (int) Math.random(); // Picks x or y
			step = (int) ((Math.random() * 2) - 1); // Random number between -1 and 1
			newPos = e.getPosition();
			newPos[direction] += step; // Updates new position with random x/y step
			
			// Check position is valid
			// Boundary Check
			if (newPos[0] == 0 || newPos[0] == g.getRows()-1 || newPos[1] == 0 || newPos[1] == g.getColumns()-1) {
				continue;
				
			}
			
			// Collision Check with treasure
			for (Treasure t : treasure) {
				if (newPos == t.getPosition()) {
					continue;
					
				} else {
					break;
				}
			}
			
			// Check collision with NPCs and other Entities
			
		}
		
		
		// Move entity
		e.setPrevPosition(e.getPosition());
		e.setPosition(newPos);
		
		// Update map position
		g.updateTile(e.getPosition(), e);
	}

// Spawn Objects

	public static void spawnEntity(int noOfObjects, int[] playerLocation, ArrayList<Entity> e, Grid grid) {
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

	// Check Player Collision
	
	public static void treasureCollide (ArrayList <Entity> treasure, Player player) {
		for (Entity t : treasure) {

			if (Arrays.equals(player.getPosition(), t.getPosition())) {

				if (treasure.size() != 1) {
					treasure.remove(t);
					System.out.println("Congratulations you have found the treasure! There are " + treasure.size()
							+ " pieces left on the map!");
					break;
				} else {
					System.out.println("Congratulations you have found all the treasure! YOU WIN!");
					player.setWin(true);
					break;
				}
			}
		}
	}
	
	public static void enemyCollide (ArrayList <Entity> enemy, Player player) {
		for (Entity e : enemy) {

			if (Arrays.equals(player.getPosition(), e.getPosition())) {
				
				System.out.println("An Enemy has caught you!! You Lose!!");
				
				player.setAlive(false);
				break;

			}
		}
	}
	
	public static void villagerCollide (ArrayList <Entity> villager, Player player) {
		for (Entity v : villager) {

			if (Arrays.equals(player.getPosition(), v.getPosition())) {
				
				System.out.println("You have saved a villager!!");
				villager.remove(v);
				break;

			}
		}
	}
	
	public static void updateEntityTile (ArrayList <Entity> entity, Grid grid) {
		for (Entity e : entity) {
			grid.updateTile(e.getPosition(), e);
		}
	}
	public static void updateEnemyTile (ArrayList <Enemy> enemy, Grid grid) {
		for (Enemy e : enemy) {
			grid.updateTile(e.getPosition(), e);
		}
	}
	public static void updateVillagerTile (ArrayList <Villager> villager, Grid grid) {
		for (Villager v : villager) {
			grid.updateTile(v.getPosition(), v);
		}
	}



}

