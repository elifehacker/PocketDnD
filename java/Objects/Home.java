package Objects;

import java.util.ArrayList;

import Creatures.Creature;
import Creatures.Hero;
import Objects.Items.Consumable;
import Objects.Items.Equipment;
import Objects.Items.Item;
import Objects.Items.Souvenir;
import World.MessagePrinter;

public class Home {

	private ArrayList<Creature> residents;
	private Hero player;
	private static Home instance = null;
	private boolean athome = true;
	private boolean training = false;
	private int reputation = 0;
	private int gold = 400;

	private int train_c =0;
	private int adv_suc = 0;
	private int adv_fail = 0;

	private ArrayList<Consumable> consumables;
	private ArrayList<Equipment> equipments;
	private ArrayList<Souvenir> souvenirs;

	private Home() {
		this.residents = new ArrayList<Creature>();
		Hero h = new Hero();
		h.levelup(3);
		this.residents.add(h);
		this.player = h;
		setReputation();

		this.consumables = new ArrayList<Consumable>();
		this.equipments = new ArrayList<Equipment>();
		this.souvenirs = new ArrayList<Souvenir>();
	}
	
	private void setReputation() {
		// TODO Auto-generated method stub
		reputation = player.getRank()*10+player.getLevel();
		MessagePrinter.print("New Reputation is "+reputation);
	}

	public synchronized void train(int index) {
		if(athome) {
			int required = player.getStat(index)/50;
			required*=50;
			required+=50;
			if(gold<required) return;
			
			training = true;
			gold-=required;
			try {
				Thread.sleep(World.WorldEngine.training);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			player.improve1stats(index);
			train_c++;
			training = false;
		}

	}
	
	public void printAll() {
		MessagePrinter.print("**** Home **** ");
		player.printAll();
		MessagePrinter.print("Household Gold: "+this.gold);
		MessagePrinter.print("Reputation: "+this.reputation);
		MessagePrinter.print("Total training: "+this.train_c);
		MessagePrinter.print("Successful Adventure: "+this.adv_suc+"/"+(this.adv_fail+this.adv_suc));

	}
	
	public synchronized void goAdventure() {
		if(athome) {
			if(!training) {
				athome = false;
				int diff = reputation/10;
				if(World.WorldEngine.getRandomInteger(0, 10)>3)diff--;

				if (diff<=0) diff =1;
				
				Adventure adv = new Adventure(player, diff, 3);
				MessagePrinter.print("**** Adventure rank "+diff+" starts **** ");
				this.gold+=100;
				
				if(adv.start()) {
					if (diff == reputation/10)player.incRank();
					adv_suc++;
				}else {
					adv_fail++;
				}
				
				this.gold += player.getGold();
				MessagePrinter.print("Gold collected "+player.getGold());
				player.setGold(0);

				putGearFromBackpackToHome("Items", player.getItems(), this.consumables);
				putGearFromBackpackToHome("Equipments", player.getEquipments(), this.equipments);
				player.stripGears();

				setReputation();
				player.clearDamge();
				athome = true;
				
				this.printAll();
			}
		}
	}

	private <T> void putGearFromBackpackToHome (String str, ArrayList<T> fromlist, ArrayList<T> tolist){
		MessagePrinter.print(str+" collected:");
		StringBuffer sb = new StringBuffer();
		for(T item : fromlist){
			sb.append(((Item)item).getName()+". ");
			tolist.add(item);
		}
		MessagePrinter.print(sb.toString());
	}
	
	public static Home getHome() {
		if(instance == null) instance = new Home();
		return instance;
	}
	
	public int getReputation() {
		return reputation;
	}

	public Hero getPlayer(){
		return this.player;
	}

	public ArrayList<Equipment> getEquipments(){
		return equipments;
	}

	public ArrayList<Consumable> getConsumables(){
		return consumables;
	}

	public ArrayList<Souvenir> getSouvenirs(){
		return souvenirs;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length > 0) {
		    try {
		        System.err.println("Update set to " + args[0]+" seconds per update");
		        World.WorldEngine.pause = Integer.parseInt(args[0])*1000;
		    } catch (NumberFormatException e) {
		        System.err.println("Argument" + args[0] + " must be an integer.");
		        System.exit(1);
		    }
		}
        System.err.println("**** Welcome to the world of Pocket DnD ****");
        System.err.println("A young hero, hoping to achieve great wonders.");
        System.err.println("Would you witness and guide him through his journey?");
		
        try {
			Thread.sleep(World.WorldEngine.pause);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        MessagePrinter.print("**** Home **** ");

		while(Home.getHome().getReputation()<20) {
			int num = World.WorldEngine.getRandomInteger(0, 6);
			if(num<4) Home.getHome().train(num);
			if(num >= 4) Home.getHome().goAdventure();
		}
	}

}
