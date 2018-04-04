package skyWars;

public abstract class Ship {
	// Enemy Ships superclass for polymorphism
	
	/* Ships' id determines it's type: 
	 * 1 - masterSpaceShip
	 * 2 - BattleStar
	 * 3 - BattleCruiser
	 * 4 - BattleShooter
	 */
	private int shipTypeId;

	public int getShipTypeId() {
		return this.shipTypeId;
	}

	public void setShipTypeId(int shipTypeId) {
		this.shipTypeId = shipTypeId;
	}
}
