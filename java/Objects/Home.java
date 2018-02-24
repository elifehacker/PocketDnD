package Objects;

import java.util.ArrayList;

import Creatures.Creature;
import Creatures.Hero;

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
	
	private Home() {
		residents = new ArrayList<Creature>();
		Hero h = new Hero();
		h.levelup(3);
		residents.add(h);
		player = h;
		setReputation();
	}
	
	private void setReputation() {
		// TODO Auto-generated method stub
		reputation = player.getRank()*10+player.getLevel();
		System.out.println("New Reputation is "+reputation);
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
		System.out.println("**** Home **** ");
		player.printAll();
		System.out.println("Gold "+this.gold);
		System.out.println("Reputation "+this.reputation);
		System.out.println("Total training "+this.train_c);
		System.out.println("Successful Adventure "+this.adv_suc+"/"+(this.adv_fail+this.adv_suc));

	}
	
	public synchronized void goAdventure() {
		if(athome) {
			if(!training) {
				athome = false;
				int diff = reputation/10;
				if(World.WorldEngine.getRandomInteger(0, 10)>3)diff--;

				if (diff<=0) diff =1;
				
				Adventure adv = new Adventure(player, diff, 3);
				System.out.println("**** Adventure rank "+diff+" starts **** ");
				this.gold+=100;
				
				if(adv.start()) {
					if (diff == reputation/10)player.incRank();
					adv_suc++;
				}else {
					adv_fail++;
				}
				
				this.gold += player.getGold();
				System.out.println("Gold collected "+player.getGold());
				
				player.setGold(0);
				setReputation();
				player.clearDamge();
				athome = true;
				
				this.printAll();
			}
		}
	}
	
	public static Home getHome() {
		if(instance == null) instance = new Home();
		return instance;
	}
	
	public int getReputation() {
		return reputation;
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
        System.out.println("**** Home **** ");

		while(Home.getHome().getReputation()<30) {
			int num = World.WorldEngine.getRandomInteger(0, 6);
			if(num<4) Home.getHome().train(num);
			if(num >= 4) Home.getHome().goAdventure();
		}
	}

}
