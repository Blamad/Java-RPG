package net.RPG.Level;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Tile {
	
	public static int[] blank = {-1,-1};
	
	//background
	public static int[][] backTile = new int[65][2];
	
	//collision
	public static int[][] terrTile = new int[257][2];
	
	//items
	
	
	public static int size = 32;
	public static BufferedImage terrain, background, items, characters;
	
	
	public Tile() {
		try{
			Tile.background = ImageIO.read(new File("res/background.png"));
			Tile.terrain = ImageIO.read(new File("res/terrain.png"));
			Tile.characters = ImageIO.read(new File("res/characters.png"));
			Tile.items = ImageIO.read(new File("res/items.png"));
		}catch(Exception e) {
			System.out.println("Error loading images!");
		}
		//tile id creation
		int k = 1;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				backTile[k] = new int[]{j,i};
				k++;
			}
		}
		k = 1;
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 16; j++){
				terrTile[k] = new int[]{j,i};
				k++;
			}
		}
	}
}
