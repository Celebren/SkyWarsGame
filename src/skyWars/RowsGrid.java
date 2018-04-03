package skyWars;

import java.util.ArrayList;

public class RowsGrid {
	
	// this is the sky basically
	private ArrayList<TilesRow> gridOfRows = new ArrayList<TilesRow>();
	
	// number of rows
	private final int ROWS_IN_GRID = 4;
	
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
}
