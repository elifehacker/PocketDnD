package Events;

import Creatures.Creature;
import Creatures.Hero;
import Objects.Location;
import World.EventConst;
import World.MessagePrinter;

public class VillageEvent extends Event{

	public VillageEvent(String type, Location loc, Creature ch, int diff, int count) {
		super(type, loc, ch, diff, count);
		// TODO Auto-generated constructor stub
	}
	
	public int start(Hero player) {
		MessagePrinter.print(EventConst.getARandomVillageDesc(super.id%3));		
		return super.start(player);
	}

}
