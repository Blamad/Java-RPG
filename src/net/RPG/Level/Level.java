package net.RPG.Level;

import java.awt.Graphics;
import java.awt.Rectangle;

import net.RPG.Core.Core;
import net.RPG.Entities.Entity;
import net.RPG.Entities.Player;
import net.RPG.Entities.Enemies.Ghost;
import net.RPG.Entities.Enemies.Orc;
import net.RPG.Entities.Items.Sword;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Level {

	private int width = 100, height = 100;

	public Background[][] bg = new Background[width][height];
	public Solid[][] solid = new Solid[width][height];

	private final String Dpath = "res/world/level_";
	private String path = Dpath;

	private TiledMap map = null;
	private Core RPG;

	public Level(Core rPG, int id) {
		RPG = rPG;
		path = Dpath + Integer.toString(id) + ".tmx";
		System.out.println(path);

		try {
			map = new TiledMap(path, false);
		} catch (SlickException e) {
			System.out.println("Error loading map!");
		}

		// inicjacja
		for (int x = 0; x < bg.length; x++) {
			for (int y = 0; y < bg[0].length; y++) {
				bg[x][y] = new Background(new Rectangle(x * Tile.size, y
						* Tile.size, Tile.size, Tile.size), Tile.blank);
				solid[x][y] = new Solid(new Rectangle(x * Tile.size, y
						* Tile.size, Tile.size, Tile.size), Tile.blank);
			}
		}
		loadWorld();
	}

	public void loadWorld() {
		int background = map.getLayerIndex("background");
		int solids = map.getLayerIndex("collision");
		int entities = map.getLayerIndex("object");
		Entity entity;

		for (int x = 0; x < bg.length; x++) {
			for (int y = 0; y < bg[0].length; y++) {

				// background
				for (int k = 1; k < 65; k++) {
					if (map.getTileId(x, y, background) == k) {
						bg[x][y].id = Tile.backTile[k];
					}
				}

				// solids
				if (map.getTileId(x, y, solids) != 0) {
					for (int k = 1; k < 257; k++) {
						if (map.getTileId(x, y, solids) == k + 64) {
							solid[x][y].id = Tile.terrTile[k];
						}
					}
				}

				// entities
/*				switch (map.getTileId(x, y, entities)) {
				
				case 0:
					break;
				case 321:
					RPG.aX = x * Tile.size - (RPG.tiledHeight/2)*Tile.size;
					RPG.aY = y * Tile.size - (RPG.tiledWidth/2-2)*Tile.size;
					entity = new Player(RPG, x * Tile.size, y * Tile.size);
					RPG.entities.add(entity);
					break;
				case 322:
					entity = new Ghost(RPG, x * Tile.size, y * Tile.size);
					RPG.entities.add(entity);
					break;
				case 323:
					entity = new Orc(RPG, x * Tile.size, y * Tile.size);
					RPG.entities.add(entity);
					break;
				case 329:
					entity = new Sword(RPG, x * Tile.size, y * Tile.size);
					RPG.entities.add(entity);
					break;
				}*/
			}
		}
	}

	public void tick() {

	}

	public void render(Graphics g, int camX, int camY, int renX, int renY) {

		for (int x = camX / Tile.size; x < camX / Tile.size + renX; x++) {
			for (int y = camY / Tile.size; y < camY / Tile.size + renY; y++) {
				if (x >= 0 && y >= 0 && x < width && y < height) {
					bg[x][y].render(g);
					if (solid[x][y].id != Tile.blank)
						solid[x][y].render(g);
				}
			}
		}
	}
}
