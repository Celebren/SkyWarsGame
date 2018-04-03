package skyWars;

import java.util.ArrayList;
import java.util.HashMap;

public class demo {
	
	// test rows and tiles creation
	public static void main(String[] args) {
		// declare variables and collections
		HashMap<Integer, ArrayList<Integer>> mapOfMoves;
		
		// make new sky grid
		RowsGrid sky = new RowsGrid();
		
		// get legal moves list
		mapOfMoves = sky.getLegalMovesMap();
		
		// test
		System.out.println("Legal moves for each tile:");
		for (HashMap.Entry<Integer, ArrayList<Integer>> entry : mapOfMoves.entrySet()) {
			System.out.println(entry);
		}
		
	} // end of main
} 
