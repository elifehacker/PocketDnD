package Events;

import java.util.ArrayList;
import java.util.Random;

import Creatures.Creature;
import Creatures.Hero;
import Objects.Location;
import World.EventConst;
import World.MessagePrinter;

public class Event {

	String eventtype;
	Location location;
	Creature NPC;
	int difficulty;
	int id;
	
	static int acc = 0;
	
	public Event(String type, Location loc, Creature ch, int diff, int count){
		eventtype = type;
		location = loc;
		NPC = ch;
		difficulty = diff;
		id = count;
	}
	

	public int start(Hero player) {
		
		Random r = new Random();
		char type = eventtype.charAt(r.nextInt(eventtype.length()));
		MessagePrinter.print("event start "+eventtype+" "+type);		
		if(type=='F') { 
			if (NPC==null) NPC=new Creature(difficulty, location.getId());
			if(World.WorldEngine.fight(player, NPC)) {
				getExpAndGold(player,2);
				return 1;
			}
			return -1;
		}else if(type=='A') {
			return handleAccident(player);
		}
		
		ArrayList<String> list = EventConst.getEventDesc(type);
		//this prints the event description
		if(list!=null) MessagePrinter.print(list.get(0));

		if(player.check(type, difficulty)) {
			//reward
			if(list!=null) MessagePrinter.print("Succeeded! "+list.get(1));
			getExpAndGold(player,1);
			return 1;
		}else {
			//penalty
			if(list!=null) MessagePrinter.print("Failed! "+list.get(2));
			int dmg = World.WorldEngine.getRandomInteger(player.getHealth()/20, player.getHealth()/20);
			player.incDamage(dmg);
			MessagePrinter.print("Health reduced by "+dmg+" to "+player.getCurrentHealth());
			if(player.getCurrentHealth()<=0)
				return -1;
		}
		
		try {
			Thread.sleep(World.WorldEngine.pause);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	private int handleAccident(Hero player) {
		// TODO Auto-generated method stub
		acc++;
		MessagePrinter.print("A surprise event was triggered.");
		MessagePrinter.print(World.EventConst.getAccDesc(acc%2));

		if(acc%2==1) {
			if(World.WorldEngine.fight(player, player)) {
				getExpAndGold(player,3);
				return 1;
			}
		}else if(acc%2==0){
			Creature dragon = new Creature();
			dragon.makeADragon(difficulty*World.CharConst.difficultyLevelScaling);
			if(World.WorldEngine.fight(player, dragon)) {
				getExpAndGold(player,5);
				return 1;
			}
		}
		return -1;
		
	}


	void getExpAndGold(Hero player, int multiplier) {
		int gold = World.WorldEngine.getRandomInteger(this.difficulty*10,this.difficulty*5);
		int exp = World.WorldEngine.getRandomInteger(this.difficulty*100,this.difficulty*50);
		MessagePrinter.print("Got "+gold*multiplier+" gold and "+exp*multiplier+" exp");
		player.incGold(gold*multiplier);
		player.incExp(exp*multiplier);
	}
	
	public Location getLocation() {
		return this.location;
	}
}
