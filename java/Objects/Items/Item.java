package Objects.Items;

import Creatures.Creature;

public class Item {
	int id;
	String name;
	String description;
	Creature owner;
	int quantity;

	public Item(int identifier, String nm, String desc){
		this.id = identifier;
		this.name = nm;
		this.description = desc;
		owner=null;
		quantity = 1;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Creature getOwner() {
		return owner;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setOwner(Creature c){
		this.owner = c;
	}

	public void setQuantity(int i){
		this.quantity = i;
	}

}
