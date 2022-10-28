package com.sprint.game_text;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
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
    int [] location = generateCoordinates(rows, columns);
    Player player = new Player(name, location);
    
  
    
  }
  
  
  public static int [] generateCoordinates(int max_x, int max_y) {
	  double x = Math.random();
	  double y = Math.random();
	  
	  int [] coords = {(int) x * max_x, (int) y * max_y};
	  
	  
	  return coords;
  }
}
