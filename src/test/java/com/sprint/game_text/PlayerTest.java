package com.sprint.game_text;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Player Tests")
class PlayerTest {
	
	int[] init_pos = {0,0};
	private Player testPlayer = new Player("testPlayer",init_pos);

	@Test
	@DisplayName("Player initialisation")
	void playerInitTest() {
		assertEquals("testPlayer", this.testPlayer.getName());
		int[] pos = {2,3};
		assertEquals(pos[0], this.testPlayer.getPosition()[0]);
		assertEquals(pos[1], this.testPlayer.getPosition()[1]);
	}
	
	@Test
	@DisplayName("Name change")
	void nameChange() {
		String newName = "gandalf";
		this.testPlayer.setName(newName);
		assertEquals(newName, this.testPlayer.getName());
	}
	
	@Test
	@DisplayName("Position change")
	void positionChange() {
		int[] newPos = {1, 1};
		this.testPlayer.setPosition(newPos);;
		assertEquals(newPos, this.testPlayer.getPosition());
	}
	
	@Test
	@DisplayName("Sprite change")
	void spriteChange() {
		char newSprite = '@';
		this.testPlayer.setSprite(newSprite);;
		assertEquals(newSprite, this.testPlayer.getSprite());
	}

}
