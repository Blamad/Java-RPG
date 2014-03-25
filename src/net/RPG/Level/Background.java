package net.RPG.Level;

import java.awt.Graphics;
import java.awt.Rectangle;

import net.RPG.Core.Core;


public class Background extends Rectangle {
	
	public int[] id = {-1,-1};
	
	public Background(Rectangle rect, int id[]) {
		setBounds(rect);
		this.id = id;
	}
	
	public void render(Graphics g) {
		g.drawImage(Tile.background, x - (int)Core.aX, y - (int)Core.aY, x + width - (int)Core.aX, y + height - (int)Core.aY , id[0] * Tile.size, id[1] * Tile.size, id[0] * Tile.size  + Tile.size, id[1] * Tile.size + Tile.size, null);
	}

}