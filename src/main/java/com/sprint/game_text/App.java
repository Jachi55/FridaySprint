package com.sprint.game_text;

import java.util.Scanner;

public class App {
	private double distanceFromTreasure = 0;
	
  public static void main(String[] args) {
	  
	//======================== GAME SETUP ========================
    Scanner scanner = new Scanner (System.in);

    
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
    while (treasureLocation == playerLocation) {
    	treasureLocation = generateCoordinates(rows,columns);
    }
    
    Treasure treasure = new Treasure (treasureLocation);
    
    gameGrid.show();
    
  }
  
  public static int [] generateCoordinates(int max_x, int max_y) {
	  double x = Math.random();
	  double y = Math.random();
	  
	  int [] coords = {(int) x * max_x, (int) y * max_y};
	  
	  return coords;
  }
  
  // Calculates Distance
  public static double getDistance(Entity e1, Entity e2) {
	  
	  int[] e1Position = e1.getPosition();
	  int[] e2Position = e2.getPosition();
	  
	  int[] distanceVector = {e1Position[0]-e2Position[0], e1Position[0]-e2Position[0]};
	  double absDistance = Math.sqrt( ( distanceVector[0] ^ 2  ) + ( distanceVector[0] ^ 2 ) );
	  
	  return absDistance;
  }
}
