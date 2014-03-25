package net.RPG.Entities;

import java.awt.Color;
import java.awt.Graphics;

import net.RPG.Core.Core;
import net.RPG.Level.Tile;

public class Player extends Character {
	
	public static int Rx, Ry;
	
	
	private boolean goUp = false, goDown = false, goLeft = false, goRight = false;
	
	private Inventory inventory;
	
	public Player(Core rPG, double x, double y) {
		super(rPG ,new int[] {0,0},x,y);
		inventory = new Inventory(20);
		
		Rx = (int)(x - RPG.aX); //zeby render byl zawsze na srodku
		Ry = (int)(y - RPG.aY);
		
		aniTime = 10;
		stats.setMoveSpeed(2);
		stats.setHealth(110);
	}
	
	@Override
	public void collidedWith(Entity entity) {
		if(entity instanceof Item && RPG.bSPACE){
			((Item) entity).pickUp();
			inventory.addItem((Item) entity);
			RPG.bSPACE = false;
			//RPG.removeEntity(entity);
		}
		if(entity instanceof Enemy){
			((Enemy) entity).attack();
		}
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void tick(){
		if(aniDelta >= aniTime){
			aniFrame++;
			aniDelta = 0;
			if(aniFrame > 2){
				aniFrame = 0;
			}
		}
		
		if(RPG.bW && canMove(tX, tY - 1) && !isMoving) {
			goUp = true;
		}  if(RPG.bS && canMove(tX, tY + 1) && !isMoving) {
			goDown = true;
		}  if(RPG.bA && canMove(tX - 1, tY) && !isMoving) {
			goLeft = true;
		}  if(RPG.bD && canMove(tX + 1, tY) && !isMoving) {
			goRight = true;
		}
		//up
		if(goUp) {
			moveDir = 3;
			aniDelta++;
			
			if(!isMoving){
				isMoving = true;
			}
			if(isMoving) {
				RPG.aY -= stats.getMoveSpeed();
				y -= stats.getMoveSpeed();
				moveDelta += stats.getMoveSpeed();

				if(moveDelta >= Tile.size){
					moveDelta = 0;
					tY -= 1;
					if(!RPG.bW || !canMove(tX, tY - 1)) {
						goUp = false;
						isMoving = false;
						aniFrame = 1;
					}
				}
			}
		} 
		//down
		if(goDown) {
			aniDelta++;
			moveDir = 0;
			if(!isMoving) {
				isMoving = true;
			}
			if(isMoving) {
				RPG.aY += stats.getMoveSpeed();
				y+= stats.getMoveSpeed();
				moveDelta += stats.getMoveSpeed();
				
				if(moveDelta >= Tile.size){
					moveDelta = 0;
					tY += 1;
					if(!RPG.bS || !canMove(tX, tY + 1)) {
						goDown = false;
						isMoving = false;
						aniFrame = 1;
					}
				}
			}	
		} 
		//left
		if(goLeft) {
			aniDelta++;
			moveDir = 1;
			if(!isMoving) {
				isMoving = true;
			}
			if(isMoving) {
				RPG.aX -= stats.getMoveSpeed();
				moveDelta += stats.getMoveSpeed();
				x -= stats.getMoveSpeed();
				
				if(moveDelta >= Tile.size){
					moveDelta = 0;
					tX -= 1;
					if(!RPG.bA || !canMove(tX - 1, tY)) {
						goLeft = false;
						isMoving = false;
						aniFrame = 1;
					}
				}
			}	
		} 
		//right
		if(goRight) {
			moveDir = 2;
			if(!isMoving) {
				isMoving = true;
			}
			if(isMoving) {
				aniDelta++;
				RPG.aX += stats.getMoveSpeed();
				moveDelta += stats.getMoveSpeed();
				x += stats.getMoveSpeed();
				
				if(moveDelta >= Tile.size){
					moveDelta = 0;
					tX += 1;
					if(!RPG.bD || !canMove(tX + 1, tY)) {
						goRight = false;
						isMoving = false;
						aniFrame = 1;
					}
				}			
			}	
		}
		if(RPG.bI) {
			RPG.bI = false;
			RPG.inGame = false;
			RPG.inInventory = true;
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		super.setImage(new int[] {id[0] + aniFrame,id[1] + moveDir});
		g.drawImage(image, Rx, Ry, null);
		if(isAware) {
			g.setColor(Color.white);
			g.drawString("!", Rx+Tile.size/2,Ry);
			isAware = false;
		}
	}

}
