package net.RPG.Entities;

public class EntityStats {
	
	private int xp;
	private int attack;
	private int defence;
	
	private int attackSpeed;
	/**move speed*/
	private double moveSpeed;
	/**entity's health*/
	protected int health;
	/**entity's max health*/
	private int maxHealth;
	
	public EntityStats() {
		
	}
	
	public void setMoveSpeed(double s){
		moveSpeed = s;
	}
	
	public void setHealth(int h) {
		health = h;
	}
	
	public void setMaxHealth(int h) {
		maxHealth = h;
	}
	
	public double getMoveSpeed() {
		return moveSpeed;
	}
}
