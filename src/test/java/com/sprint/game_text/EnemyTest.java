package com.sprint.game_text;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EnemyTest {
	int[] array = {2,2};
	Enemy enemy = new Enemy(array);
	
	@Test
	@DisplayName("Create general enemy")
	void positionChange() {
		int[] array = {2,3};
		Enemy enemy = new Enemy(array);
		assertEquals(2,enemy.getPosition()[0], "Enemy X coord is not as expected");
		assertEquals(3,enemy.getPosition()[1], "Enemy Y coord is not as expected");
	}

}
