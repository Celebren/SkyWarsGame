package skyWars;

public abstract class Ship {
	// Enemy Ships superclass for polymorphism
	
	/* Ships' id determines it's type: 
	 * 0 - masterSpaceShip (Cobra)
	 * 1 - BattleStar	(Fighter)
	 * 2 - BattleCruiser (Corvette)
	 * 3 - BattleShooter (Gunship)
	 */
	private int shipTypeId;

	public int getShipTypeId() {
		return this.shipTypeId;
	}

	public void setShipTypeId(int shipTypeId) {
		this.shipTypeId = shipTypeId;
	}
}
