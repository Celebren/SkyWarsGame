package skyWars;

import java.util.ArrayList;

public class TilesRow {
	
	// each row has an id from 1 to 4
	private int rowId;
	private int x; // generic x variable for maths calculations when adding tiles
	
	private final int TILES_IN_A_ROW = 4;
	
	private ArrayList<GameTile> rowOfTiles = new ArrayList<GameTile>();
	
	// when constructing a tiles row add new tiles to it looping 
	// the number of tiles in a row
	public TilesRow (int rowId) {
		
		setRowId(rowId);
		
		// if row id = 1, create tiles 1 to 4 (i + rowId)
		// when row id > 1, create tiles using i + ((rowId * 4) - 3)
		if (rowId == 1) {
			x = rowId;
		} else {
			x = (rowId*4)-3;
		}
		
		for (int i=0; i < TILES_IN_A_ROW; i++) {			
			GameTile tile = new GameTile(i+(x));
			this.rowOfTiles.add(tile);		
		}
		
	} // end constructor
	
	// test tiles have been added correctlly 
	public void displayTiles() {
		for (GameTile tile: this.rowOfTiles) {
			tile.displayId();			
		}
	} // end displayTiles
	 
	
	// getters & setters
	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public ArrayList<GameTile> getRowOfTiles() {
		return this.rowOfTiles;
	}

	public void setRowOfTiles(ArrayList<GameTile> rowOfTiles) {
		this.rowOfTiles = rowOfTiles;
	}
	
	
}
