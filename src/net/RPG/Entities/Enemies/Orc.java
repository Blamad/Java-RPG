package net.RPG.Entities.Enemies;

import net.RPG.Core.Core;
import net.RPG.Entities.Enemy;

public class Orc extends Enemy{

	public Orc(Core rPG, double x, double y) {
		super(rPG, new int[] {3,4}, x, y);
		
		sight = 3;
		sloth = 4;
		aniTime = 3;
		stats.setMoveSpeed(2);
		stats.setHealth(110);
	}

}
