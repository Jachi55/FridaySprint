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
	
    @Test
    @DisplayName("Distance Calculations")
    public void distanceCalculations() {
    	double dist = Math.sqrt( ( (pos1[0]-pos2[0])^2 ) + ( (pos1[1]-pos2[1])^2 ) );
    	assertEquals(dist, App.getDistance(p1, t1));
    }
}
