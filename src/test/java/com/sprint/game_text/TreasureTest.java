package com.sprint.game_text;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TreasureTest {
	int[] pos = {3,3};
	private Treasure treasure = new Treasure(pos);
	
	@Test
	@DisplayName("Treasure initialisation")
	void playerInitTest() {
		assertEquals(pos[0], treasure.getTreasurePosition()[0]);
		assertEquals(pos[1], treasure.getTreasurePosition()[1]);
	}
}
