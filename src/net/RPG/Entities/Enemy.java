package net.RPG.Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import net.RPG.Core.Core;
import net.RPG.Level.Tile;

public class Enemy extends Character {

	/**how often you wanna move? */
	protected int sloth = 0;
	/**how far is my sight*/
	protected int sight = 0;
	
	protected boolean isFighting = false;
	protected boolean isFollowing = false;
	
	private int dir[] = { 0, 0 };
	
	/**following stuff */
	private int[] playerPos = {0,0};
	
	Random generator = new Random();

	public Enemy(Core rPG, int i[], double x, double y) {
		super(rPG, i, x, y);
	}

	@Override
	public void tick() {
		
		// jezeli obiekt jest w polu widzenia, niech sie rusza/renderuje
		if (x >= RPG.aX - Tile.size && y >= RPG.aY - Tile.size
				&& x < RPG.aX + RPG.pixel.width + Tile.size
				&& y < RPG.aY + RPG.pixel.height + Tile.size) {
			isVisible = true;
		} else
			isVisible = false;

		if (isVisible) {

			if (aniDelta >= aniTime) {
				aniFrame++;
				aniDelta = 0;
				if (aniFrame > 2) {
					aniFrame = 0;
				}
			}

			if (!isMoving) {			
				
				if(!isFollowing) {
					if (generator.nextInt(100) < sloth) { // ruszy
						moveDir = generator.nextInt(4); // random kierunku
				
						// czy nie skoliduje
						if (moveDir == 3 && canMove(tX, tY - 1)) {
							isMoving = true;
							dir = new int[] { 0, -1 };
						} else if (moveDir == 0 && canMove(tX, tY + 1)) {
							isMoving = true;
							dir = new int[] { 0, 1 };
						} else if (moveDir == 1 && canMove(tX - 1, tY)) {
							isMoving = true;
							dir = new int[] { -1, 0 };
						} else if (moveDir == 2 && canMove(tX + 1, tY)) {
							isMoving = true;
							dir = new int[] { 1, 0 };
						}
					}
				} else {
					playerPos[0] = RPG.player.gettX();
					playerPos[1] = RPG.player.gettY();
					
					if(playerPos[0] == tX) {
						dir = new int[] {0,(int) Math.signum(playerPos[1]-tY)};
						if(dir[1] == 1)
							moveDir = 0;
						else
							moveDir = 3;
					} else if(playerPos[1] == tY) {
						dir = new int[] {(int) Math.signum(playerPos[0]-tX),0};
						if(dir[0] == 1)
							moveDir = 2;
						else
							moveDir = 1;
					} else {
						if(generator.nextInt(2) == 1){
							dir = new int[] {0,(int) Math.signum(playerPos[1]-tY)};
							if(dir[1] == 1)
								moveDir = 0;
							else
								moveDir = 3;
						} else {
							dir = new int[] {(int) Math.signum(playerPos[0]-tX),0};
							if(dir[0] == 1)
								moveDir = 2;
							else
								moveDir = 1;
						}
					}
					isMoving = true;
					isFollowing = false;
				}
			}
			
			if (isMoving) {
				x += dir[0] * stats.getMoveSpeed();
				y += dir[1] * stats.getMoveSpeed();
				moveDelta += stats.getMoveSpeed();
				aniDelta += 1;

				if (moveDelta >= Tile.size) {
					moveDelta = 0;
					tX += dir[0];
					tY += dir[1];
					isMoving = false;
					aniFrame = 1;
				}
			}
		}
	}

	public void attack() {
		isFighting = true;
	}
	
	public void lookFor(Player entity) {
		me.setBounds((int)x -sight*Tile.size,(int)y -sight*Tile.size,Tile.size +sight*Tile.size,Tile.size +sight*Tile.size);
		him.setBounds((int)entity.x, (int)entity.y,Tile.size, Tile.size);
		if(me.intersects(him))
			isFollowing = true;
	}
	
	public void render(Graphics g) {
		if (isVisible) { // jezeli widoczny, renderuj
			super.setImage(new int[] { id[0] + aniFrame, id[1] + moveDir });
			g.drawImage(image, (int) (x - RPG.aX), (int) (y - RPG.aY), null);
			
			if(isAware && isFighting) {
				g.setColor(Color.white);
				g.drawString("Attack!", (int) (x - RPG.aX + Tile.size/2 - 16), (int)(y - RPG.aY - 4));
				isAware = isFighting = false;
			}
			
			if(isAware) {
				g.setColor(Color.white);
				g.drawString("!", (int)(x - RPG.aX +Tile.size/2),(int)(y - RPG.aY));
				isAware = false;
			}
			if(isFollowing) {
				g.setColor(Color.white);
				g.drawString("?", (int)(x - RPG.aX +Tile.size/2),(int)(y - RPG.aY));
				isFollowing = false;
			}
		}
	}
}
