package Objects;
import android.util.Log;

import java.util.ArrayList;

import Creatures.Creature;
import Creatures.Hero;
import Events.CastleEvent;
import Events.DreamEvent;
import Events.DungeonEvent;
import Events.Event;
import Events.ForestEvent;
import Events.MountainEvent;
import Events.RoadEvent;
import Events.VillageEvent;
import Events.WaterEvent;
import Objects.Items.Consumable;
import Objects.Items.Equipment;
import World.EventConst;
import World.ItemConst;
import World.MessagePrinter;
import World.WorldEngine;

import static World.WorldEngine.getRandomInteger;

public class Adventure {

	Hero player;
	int difficulty;
	int duration;
	ArrayList<Event> events;
	final int numofEventperLoc = 3;
	/*
	ArrayList<String> FSE = setTypes("FSE");
	ArrayList<String> RCP = setTypes("RCP");
	ArrayList<String> IM = setTypes("IM");
	*/
	String X = new String("FSE");
	String Y = new String("RCP");
	String Z = new String("IM");


	Location currentLoc = null;
	int currentSuccess = 0;
	int currentDur = 0;
	int currentEvt = 0;

	public Adventure(Hero c, int diff, int dur) {
		this.player = c;
		this.difficulty = diff;
		this.duration = dur;
		this.events = new ArrayList<Event>();
		
	}

	public int getDiff(){
		return difficulty;
	}

	//remember to call this after loading from previous save
	public void setHero(Hero h){
		this.player = h;
	}

	public int start() {
		//go to difficulty number of location, each location has 3 events
		while(currentDur < this.duration){

			if(currentEvt == 0){
				//only generate a new location when starting anew.
				//keep the currentLocation if this method was called from a previous save.
				currentLoc = EventConst.getARandomPlace();
				currentSuccess = 0;
			}

			int locid = currentLoc.getId();

			while (currentEvt< numofEventperLoc) {

				/*
				 * 0 Home
				 * 100 Village Y Z
				 * 200 Mountain X
				 * 300 Dungeon X Z
				 * 400 Forest X
				 * 500 River X
				 * 600 Castle Y X Z
				 * 700 Road X Y
				 * 800 Dream Y X Z
				 * 
				 * 	 X:FSE
					 Y:RCP
					 Z:IM
				 */
				StringBuilder sb = new StringBuilder();
				Event ev = null;
				
				//1/3 *1/3 *1/3 of the time an accident could happen
				if(currentEvt+1==numofEventperLoc && getRandomInteger(0, 10) < 3) sb.append("A");
				//sb.append("A");
				
				Creature c = null;
				
				if(locid < 200) { //Village
					sb.append(Z).append(Y);
					ev = new VillageEvent(sb.toString(), currentLoc, c, difficulty, currentDur*numofEventperLoc+currentEvt);
					
				}else if(locid <300) {//Mountain
					sb.append(X);
					ev = new MountainEvent(sb.toString(), currentLoc, c, difficulty, currentDur*numofEventperLoc+currentEvt);
					
				}else if(locid <400) {//Dungeon
					sb.append(Z).append(X);
					ev = new DungeonEvent(sb.toString(), currentLoc, c, difficulty, currentDur*numofEventperLoc+currentEvt);
					
				}else if(locid <500) {//Forest
					sb.append(X);
					ev = new ForestEvent(sb.toString(), currentLoc, c, difficulty, currentDur*numofEventperLoc+currentEvt);
					
				}else if(locid <600) {//River
					sb.append(X);
					ev = new WaterEvent(sb.toString(), currentLoc, c, difficulty, currentDur*numofEventperLoc+currentEvt);

				}else if(locid <700) {//Castle
					sb.append(X).append(Y).append(Z);
					ev = new CastleEvent(sb.toString(), currentLoc, c, difficulty, currentDur*numofEventperLoc+currentEvt);
					
				}else if(locid <800) {//Road
					sb.append(X).append(Y);
					ev = new RoadEvent(sb.toString(), currentLoc, c, difficulty, currentDur*numofEventperLoc+currentEvt);
					
				}else if(locid <900) {//Dream
					sb.append(X).append(Y).append(Z);
					ev = new DreamEvent(sb.toString(), currentLoc, c, difficulty, currentDur*numofEventperLoc+currentEvt);

				}
				
				if(ev==null) ev = new Event(sb.toString(), currentLoc, new Creature(), difficulty, currentDur*numofEventperLoc+currentEvt);

				MessagePrinter.print("=== At "+ev.getLocation().getName()+" ===");
				int result = ev.start(player);
				if(result==-1) {
					//player has 0 or less health, try using a recovery item.
					MessagePrinter.print("[!] Our hero "+ player.getName()+" has no health left!");
					player.useRecoveryItem();
					if(player.getCurrentHealth()>0) {
						continue;
					}else{
						player.faint();
					}
					return -1;
				}else {
					if(result==1) currentSuccess++;
					if(currentEvt+1==numofEventperLoc) {
						if(!getReward(currentEvt, currentSuccess)) return -1;
					}
				}

				currentEvt++;
				WorldEngine.catchingUpTime(World.WorldEngine.event);
				if(!WorldEngine.catchingUpTime()){
					//time limit reached. force quit
					Log.e("Debug","In adventure, time limit reached");

					return 0;
				}

			}

			currentEvt = 0;
			currentDur++;
		}
		
		MessagePrinter.print("**** Adventure completed! ****");
		return 1;
	}

