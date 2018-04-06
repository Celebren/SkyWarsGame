package skyWars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GameTile {

	// each tile has an id from 1 to 16
	int tileId;
	
	// create hashMap where key = tileId and value = legal moves for that tile
	private HashMap<Integer, ArrayList<Integer>> mapOfMoves = new HashMap<Integer, ArrayList<Integer>>();
	
	private ArrayList<Integer> legalMoves;
	
	// this list will contain ships that exist on the tile
	private ArrayList<Ship> listOfShipsOnTile = new ArrayList<Ship>();

	private Ship ship;
	
	public GameTile() {} // empty constructor for testing
	
	public GameTile(int tileId) {
		setTileId(tileId);
		legalMoves = new ArrayList<Integer>();
		createMapOfLegalMoves(tileId);
	}
	
	public void displayId() {
		System.out.println("Tile no " + getTileId());
	}

	// create map of legal moves
	private void createMapOfLegalMoves(int tileId) {
		switch(tileId) {
			case 1:  this.legalMoves.addAll(Arrays.asList(2,5,6)); this.mapOfMoves.put(tileId, legalMoves); break;
			case 2:  this.legalMoves.addAll(Arrays.asList(1,3,5,6,7)); this.mapOfMoves.put(tileId, legalMoves); break;
			case 3:  this.legalMoves.addAll(Arrays.asList(2,4,6,7,8)); this.mapOfMoves.put(tileId, legalMoves); break;
			case 4:  this.legalMoves.addAll(Arrays.asList(3,7,8)); this.mapOfMoves.put(tileId, legalMoves); break;
			case 5:  this.legalMoves.addAll(Arrays.asList(1,2,6,9,10)); this.mapOfMoves.put(tileId, legalMoves); break;
			case 6:  this.legalMoves.addAll(Arrays.asList(1,2,3,5,7,9,10,11)); this.mapOfMoves.put(tileId, legalMoves); break;
			case 7:  this.legalMoves.addAll(Arrays.asList(2,3,4,6,8,10,11,12)); this.mapOfMoves.put(tileId, legalMoves); break;
			case 8:  this.legalMoves.addAll(Arrays.asList(3,4,7,11,12)); this.mapOfMoves.put(tileId, legalMoves); break;
			case 9:  this.legalMoves.addAll(Arrays.asList(5,6,10,13,14)); this.mapOfMoves.put(tileId, legalMoves); break;
			case 10: this.legalMoves.addAll(Arrays.asList(5,6,7,9,11,13,14,15)); this.mapOfMoves.put(tileId, legalMoves); break;
			case 11: this.legalMoves.addAll(Arrays.asList(6,7,8,10,12,14,15,16)); this.mapOfMoves.put(tileId, legalMoves); break;
			case 12: this.legalMoves.addAll(Arrays.asList(7,8,11,15,16)); this.mapOfMoves.put(tileId, legalMoves); break;
			case 13: this.legalMoves.addAll(Arrays.asList(9,10,14)); this.mapOfMoves.put(tileId, legalMoves); break;
			case 14: this.legalMoves.addAll(Arrays.asList(9,10,11,13,15)); this.mapOfMoves.put(tileId, legalMoves); break;
			case 15: this.legalMoves.addAll(Arrays.asList(10,11,12,14,16)); this.mapOfMoves.put(tileId, legalMoves); break;
			case 16: this.legalMoves.addAll(Arrays.asList(11,12,15)); this.mapOfMoves.put(tileId, legalMoves); break;
		}
	}
	
	// getters & setters
	public int getTileId() {
		return this.tileId;
	}

	public void setTileId(int tileId) {
		this.tileId = tileId;
	}

	public HashMap<Integer, ArrayList<Integer>> getMapOfMoves() {
		return this.mapOfMoves;
	}

	public void setMapOfMoves(HashMap<Integer, ArrayList<Integer>> mapOfMoves) {
		this.mapOfMoves = mapOfMoves;
	}

	public Ship getShip() {
		return this.ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public ArrayList<Ship> getListOfShipsOnTile() {
		return this.listOfShipsOnTile;
	}

	public void setListOfShipsOnTile(ArrayList<Ship> listOfShipsOnTile) {
		this.listOfShipsOnTile = listOfShipsOnTile;
	}
}
