package Objects.Items;

import Creatures.Creature;

public class Item {
	int id;
	String description;
	Creature owner;
	int quantity;

	public Item(int identifier, String desc){
		this.id = identifier;
		this.description = desc;
		owner=null;
		quantity = 0;
	}

	public void setOwner(Creature c){
		this.owner = c;
	}

	public void setQuantity(int i){
		this.quantity = i;
	}

}
