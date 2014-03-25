package net.RPG.Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import net.RPG.Core.Core;
import net.RPG.Level.Tile;


public class Item extends Entity {
	
	public String name;
	
	public Item(Core rPG, int i[], int x, int y) {
		super(rPG, i, x, y);
		isVisible = true;
	}
	
	public void setImage(int[] i) {
		image = Tile.items.getSubimage(i[0] * Tile.size, i[1]*Tile.size, Tile.size, Tile.size);
	}
	
	public void pickUp() {
		x = y = 0;
		System.out.println("You just picked up: " + name + "!");
		//RPG.inGame = false;
	}
	
	public void render(Graphics g) {
		if (isVisible) { // jezeli widoczny, renderuj
			setImage(new int[] { id[0], id[1]});
			g.drawImage(image, (int) (x - RPG.aX), (int) (y - RPG.aY), null);
			
			if(isAware) {
				g.setColor(Color.white);
				g.drawString("!", (int)(x - RPG.aX +Tile.size/2),(int)(y - RPG.aY));
				isAware = false;
			}
		}
	}

	@Override
	public void tick() {
			
	}
	
	
	
}