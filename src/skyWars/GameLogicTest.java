package skyWars;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

class GameLogicTest {
	private GameLogic gameLogic = new GameLogic();
	
	@Test
	void testMasterShipSpawn() {
		int min = 2;
		int max = 16;
		int actual = gameLogic.masterShipSpawn();
		assertTrue(min <= actual && actual <= max);
	}
	
	
	@Test
	void testEnemyShipSpawn() {
		int min = 0;
		int max = 1;
		int actual = gameLogic.enemyShipSpawn();
		assertTrue(min <= actual && actual <= max);
	}
	
	@Test
	void testMoveMasterShip() {
		int min = 1;
		int max = 16;
		gameLogic.masterShipSpawn();
		gameLogic.moveMasterShip();
		int actual = gameLogic.getMasterShipTile().getTileId();
		assertTrue(min <= actual && actual <= max);
	}
	
	@Test
	void testMoveEnemyShips() {
		gameLogic.setBattleStarTile(new GameTile(1));
		gameLogic.setBattleCruiserTile(new GameTile(1));
		gameLogic.setBattleShooterTile(new GameTile(1));
		
		HashSet<Ship> expected = new HashSet<Ship>();
		expected.add(new BattleStar());
		expected.add(new BattleCruiser());
		expected.add(new BattleShooter());
		
		HashSet<Ship> actual = new HashSet<Ship>();
		gameLogic.moveEnemyShips();
		actual.addAll(gameLogic.getBattleStarTile().getListOfShipsOnTile());
		actual.addAll(gameLogic.getBattleCruiserTile().getListOfShipsOnTile());
		actual.addAll(gameLogic.getBattleShooterTile().getListOfShipsOnTile());
		
		assertEquals(expected.size(), actual.size());
	}
	
	@Test
	void testConflictResolution() {
		int min = 0;
		int max = 4;
		gameLogic.setMasterShipTile(new GameTile(1));
		
		int actual = gameLogic.conflictResolution();
		
		assertTrue(min <= actual && actual <= max);
	}
	
	@Test
	void testMasterShipWinsResolution() {
		int expected = 2;
		gameLogic.setMasterShipTile(new GameTile(1));
		gameLogic.getMasterShipTile().addShipToList(new BattleStar());
		int actual = gameLogic.masterShipWinsResolution();
		assertEquals(expected, actual);
	}
	
	@Test
	void testIncreaseHighScore() {
		int expected = 1000;
		gameLogic.setScore(1000);
		gameLogic.setHighScore(0);
		gameLogic.increaseHighScore();
		int actual = gameLogic.getHighScore();
		assertEquals(expected, actual);
	}
	
	@Test
	void testMasterShipDestroyedResolution() {
		int expected = 1;
		int actual = gameLogic.masterShipDestroyedResolution();
		assertEquals(expected, actual);
	}
	
}
