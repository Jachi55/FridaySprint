package com.sprint.game_text;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

@DisplayName("App Tests")
public class AppTest {
	
	int[] pos1 = {0,0};
	int[] pos2 = {2,2};
	  
	Player p1 = new Player("Player1", pos1);
	Treasure t1 = new Treasure(pos2);
	Grid g = new Grid(20, 20);
	
    @Test
    @DisplayName("Distance Calculations")
    public void distanceCalculations() {
    	System.out.println("Pos1: " + pos1[0] + "," + pos1[1]);
    	System.out.println("Pos2: " + pos2[0] + "," + pos2[1]);
    	double[] distVec = {pos1[0]-pos2[0], pos1[1]-pos2[1]};
    	double dist = Math.sqrt( (distVec[0] * distVec[0]) + (distVec[1] * distVec[1]) );
    	assertEquals(dist, App.getDistance(p1, t1));
    }
    
    @Test
    @DisplayName("Random Movement")
    public void randomMovement() {
    	System.out.println("\n\n");
;    	p1.setPosition(pos1);
    	Player testPrevPos = new Player("test", p1.getPosition());
    	
    	App.randomMove(p1, g);
    	assertTrue(2 >= App.getDistance(p1, testPrevPos) );
    }
}
