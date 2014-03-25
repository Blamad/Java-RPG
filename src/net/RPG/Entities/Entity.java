package net.RPG.Entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import net.RPG.Core.Core;
import net.RPG.Level.Tile;

public abstract class Entity {

	/**entity coordinates*/
	protected double x, y;
	/**entity's tile position*/
	protected int tX, tY;
	/**entity's tile position in tileset*/
	protected int[] id;
	/** */
	protected boolean isVisible = false;
	
	/**collision stuff*/
	protected Rectangle me = new Rectangle();
	protected Rectangle him = new Rectangle();	
	protected boolean isAware = false;
	
	protected Core RPG;

	/**image for rendering entity*/
	protected Image image;
	
	public Entity(Core rPG, int[] i, double x, double y) {
		this.x = x;
		this.y = y;
		tX = (int) (x / Tile.size);
		tY = (int) (y / Tile.size);
		RPG = rPG;
		id = i;
	}
	
	public abstract void tick();
	
	public abstract void setImage(int[] i);
	
	public void render(Graphics g) {
		g.drawImage(image, (int) (x - RPG.aX), (int) (y - RPG.aY), null);
	}
	
	public boolean visibility() {
		return isVisible;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public int gettX() {
		return tX;
	}
	
	public int gettY() {
		return tY;
	}
	
	public Image getImage() {
		return image;
	}
	
	public boolean collidesWith(Entity entity) {
		
		me.setBounds((int)x-2,(int)y-2,Tile.size+2,Tile.size+2);
		him.setBounds((int)entity.x - 2, (int)entity.y - 2,Tile.size + 2, Tile.size + 2);
		if(me.intersects(him)) {
			isAware = true;
		}
		return me.intersects(him);
	}
	
	public void collidedWith(Entity entity) {
		isAware = true;
	}
}
