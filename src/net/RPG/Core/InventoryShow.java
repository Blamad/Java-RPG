package net.RPG.Core;

import java.awt.Color;
import java.awt.Graphics;

import net.RPG.Entities.Item;
import net.RPG.Entities.Player;
import net.RPG.Level.Tile;

public class InventoryShow {
	
	private int cX = 400, cY = 150;
	private Player owner = null;
	private Item[] itemArray = null;
	private int x = 0, y = 0, k = 0;
	private Core RPG;
	private boolean scanned = false;
	private int width, height;
	private boolean up = false, down = false, left = false, right = false;
	private int delay = 0, delayVar = 10;
	
	public InventoryShow(Core rPG, Player player) {
		owner = player;
		RPG = rPG;
		width = 4*(2 + Tile.size);
		height = 5*(2+Tile.size) + 15;
	}
	
	//4 na 5
	
	public void tick() {
		if(!scanned){
			itemArray = owner.getInventory().getItemList();
			scanned = true;
		}
		
		if(delay > 0) {
			delay++;
			if(delay > delayVar)
				delay = 0;
		}
		
		if(RPG.bW && delay == 0) {
			up = true;
			delay++;
		}
		else if(RPG.bS && delay == 0) {
			down = true;
			delay++;
		}
		else if(RPG.bA && delay == 0) {
			left = true;
			delay++;
		}
		else if(RPG.bD && delay == 0) {
			right = true;
			delay++;
		}
		
		if(up) {
			if(y > 0)
				y--;
			else
				y = 4;
			up = false;
		} else
		if(down) {
			if(y < 4)
				y++;
			else
				y = 0;
			down = false;
		}
		if(left) {
			if(x > 0)
				x--;
			else
				x = 3;
			left = false;
		}
		if(right) {
			if(x < 3)
				x++;
			else
				x = 0;
			right = false;
		}
		
		if(RPG.bESC || RPG.bI) {
			RPG.bI = false;
			scanned = false;
			RPG.inInventory = false;
			RPG.inGame = true;
		}
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.gray);
		g.fillRect(cX, cY, width, height);
		g.setColor(Color.yellow);
		g.fillRect(cX + (x)*(1 + Tile.size), cY + (y)*(1 + Tile.size), Tile.size, Tile.size);
		//g.setColor(Color.black);
		for(int i = 0; i < itemArray.length; i++) {
			if(itemArray[k] != null)
			// fucks it up	g.fillRect(cX + (k%4 - 1)*(1 + Tile.size) -1, cY + (k%5 - 1)*(1 + Tile.size)-1, cX + (k%4 - 1)*(1 + Tile.size)+Tile.size+1, cY + (k%5 - 1)*(1 + Tile.size)+Tile.size+1);
				g.drawImage(itemArray[k++].getImage(),cX + (k%4 - 1)*(1 + Tile.size), cY + (k%5 - 1)*(1 + Tile.size), null);
		}
		if(itemArray[x + y*4] != null){
			g.setColor(Color.white);
			g.drawString("Item: " + itemArray[x + y*4].name, cX + 10, cY + height - 13);
		}
		k = 0;
	}
}
