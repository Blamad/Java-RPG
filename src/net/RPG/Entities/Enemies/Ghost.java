package net.RPG.Entities.Enemies;

import net.RPG.Core.Core;
import net.RPG.Entities.Enemy;

public class Ghost extends Enemy {

	public Ghost(Core rPG, double x, double y) {
		super(rPG, new int[] { 0, 4 }, x, y);
		
		sight = 4;
		sloth = 1;
		aniTime = 5;
		stats.setMoveSpeed(1);
		stats.setHealth(110);
	}

}
