package Objects.Items;

import android.support.annotation.NonNull;

import Creatures.Creature;

public class Item implements Comparable<Item>{
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

	public boolean isWeapon(){
		if(60000<this.getId()&& this.getId()<90000) return true;
		return false;
	}

	public boolean isArmor(){
		if(50000<this.getId()&& this.getId()<60000) return true;
		return false;
	}

	public boolean isBoots(){
		if(40000<this.getId()&& this.getId()<50000) return true;
		return false;
	}

	@Override
	public int compareTo(@NonNull Item item) {
		if(item.getId()>this.getId()) return 1;
		if(item.getId()<this.getId()) return -1;

		return 0;
	}
}
