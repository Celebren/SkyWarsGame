package skyWars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ButtonsLogic {
	
	private int rngMin = 1;
	private int rngMax = 16;
	private int masterShipSpawnTile;
	private int nextTile;
	private Random randomNumbersGenerator = new Random();
	private RowsGrid sky = new RowsGrid();
	private GameTile tile;
	private ArrayList<GameTile> listOfTiles = sky.getArrayListOfTiles();
	private MasterSpaceShip masterSpaceShip = new MasterSpaceShip();
	private HashMap<Integer, ArrayList<Integer>> legalMovesMap = sky.getLegalMovesMap();
	private ArrayList<Integer> movesList = new ArrayList<Integer>();
		
	
	public ButtonsLogic () {		
		
		// nextInt(max - min + 1) + min to calculate random number in range min:max
		//System.out.println(randomNumbersGenerator.nextInt(rngMax - rngMin + 1) + rngMin); 
	}
	
	// call when reset button is pressed
	public int masterShipSpawn() {
		masterShipSpawnTile = randomNumbersGenerator.nextInt(rngMax - rngMin + 1) + rngMin;
		
		// get tile ship is in	
		for (GameTile t : listOfTiles) {
			if (t.getTileId() == masterShipSpawnTile) {
				t.setShip(masterSpaceShip);
				this.tile = t;
			}
		}
		System.out.println("Start at tile " + masterShipSpawnTile);
		return masterShipSpawnTile;
	}
	
	// call when move button is pressed
	public int moveMasterShip() {
		int tileId = this.tile.getTileId();		
		movesList = legalMovesMap.get(tileId);
		System.out.println("Legal Moves: " + movesList);
		// get random legal tile for next move
		int nextTile = movesList.get(randomNumbersGenerator.nextInt(movesList.size()));
		System.out.println("Move to tile: " + nextTile);
		// get tile ship is in	
			for (GameTile t : listOfTiles) {
				if (t.getTileId() == nextTile) {
					t.setShip(masterSpaceShip);
					this.tile = t;
				}
			}
		
		return nextTile;
	}
	
	

}
