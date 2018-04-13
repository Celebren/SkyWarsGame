package skyWars;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class RowsGrid implements Serializable {
	
	// this is the sky
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// declare variables and collecitons
	private ArrayList<TilesRow> gridOfRows = new ArrayList<TilesRow>();
	private HashMap<Integer, ArrayList<Integer>> mapOfMoves = new HashMap<Integer, ArrayList<Integer>>();
	private HashMap<Integer, ArrayList<Integer>> hashMapEntry = new HashMap<Integer, ArrayList<Integer>>();
	private	Integer key;
	private ArrayList<Integer> value;
	private ArrayList<GameTile> listOfTiles = new ArrayList<GameTile>();
	private GameTile gameTile = new GameTile();

	private final int ROWS_IN_GRID = 4;
	private final int TILES_IN_A_ROW = 4;
	
	// loop 4 times adding rows whe constructor is called
	public RowsGrid() {		
		for (int i = 0; i < ROWS_IN_GRID; i++) {
			TilesRow row = new TilesRow(i+1);
			this.gridOfRows.add(row);
		}			
	} // end constructor

	// test display
	public void displayRows() {
		for (TilesRow row: this.gridOfRows) {
			int rowId = row.getRowId();
			System.out.println("Row: " + rowId);
			row.displayTiles();
		}
	} // end displayRows()
		
	// get the map of legal moves
	public HashMap<Integer, ArrayList<Integer>> getLegalMovesMap() {
		for (int i = 0; i < ROWS_IN_GRID; i++) {			
			for (int y = 0; y < TILES_IN_A_ROW; y++) {
				
				// take each map entry for each tile for each row
				this.hashMapEntry = this.getGridOfRows().get(i).getRowOfTiles().get(y).getMapOfMoves();
				
				// extract key and value
				for (HashMap.Entry<Integer, ArrayList<Integer>> entry : hashMapEntry.entrySet()) {
					this.key = entry.getKey();
					this.value = entry.getValue();
				}
				// add key and value to new map
				this.mapOfMoves.put(key, value); 				
			}
		}		
		return this.mapOfMoves;
	}// end of get map of legal moves
	
	// get array of all tiles
	public ArrayList<GameTile> getArrayListOfTiles() {
		for (int i = 0; i < ROWS_IN_GRID; i++) {			
			for (int y = 0; y < TILES_IN_A_ROW; y++) {
				this.gameTile = this.getGridOfRows().get(i).getRowOfTiles().get(y);
				this.listOfTiles.add(gameTile);
			}
		}
		return this.listOfTiles;
	}
	
	public ArrayList<TilesRow> getGridOfRows() {
		return this.gridOfRows;
	}

	public void setGridOfRows(ArrayList<TilesRow> gridOfRows) {
		this.gridOfRows = gridOfRows;
	}
}
