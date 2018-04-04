package skyWars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.sun.media.sound.SimpleSoundbank;

public class demo {
	
	// test rows and tiles creation
	public static void main(String[] args) {
		// declare variables and collections
		HashMap<Integer, ArrayList<Integer>> mapOfMoves;
		ArrayList<GameTile> listOfTiles;
		Random rng = new Random();
		
		// make new sky grid
		RowsGrid sky = new RowsGrid();
		
		// get legal moves list
		mapOfMoves = sky.getLegalMovesMap();
		
		// test
		System.out.println("Legal moves for each tile:");
		for (HashMap.Entry<Integer, ArrayList<Integer>> entry : mapOfMoves.entrySet()) {
			System.out.println(entry);
			
			// get random legal tile for next move
			int nextTile = rng.nextInt(entry.getValue().size());
			System.out.println("random legal tile " + entry.getValue().get(nextTile));
		}
		
		/*
		listOfTiles = sky.getArrayListOfTiles();
		
		// test array of tiles
		System.out.println("\nList of tiles");
		for (GameTile tile : listOfTiles) {
			System.out.println(tile.getTileId());
		}
		*/
		// test RNG
		//System.out.println("\nTesting rng: ");
		//ButtonsLogic rng1 = new ButtonsLogic();
		
	} // end of main
} 
