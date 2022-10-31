package com.sprint.game_text;

import java.util.Arrays;
import java.util.Scanner;

public class App {
	
  public static void main(String[] args) {
	  
	//======================== GAME SETUP ========================
    Scanner scanner = new Scanner (System.in);
    char move;
    int distanceFromTreasure;

    
    System.out.println("Welcome to Gridlock!!");
    System.out.println("You can make this grid as big as you like");
    System.out.println("How many rows would you like?");
    int rows = scanner.nextInt();

    System.out.println("How many columns would you like?");
    int columns = scanner.nextInt(); 
    Grid gameGrid = new Grid(rows,columns);
    
    System.out.println("What is your name?");
    String name = scanner.next() ;
    
    
    int [] playerLocation = generateCoordinates(rows, columns);
    Player player = new Player(name, playerLocation);
    int treasureLocation[] = generateCoordinates(rows,columns);
    while (Arrays.equals(playerLocation,treasureLocation)) {
    	treasureLocation = generateCoordinates(rows,columns);
    }
    
    Treasure treasure = new Treasure (treasureLocation);
    
    gameGrid.updateTile(treasure.getPosition(), treasure);
    gameGrid.updateTile(player.getPosition(), player);
    
    gameGrid.show();
    
    int [] pos;
    
    // GAME LOOP 
    
    while (true) {

    	if (Arrays.equals(player.getPosition(), treasure.getPosition())) {

    		
    		System.out.println("\n Congratulations you have found the treasure! YOU WIN!");
    		break;
    		
    	}
    	distanceFromTreasure = getDistance(player,treasure);
    	System.out.println("You are " + distanceFromTreasure + "m away from the Treasure" );
    	System.out.println("\n Use 'wsad' to move!");
    	move = scanner.next().charAt(0);
    	pos = player.getPosition();
    	
    	// Boundary Control 
    	
    	if ((pos[1] == columns-1  && move == 'd') || pos[1] <= 0 && move == 'a' || pos[0] == rows-1 && move == 's' || pos[0] <= 0 && move == 'w') {
    		
    		System.out.println("Out of Bounds!!");
    	}
    	
    	else {
    	
        player.movePlayer(move);
    	gameGrid.updateTile(player.getPosition(), player);
    	gameGrid.show();

    	
    }
    }
    
    
    scanner.close();
    
  }
  
  public static int [] generateCoordinates(int max_x, int max_y) {
	  double x = Math.random();
	  double y = Math.random();
	  x = x * max_x;
	  y = y * max_y;
	  
	  int [] coords = {(int) x, (int) y};
	  
	  return coords;
  }
  
  // Calculates Distance
  public static int getDistance(Entity e1, Entity e2) {
	  
	  int[] e1Position = e1.getPosition();
	  int[] e2Position = e2.getPosition();
	  
	  double[] distanceVector = {e1Position[0]-e2Position[0], e1Position[1]-e2Position[1]};
	  double absDistance = Math.sqrt( ( distanceVector[0] * distanceVector[0] ) + ( distanceVector[1] * distanceVector[1] ) );
	  
	  return (int) absDistance;
  }
}
