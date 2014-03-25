package net.RPG.Entities;

public class Inventory {
	
	private Item[] items;
	
	public Inventory(int size) {
		items = new Item[size];
		for(int i = 0; i < items.length; i++)
			items[i] = null;
	}
	
	public boolean addItem(Item item) {
		
		for(int i = 0; i < items.length; i++) {
			if(items[i] == null) {
				items[i] = item;
				System.out.println("Item "+ item.name + " picked up.");
				return true;
			}
		}
		return false;
	}
	
	public Item get(int index){
		return items[index];
	}
	
	public Item[] getItemList() {
		return items;
	}
}
