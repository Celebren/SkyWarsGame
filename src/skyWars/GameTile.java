package skyWars;

public class GameTile {

	// each tile has an id from 1 to 16
	private int tileId;
	
	public GameTile(int tileId) {
		setTileId(tileId);
	}
	
	public void displayId() {
		System.out.println("Tile no " + getTileId());
	}

	public int getTileId() {
		return this.tileId;
	}

	public void setTileId(int tileId) {
		this.tileId = tileId;
	}
}
