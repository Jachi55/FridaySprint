package com.sprint.game_text;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;

@DisplayName("App Tests")
public class AppTest {
	
	int[] pos1 = {0, 0};
	int[] pos2 = {2, 2};
	int[] pos3 = {3, 3};
	int[] pos4 = {7, 7};
	  
	Player p1 = new Player("Player1", pos1);
	Treasure t1 = new Treasure(pos2);
	Villager v1 = new Villager(pos3);
	Enemy e1 = new Enemy(pos4);
	
	Grid g = new Grid(20, 20);
	
    @Test
    @DisplayName("Distance Calculations")
    public void distanceCalculations() {
    	System.out.println("\nDistance Calculations Test");
    	// Set positions
    	p1.setPosition(pos1);
    	t1.setPosition(pos2);
    	System.out.println("P1 Position: " + p1.getPosition()[0] + "," + p1.getPosition()[1]);
    	System.out.println("T1 Position: " + t1.getPosition()[0] + "," + t1.getPosition()[1]);
  
    	// Calculate test distance
    	double[] distVec = {pos1[0]-pos2[0], pos1[1]-pos2[1]};
    	double dist = Math.sqrt( (distVec[0] * distVec[0]) + (distVec[1] * distVec[1]) );
    	System.out.println(dist);
    	System.out.println(App.getDistance(p1, t1));
    	assertEquals(dist, App.getDistance(p1, t1));
    }
    
    @Test
    @DisplayName("Generate a Direction Vector")
    public void directionVector() {
    	int step = 1;
    	int[] directionVector = App.generateDirectionVector(step);
    	
    	assertTrue( step >= Math.abs( directionVector[0] ) );
    	assertTrue( step >= Math.abs( directionVector[1] ) );
    }
	
}