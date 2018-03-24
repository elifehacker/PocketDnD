package Creatures;

import java.util.ArrayList;

import Objects.Backpack;
import Objects.Items.Consumable;
import Objects.Items.Effect;
import Objects.Items.Equipment;
import Objects.Items.Item;
import World.CharConst;
import World.MessagePrinter;

public class Hero extends Creature{

	private int gold = 0;
	private int exp = 0;
	private ArrayList<Consumable> consumables;
	private ArrayList<Equipment> equipments;
	private int nextlevel =(int) (Math.pow(super.getLevel(),CharConst.lvcurve)*100);;
	private int rank = 1;
	private ArrayList<Equipment> equiped;
	private ArrayList<Effect> buffs;

	public Hero() {
		super();
		super.types.clear();
		super.weakness.clear();
		super.types.add("Human Hero");
		this.consumables = new ArrayList<Consumable>();
		this.equipments = new ArrayList<Equipment>();
		this.equiped = new ArrayList<Equipment>();
		this.buffs = new ArrayList<Effect>();
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

	public void equip(Equipment eqp){
		this.equiped.add(eqp);
	}

	public ArrayList<Equipment> getEquiped(){
		return this.equiped;
	}

	public ArrayList<Consumable> getItems() {
		return consumables;
	}

	public void addItems(ArrayList<Consumable> items) {
		this.consumables.addAll(items);
	}

	public void addItem(Consumable item) {
		MessagePrinter.print("Got a new item! "+item.getName()+"!");
		this.consumables.add(item);
	}

	public ArrayList<Equipment> getEquipments() {
		return equipments;
	}

	public void addEquipment(Equipment item) {
		MessagePrinter.print("Got a new equipment! "+item.getName()+"!");
		this.equipments.add(item);
	}

	public void addGold(int gold) {
		MessagePrinter.print("Got "+gold+" gold!");
		this.gold += gold ;
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

	public void useRecoveryItem(){
		if(this.getCurrentHealth()<=0){
			for(int i = 0; i < this.getItems().size(); i++){
				Consumable it = this.getItems().get(i);
				if(it.getEffectType().equals("Rec") && it.getEffectAttribute().equals("HTH")){
					if(it.getEffectMagnitude().contains("%")){
						int amount = Integer.parseInt(it.getEffectMagnitude().split("%")[0]);
						this.decDamage(this.getHealth()*amount/100);
					}else {
						this.decDamage(Integer.parseInt(it.getEffectMagnitude()));
					}
					MessagePrinter.print("Used item "+it.getName()+". Recovered "+it.getEffectMagnitude()+ " health.");
					this.consumables.remove(i);
					break;
				}
			}
		}
	}

	public void faint(){
		this.setGold(this.getGold()/2);
		MessagePrinter.print(this.getName()+" has no health and fainted. Gold collected halved.");
	}

	public void equipFromBackpack(Backpack backpack){
		equip(backpack.getWeapon());
		equip(backpack.getArmor());
		equip(backpack.getBoots());
		getItems().addAll(backpack.getItems());
		backpack.clearBackpack();

		//player use buff consumable
		useBuffItem();
	}

	private void useBuffItem(){
		for(Consumable item : this.consumables){
			if(item.isBuffItem()){
				int attribute = CharConst.getStatIndex(item.getEffectAttribute());

				if (attribute == CharConst.LVL){
					this.levelup(this.getLevel()+1);
				}else{
					MessagePrinter.print(this.getName()+" had "+item.getName()+" before leaving home.");
					int mag = this.buff1stats(attribute, item.getEffectMagnitude(),false);
					buffs.add(new Effect(attribute,mag));
					this.consumables.remove(item);
				}

			}
		}
	}

	private void deBuff(){
		for(Effect effect : this.buffs){
			this.debuff1stats(effect.getAttribute(), effect.getMagnitude(), false);
		}
		buffs.clear();
	}

	public void stripGears(){
		this.equipments.clear();
		this.consumables.clear();
		this.equiped.clear();
		this.deBuff();
	}

}
