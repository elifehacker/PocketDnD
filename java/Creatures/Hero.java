package Creatures;

import java.util.ArrayList;

import Objects.Equipment;
import Objects.Item;
import World.CharConst;
import World.MessagePrinter;

public class Hero extends Creature{

	private int gold = 0;
	private int exp = 0;
	private ArrayList<Item> items;
	private ArrayList<Equipment> equipments;
	private int nextlevel =(int) (Math.pow(super.getLevel(),CharConst.lvcurve)*100);;
	private int rank = 1;
	
	public Hero() {
		super();
		super.types.clear();
		super.weakness.clear();
		super.types.add("Human Hero");

	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public void incGold(int gold) {
		this.gold += gold;
	}
	
	public int getExp() {
		return exp;
	}

	public void incExp(int up) {
		this.exp += up;
		while(exp>=nextlevel) {
			this.exp = this.exp-nextlevel;
			MessagePrinter.print("****Level UP to "+(super.getLevel()+1)+"****");
			super.levelup(super.getLevel()+1);
			this.nextlevel=(int) (Math.pow(super.getLevel(),CharConst.lvcurve)*100);
		}
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void addItems(ArrayList<Item> items) {
		this.items.addAll(items);
	}

	public ArrayList<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(ArrayList<Equipment> equipments) {
		this.equipments = equipments;
	}
	
	public void printAll() {
		super.printAll();
        StringBuilder sb = new StringBuilder();
        sb.append("gold "+this.gold+"\n");
        sb.append("exp "+this.exp+"/"+this.nextlevel+"\n");
        sb.append("rank "+this.rank+"\n");

        MessagePrinter.print(sb.toString());

	}

	public int getRank() {
		// TODO Auto-generated method stub
		return rank;
	}

	public void incRank() {
		// TODO Auto-generated method stub
		rank++;
	}
	
	
}
