package skyWars;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class GameLogic implements Serializable, Observable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rngMin; 
	private int rngMax;
	private int masterShipSpawnTile;
	private int enemyShipSpawnTile;
	private int chanceOfEnemySpawn;
	private int enemyType;
	private int masterMode; // 0 = defensive mode, 1 = offensive mode
	private int destroyedShipId = 0;

	private int score = 0;
	private int highScore;

	private final int SCORE_STAR = 25;
	private final int SCORE_SHOOTER = 50;
	private final int SCORE_CRUISER = 75;

	private Random randomNumbersGenerator = new Random();
	private RowsGrid sky = new RowsGrid();
	private GameTile masterShipTile;
	private GameTile battleStarTile;
	private GameTile battleCruiserTile;
	private GameTile battleShooterTile;

	private Ship destroyedShip;

	private MasterSpaceShip masterSpaceShip = new MasterSpaceShip();
	private BattleStar battleStarShip = new BattleStar();
	private BattleCruiser battleCruiserShip = new BattleCruiser();
	private BattleShooter battleShooterShip = new BattleShooter();

	private ArrayList<GameTile> listOfTiles = sky.getArrayListOfTiles();

	private HashMap<Integer, ArrayList<Integer>> legalMovesMap = sky.getLegalMovesMap();
	private ArrayList<Integer> movesListForMaster = new ArrayList<Integer>();
	private ArrayList<Integer> movesListForStar = new ArrayList<Integer>();
	private ArrayList<Integer> movesListForCruiser = new ArrayList<Integer>();
	private ArrayList<Integer> movesListForShooter = new ArrayList<Integer>();

	private ArrayList<Scores> scoresObservers = new ArrayList<Scores>();
	
	public GameLogic () {}


	// call when reset button is pressed
	public int masterShipSpawn() {		
		rngMin = 2; // master ship shouldn't be able to spawn on tile01 where enemy ships come from
		rngMax = 16;
		// nextInt(max - min + 1) + min to calculate random number in range min:max
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
		chanceOfEnemySpawn = 0;
		enemyShipSpawnTile = 0;

		chanceOfEnemySpawn = randomNumbersGenerator.nextInt(rngMax - rngMin + 1) + rngMin;

		if (chanceOfEnemySpawn == 1) {

			this.setEnemyType(randomNumbersGenerator.nextInt(rngMax - rngMin + 1) + rngMin);

			switch(this.enemyType) {
			case 1: 
				if (battleStarTile == null) {
					this.battleStarTile = listOfTiles.get(0);
					enemyShipSpawnTile = 1;
				}
				break;
			case 2:
				if (battleCruiserTile == null) {
					this.battleCruiserTile = listOfTiles.get(0);
					enemyShipSpawnTile = 1;
				}
				break;
			case 3: 
				if (battleShooterTile == null) {
					this.battleShooterTile = listOfTiles.get(0);
					enemyShipSpawnTile = 1;
				}
				break;
			default: enemyShipSpawnTile = 0; break;
			}
		} else {
			enemyShipSpawnTile = 0;
		}

		return enemyShipSpawnTile;
	}

	// call when move button is pressed
	public void moveMasterShip() {
		// remove master space ship from previous tile list of ships
		this.masterShipTile.removeShipFromList(masterSpaceShip);

		int masterShipTileId = this.masterShipTile.getTileId();

		movesListForMaster = legalMovesMap.get(masterShipTileId);

		// get random legal tile for next move
		int nextTile = movesListForMaster.get(randomNumbersGenerator.nextInt(movesListForMaster.size()));

		// update which tile the maste ship is in
		for (GameTile t : listOfTiles) {
			if (t.getTileId() == nextTile) {
				this.masterShipTile = t;
			}
		}
		// add space ship to array list of ships of new tile
		this.masterShipTile.addShipToList(masterSpaceShip);		
	}

	// call when move buton is pressed
	public void moveEnemyShips() {

		if (this.battleStarTile != null) {

			// remove from list of ships on tile
			this.battleStarTile.removeShipFromList(battleStarShip);

			int battleStarTileId = this.battleStarTile.getTileId();
			movesListForStar = legalMovesMap.get(battleStarTileId);

			// get random legal tile for next move
			int nextTile = movesListForStar.get(randomNumbersGenerator.nextInt(movesListForStar.size()));

			// update which tile the enemy ship is in
			for (GameTile t : listOfTiles) {
				if (t.getTileId() == nextTile) {
					this.battleStarTile = t;
				}
			}

			// add ship to new tile's list of ships
			this.battleStarTile.addShipToList(battleStarShip);

		} // end battle star

		if (this.battleCruiserTile != null) {

			this.battleCruiserTile.removeShipFromList(battleCruiserShip);

			int battleCruiserTileId = this.battleCruiserTile.getTileId();
			movesListForCruiser = legalMovesMap.get(battleCruiserTileId);

			// get random legal tile for next move
			int nextTile = movesListForCruiser.get(randomNumbersGenerator.nextInt(movesListForCruiser.size()));

			// update which tile the enemy ship is in	
			for (GameTile t : listOfTiles) {
				if (t.getTileId() == nextTile) {
					this.battleCruiserTile = t;
				}
			}

			this.battleCruiserTile.addShipToList(battleCruiserShip);

		} // end battle cruiser

		if (this.battleShooterTile != null) {

			this.battleShooterTile.removeShipFromList(battleShooterShip);

			int battleShooterTileId = this.battleShooterTile.getTileId();
			movesListForShooter = legalMovesMap.get(battleShooterTileId);

			// get random legal tile for next move
			int nextTile = movesListForShooter.get(randomNumbersGenerator.nextInt(movesListForShooter.size()));

			// update which tile the enemy ship is in	
			for (GameTile t : listOfTiles) {
				if (t.getTileId() == nextTile) {
					this.battleShooterTile = t;
				}
			}

			this.battleShooterTile.addShipToList(battleShooterShip);

		} // end battle shooter		
	} // end moveEnemySHips()


	// conflict resolution method
	public int conflictResolution() {
		destroyedShipId = 0;
		destroyedShip = null;

		if (this.masterShipTile.getListOfShipsOnTile().size() > 1) {
			System.out.println("Conflict happens!");

			// conflict resolution depends on master ship mode
			if (masterMode == 0) {
				if (this.masterShipTile.getListOfShipsOnTile().size() == 2) {

					destroyedShipId = masterShipWinsResolution();

				} else if (this.masterShipTile.getListOfShipsOnTile().size() > 2) {
					// master ship loses					
					destroyedShipId = masterShipDestroyedResolution();

				}

			} else if (masterMode == 1) { // offensive mode logic
				if (this.masterShipTile.getListOfShipsOnTile().size() == 2 || this.masterShipTile.getListOfShipsOnTile().size() == 3) {

					destroyedShipId = masterShipWinsResolution();

				} else if (this.masterShipTile.getListOfShipsOnTile().size() > 3) {
					// master ship loses
					destroyedShipId = masterShipDestroyedResolution();
				}
			}
		}		
		return destroyedShipId;
	} // end conflicResolution()

	public int masterShipWinsResolution() {
		for (Ship s : this.masterShipTile.getListOfShipsOnTile()) {
			if (s != masterSpaceShip) {
				destroyedShip = s;
				this.masterShipTile.removeShipFromList(s);
				break;
			}
		}
		System.out.println("Master ship wins against " + destroyedShip.getShipName());

		switch (destroyedShip.getShipTypeId()) {
		case 2:
			battleStarTile = null;
			this.score += SCORE_STAR;
			break;
		case 3:
			battleCruiserTile = null;
			this.score += SCORE_CRUISER;
			break;
		case 4:
			battleShooterTile = null;
			this.score += SCORE_SHOOTER;
			break;
		}

		destroyedShipId = destroyedShip.getShipTypeId();
		increaseHighScore();
		return destroyedShipId;
	} // masterShipWinsResolution()

	// increase highscore
	public void increaseHighScore() {
		if (this.score > this.highScore) {
			setHighScore(this.score);
		}
	}

	public int masterShipDestroyedResolution() {
		System.out.println("Master ship is destroyed");		
		destroyedShipId = 1;

		return destroyedShipId;
	}
	
	// getters & setters
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

	public int getMasterMode() {
		return this.masterMode;
	}

	public void setMasterMode(int masterMode) {
		this.masterMode = masterMode;
	}

	public ArrayList<GameTile> getListOfTiles() {
		return this.listOfTiles;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getHighScore() {
		return this.highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	@Override
	public void registerObserver(Scores scores) {
		scoresObservers.add(scores);		
	}


	@Override
	public void removeObserver(Scores scores) {
		scoresObservers.remove(scores);		
	}


	@Override
	public void notifyObservers() {
		for (Scores s : scoresObservers) {
			s.update(getScore());
		}
		
	}
}
