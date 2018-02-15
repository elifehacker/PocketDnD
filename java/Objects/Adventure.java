package Objects;
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
import World.EventConst;
import World.MessagePrinter;

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
	
	public Adventure(Hero c, int diff, int dur) {
		this.player = c;
		this.difficulty = diff;
		this.duration = dur;
		this.events = new ArrayList<Event>();
		
	}

	public boolean start() {
		//go to difficulty number of location, each location has 3 events

		for(int i = 0; i < this.duration; i++) {
			Location loc = EventConst.getARandomPlace();
			int locid = loc.getId(); 

			for(int k = 0; k< numofEventperLoc; k++) {

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
				if(k+1==numofEventperLoc && World.WorldEngine.getRandomInteger(0, 10) < 3) sb.append("A");
				//sb.append("A");
				
				Creature c = null;
				
				if(locid < 200) { //Village
					sb.append(Z).append(Y);
					ev = new VillageEvent(sb.toString(), loc, c, difficulty, i*numofEventperLoc+k);		
					
				}else if(locid <300) {//Mountain
					sb.append(X);
					ev = new MountainEvent(sb.toString(), loc, c, difficulty, i*numofEventperLoc+k);		
					
				}else if(locid <400) {//Dungeon
					sb.append(Z).append(X);
					ev = new DungeonEvent(sb.toString(), loc, c, difficulty, i*numofEventperLoc+k);		
					
				}else if(locid <500) {//Forest
					sb.append(X);
					ev = new ForestEvent(sb.toString(), loc, c, difficulty, i*numofEventperLoc+k);		
					
				}else if(locid <600) {//River
					sb.append(X);
					ev = new WaterEvent(sb.toString(), loc, c, difficulty, i*numofEventperLoc+k);		

				}else if(locid <700) {//Castle
					sb.append(X).append(Y).append(Z);
					ev = new CastleEvent(sb.toString(), loc, c, difficulty, i*numofEventperLoc+k);		
					
				}else if(locid <800) {//Road
					sb.append(X).append(Y);
					ev = new RoadEvent(sb.toString(), loc, c, difficulty, i*numofEventperLoc+k);		
					
				}else if(locid <900) {//Dream
					sb.append(X).append(Y).append(Z);
					ev = new DreamEvent(sb.toString(), loc, c, difficulty, i*numofEventperLoc+k);		

				}
				
				if(ev==null) ev = new Event(sb.toString(), loc, new Creature(), difficulty, i*numofEventperLoc+k);					
				this.events.add(ev);
				
			}
		}
		
		for(Event ev : events) {
			MessagePrinter.print("=== At "+ev.getLocation().getName()+" ===");

			if(!ev.start(player)) {
				player.setGold(player.getGold()/2); 
				MessagePrinter.print(player.getName()+" has no health and fainted. Gold collected halved.");
				return false;
			}
		}
		
		MessagePrinter.print("**** Adventure completed! ****");
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
		player.levelup(7);
		Adventure a1 = new Adventure(player, 3, 3);
		a1.start();
	}
}
