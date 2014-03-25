package net.RPG.Core;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JFrame;

import net.RPG.Entities.Enemy;
import net.RPG.Entities.Entity;
import net.RPG.Entities.Player;
import net.RPG.Entities.Enemies.Ghost;
import net.RPG.Entities.Enemies.Orc;
import net.RPG.Entities.Items.Sword;
import net.RPG.Level.Level;
import net.RPG.Level.Tile;

public class Core extends Applet implements Runnable {

	private static final long serialVersionUID = 1L;

	public final int TARGET_FPS = 60;
	public final long OPTIMAL_TIME = 1000000000/TARGET_FPS;
	public long lastFpsTime = 0;
	public static int fps = 0;
	public static int renderFps = 0;
	
	private static JFrame frame; 					//okno gry
	
	public static final int res = 1; 		//final - nie zmieni sie; static - dostepna dla innych klas
	//public static double aX = 0, aY = 0;	//pozycja rogu ekranu wzgledem calej mapy
	public static double aX = 36*Tile.size, aY = 16*Tile.size;
	public static boolean moving = false;
	public static boolean run = false;		//czy gra chodzi?
	
	private Image screen;
	
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Entity> removeList = new ArrayList<Entity>();
	
	public static boolean bW, bS, bA, bD, bE, bI, bSPACE,bESC, bP;
	public static boolean inGame = true;
	public static boolean inInventory = false;
	
	public static Level level;
	public static Player player;
	public Entity entity;
	public static Core core;
	public static InventoryShow inventoryShow;
	
	public static Dimension screenSize = new Dimension(700,560);	//rozmiar okna
	public static Dimension pixel = new Dimension(screenSize.width/res,screenSize.height/res); //ile pixeli jest na ekranie
	public static Dimension size;
	public static int tiledWidth = pixel.width/Tile.size, tiledHeight = pixel.width/Tile.size;
	
	public static String name = "Pierwszy rpg";	//tytul gry
	
	public Core() {
		setPreferredSize(screenSize);
		addKeyListener(new InputManager());
		
		initEntities();
	}
	
	public void tick() {
		frame.pack();
		
		if(inGame) {
			
			for(int i = 0; i < entities.size();i++) {
				Entity entity = (Entity)entities.get(i);
				entity.tick();
			}
			
			for(int i = 0; i < entities.size();i++) {
				Entity him = (Entity)entities.get(i);
				if(him.visibility() == true && player.collidesWith(him)){
					player.collidedWith(him);
					him.collidedWith(player);
					if(him instanceof Enemy)
						((Enemy) him).lookFor(player);
				}
			}
			entities.remove(removeList);
			removeList.clear();
		}
		if(inInventory) {
			inventoryShow.tick();
		}
	}
	
	public void render() {
		
		Graphics g = screen.getGraphics();
		
		//render poziomu
		level.render(g, (int) aX, (int) aY, pixel.width/Tile.size + 2, pixel.height/Tile.size + 2);
		//render obiektow gry
		for(int i = 0; i < entities.size();i++) {
			Entity entity = (Entity)entities.get(i);
			entity.render(g);
		}
		//wypisz fps i dane
		g.setColor(Color.white);
		g.drawString("CamCor(" + (int)aX+","+(int)aY+")", 590, 530);
		g.drawString("FPS: " + renderFps, 600, 545);
		
		if(inInventory) {
			inventoryShow.render(g);
		}
		
		g = this.getGraphics();
		g.drawImage(screen,0,0,screenSize.width,screenSize.height,0,0,pixel.width,pixel.height,null);
		g.dispose();
	}
	
	public void removeEntity(Entity entity){
		removeList.add(entity);
	}
	
	public void initEntities() {
		entity = new Ghost(core, 46*Tile.size, 26*Tile.size);
		entities.add(entity);
		entity = new Orc(core, 44*Tile.size, 21*Tile.size);
		entities.add(entity);
		entity = new Ghost(core, 53*Tile.size, 24*Tile.size);
		entities.add(entity);
		entity = new Orc(core, 37*Tile.size, 22*Tile.size);
		entities.add(entity);
		entity = new Sword(core, 44*Tile.size,22*Tile.size);
		entities.add(entity);
		player = new Player(core, aX + (tiledHeight/2)*Tile.size , aY + (tiledWidth/2-2)*Tile.size);
		entities.add(player);
	}
	
	public void run() {
		screen = createVolatileImage(pixel.width,pixel.height);
		
		long lastLoopTime = System.nanoTime();
		
		while(run) {
			
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			
			lastFpsTime += updateLength;
			fps++;
			
			if(lastFpsTime >= 1000000000) {
				renderFps = fps;
				fps = 0;
				lastFpsTime = 0;
			}
			
			tick();
			
			render();
			
			try{
				Thread.sleep((lastLoopTime-System.nanoTime() + OPTIMAL_TIME) / 1000000); //6 zer, wazne
			} catch(Exception e) {
				//System.out.println("Sleeping thread fucked up.");
			}
		}
	}
	
	public void start() {
		requestFocus(); //skup sie na ekranie gry, wybierz go od razu
		//define classes
		new Tile();
		level = new Level(core,1);
		inventoryShow = new InventoryShow(this, player);
		
		run = true;
		new Thread(this).start();
	}
	
	public static void main(String args[]) {
		
		core = new Core();
		
		frame = new JFrame();
		frame.add(core);
		frame.pack();
		
		size = new Dimension(frame.getWidth(), frame.getHeight());
		
		//ustawiamy wlasciwosci ramki
		frame.setTitle(name);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);	//nie koreluje z zadnym oknem
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //wazne, gra wylacza sie po wylaczeniu!
		frame.setVisible(true);		//skup sie na nowym oknie
		core.start();
	}

}
