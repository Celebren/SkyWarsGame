package skyWars;

import java.util.ArrayList;

public class TilesRow {

	private int rowId;
	
	private final int TILESINAROW = 4;
	
	private ArrayList<GameTile> rowOfTiles = new ArrayList<GameTile>();
	
	public TilesRow (int rowId) {
		for (int i=rowId; i < TILESINAROW; i++) {
			GameTile tile = new GameTile(i+1);
			this.rowOfTiles.add(tile);
		}		
	} // end constructor
	
	public void displayTiles() {
		for (GameTile t: this.rowOfTiles) {
			t.displayId();			
		}
	} // end displayTiles

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	
	
}
