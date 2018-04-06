package skyWars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ButtonsLogic {
	
	private int rngMin; 
	private int rngMax;
	private int masterShipSpawnTile;
	private int enemyShipSpawnTile;
	private int chanceOfEnemySpawn;
	private int nextTile;
	private int enemyType;
	private Random randomNumbersGenerator = new Random();
	private RowsGrid sky = new RowsGrid();
	private GameTile masterShipTile;
	private GameTile battleStarTile;
	private GameTile battleCruiserTile;
	private GameTile battleShooterTile;
	private Ship ship;
	private ArrayList<GameTile> listOfTiles = sky.getArrayListOfTiles();
	private MasterSpaceShip masterSpaceShip = new MasterSpaceShip();
	private HashMap<Integer, ArrayList<Integer>> legalMovesMap = sky.getLegalMovesMap();
	private ArrayList<Integer> movesList = new ArrayList<Integer>();
	private ArrayList<Integer> nextEnemyShipsMoves = new ArrayList<Integer>();
		
	
	public ButtonsLogic () {		
		
		// nextInt(max - min + 1) + min to calculate random number in range min:max
		//System.out.println(randomNumbersGenerator.nextInt(rngMax - rngMin + 1) + rngMin); 
	}
	
	// call when reset button is pressed
	public int masterShipSpawn() {		
		rngMin = 2; // master ship shouldn't be able to spawn on tile01 where enemy ships come from
		rngMax = 16;
		masterShipSpawnTile = randomNumbersGenerator.nextInt(rngMax - rngMin + 1) + rngMin;		
		// get tile ship is in	
		for (GameTile t : listOfTiles) {
			if (t.getTileId() == masterShipSpawnTile) {				
				this.masterShipTile = t;				
			}
		}
		System.out.println("Start at tile " + masterShipSpawnTile);
		return masterShipSpawnTile;
	}
	
	// call when reset button is pressed
	public int enemyShipSpawn() {
		rngMin = 1;
		rngMax = 3;		
		chanceOfEnemySpawn = randomNumbersGenerator.nextInt(rngMax - rngMin + 1) + rngMin;
		if (chanceOfEnemySpawn == 1) {
			this.setEnemyType(randomNumbersGenerator.nextInt(rngMax - rngMin + 1) + rngMin);
			switch(this.enemyType) {
				case 1: 
					if (battleStarTile == null) {
						System.out.println(battleStarTile == null);
						System.out.println("Make fighter");
						this.battleStarTile = listOfTiles.get(0);
						enemyShipSpawnTile = 1;
					}
					break;
				case 2:
					if (battleCruiserTile == null) {
						System.out.println(battleCruiserTile == null);
						System.out.println("Make corvette");
						this.battleCruiserTile = listOfTiles.get(0);
						enemyShipSpawnTile = 1;
					}
					break;
				case 3: 
					if (battleShooterTile == null) {
						System.out.println(battleShooterTile == null);
						System.out.println("Make Gunship");
						this.battleShooterTile = listOfTiles.get(0);
						enemyShipSpawnTile = 1;
					}
					break;
			}
		} else {
			enemyShipSpawnTile = 0;
		}
		return enemyShipSpawnTile;
	}
	
	// call when move button is pressed
	public int moveMasterShip() {
		int masterShipTileId = this.masterShipTile.getTileId();		
		movesList = legalMovesMap.get(masterShipTileId);
		//System.out.println("Legal Moves: " + movesList);
		// get random legal tile for next move
		int nextTile = movesList.get(randomNumbersGenerator.nextInt(movesList.size()));
		//System.out.println("Move to tile: " + nextTile);
		// get tile ship is in	
			for (GameTile t : listOfTiles) {
				if (t.getTileId() == nextTile) {
					this.masterShipTile = t;
				}
			}
		
		return nextTile;
	}
	
	// call when move buton is pressed
	public void moveEnemyShips() {
				
		if (this.battleStarTile != null) {
			int battleStarTileId = this.masterShipTile.getTileId();
			movesList = legalMovesMap.get(battleStarTileId);
			System.out.println("Battle star legal moves: " + movesList);
			// get random legal tile for next move
			int nextTile = movesList.get(randomNumbersGenerator.nextInt(movesList.size()));
			System.out.println("Move battle star to tile: " + nextTile);
			// get tile ship is in	
				for (GameTile t : listOfTiles) {
					if (t.getTileId() == nextTile) {
						this.battleStarTile = t;
					}
				}
		} // end battle star
		
		if (this.battleCruiserTile != null) {
			int battleStarTileId = this.battleCruiserTile.getTileId();
			movesList = legalMovesMap.get(battleStarTileId);
			System.out.println("Battle cruiser legal moves: " + movesList);
			// get random legal tile for next move
			int nextTile = movesList.get(randomNumbersGenerator.nextInt(movesList.size()));
			System.out.println("Move battle cruiser to tile: " + nextTile);
			// get tile ship is in	
				for (GameTile t : listOfTiles) {
					if (t.getTileId() == nextTile) {
						this.battleCruiserTile = t;
					}
				}
		} // end battle cruiser
		
		if (this.battleShooterTile != null) {
			int battleStarTileId = this.battleShooterTile.getTileId();
			movesList = legalMovesMap.get(battleStarTileId);
			System.out.println("Battle shooter legal moves: " + movesList);
			// get random legal tile for next move
			int nextTile = movesList.get(randomNumbersGenerator.nextInt(movesList.size()));
			System.out.println("Move battle shooter to tile: " + nextTile);
			// get tile ship is in	
				for (GameTile t : listOfTiles) {
					if (t.getTileId() == nextTile) {
						this.battleShooterTile = t;
					}
				}
		} // end battle shooter		
	}

	public int getEnemyType() {
		return this.enemyType;
	}

	public void setEnemyType(int enemyType) {
		this.enemyType = enemyType;
	}

	public GameTile getMasterShipTile() {
		return this.masterShipTile;
	}

	public void setMasterShipTile(GameTile masterShipTile) {
		this.masterShipTile = masterShipTile;
	}

	public GameTile getBattleStarTile() {
		return this.battleStarTile;
	}

	public void setBattleStarTile(GameTile battleStarTile) {
		this.battleStarTile = battleStarTile;
	}

	public GameTile getBattleCruiserTile() {
		return this.battleCruiserTile;
	}

	public void setBattleCruiserTile(GameTile battleCruiserTile) {
		this.battleCruiserTile = battleCruiserTile;
	}

	public GameTile getBattleShooterTile() {
		return this.battleShooterTile;
	}

	public void setBattleShooterTile(GameTile battleShooterTile) {
		this.battleShooterTile = battleShooterTile;
	}
	
	

}
