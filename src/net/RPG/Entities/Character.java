package net.RPG.Entities;

import net.RPG.Core.Core;
import net.RPG.Level.Tile;

public abstract class Character extends Entity {

	/**entity's stats*/
	protected EntityStats stats;
	/**move/anim stuff*/
	protected int moveDir = 0;
	protected int moveDelta = 0;
	protected int aniFrame = 1;
	protected int aniTime = 0;
	protected int aniDelta = 0;
	
	protected boolean isMoving = false;
		
	public Character(Core rPG, int[] i, double x, double y) {
		super(rPG, i, x, y);
		stats = new EntityStats();
	}
	
	public void setImage(int[] i) {
		image = Tile.characters.getSubimage(i[0] * Tile.size, i[1]*Tile.size, Tile.size, Tile.size);
	}
	
	public boolean canMove(int i, int j) {
		if(RPG.level.solid[i][j].id == Tile.blank) {
			return true;
		}
		return false;
	}
	
	public boolean ifMoves() {
		return isMoving;
	}
}