	private boolean getReward(int k, int suc){
		//rewards
		MessagePrinter.print("**** Quest completed with "+suc+"/"+numofEventperLoc+" success. Getting Rewards! ****");
		int rnd = getRandomInteger(0,70);
		rnd+= suc*20;
		if(0<rnd && rnd<=15){
			//fight a mimic
			MessagePrinter.print("The Chest started moving... It is a Mimic!");
			Creature mimic = new Creature();
			mimic.makeAMimic(player.getLevel());
			return WorldEngine.fight(player, mimic);

		}else if(15<rnd && rnd<=40){
			MessagePrinter.print("An Empty Chest...");

		}else if(40<rnd && rnd<=65){
			//1 consumables
			player.addItem((Consumable) ItemConst.getRandomItem(player.getRank(),"consumables"));
			player.addGold(WorldEngine.getRandomInteger(this.difficulty*50,50));

		}else if(65<rnd && rnd<=85){
			//gold
			player.addGold(WorldEngine.getRandomInteger(this.difficulty*100,120));

		}else if(85<rnd&& rnd<=110){
			//1 equipment
			player.addEquipment((Equipment) ItemConst.getRandomItem(player.getRank(),"equipments"));
			player.addGold(WorldEngine.getRandomInteger(this.difficulty*50,50));

		}else if(110<rnd) {
			//1 equipment and 1 consumables
			player.addEquipment((Equipment) ItemConst.getRandomItem(player.getRank(),"equipments"));
			player.addItem((Consumable) ItemConst.getRandomItem(player.getRank(),"consumables"));
			player.addGold(WorldEngine.getRandomInteger(this.difficulty*100,150));

		}
		return true;
	}

	public Hero getHero() {
		return player;
	}
	
	public static void main(String[] args){
		
		if (args.length > 0) {
		    try {
		        System.err.println("Update set to " + args[0]+" seconds per update");
		        World.WorldEngine.pause = Integer.parseInt(args[0])*1000;
		    } catch (NumberFormatException e) {
		        System.err.println("Argument" + args[0] + " must be an integer.");
		        System.exit(1);
		    }
		}
		
		Hero player = new Hero();
		player.levelup(7, false);
		Adventure a1 = new Adventure(player, 3, 3);
		a1.start();
	}
}
