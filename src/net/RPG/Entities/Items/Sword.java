package net.RPG.Entities.Items;

import net.RPG.Core.Core;
import net.RPG.Entities.Item;

public class Sword extends Item {

	public Sword(Core rPG, int x, int y) {
		super(rPG, new int[] {0,5}, x, y);
		name = "Sword";
	}

}
