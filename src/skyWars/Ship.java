package skyWars;

public abstract class Ship {
	// Enemy Ships superclass for polymorphism
	
	/* Ships' id determines it's type: 
	 * 0 - null
	 * 1 - masterSpaceShip (Cobra)
	 * 2 - BattleStar	(Fighter)
	 * 3 - BattleCruiser (Corvette)
	 * 4 - BattleShooter (Gunship)
	 */
	private int shipTypeId;
	private String shipName;

	public int getShipTypeId() {
		return this.shipTypeId;
	}

	public void setShipTypeId(int shipTypeId) {
		this.shipTypeId = shipTypeId;
	}

	public String getShipName() {
		return this.shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
}
