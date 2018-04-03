package skyWars;

import java.util.ArrayList;
import java.util.HashMap;

public class demo {
	
	// test rows and tiles creation
	public static void main(String[] args) {
		
		HashMap<Integer, ArrayList<Integer>> mapOfMoves;
		
		RowsGrid sky = new RowsGrid();
	
		mapOfMoves = sky.getLegalMovesMap();
		
		for (HashMap.Entry<Integer, ArrayList<Integer>> entry : mapOfMoves.entrySet()) {
			System.out.println(entry);
		}
		
	}
}
