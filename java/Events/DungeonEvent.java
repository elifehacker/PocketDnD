package Events;

import Creatures.Creature;
import Creatures.Hero;
import Objects.Location;
import World.EventConst;
import World.MessagePrinter;

public class DungeonEvent extends Event{

	public DungeonEvent(String type, Location loc, Creature ch, int diff, int count) {
		super(type, loc, ch, diff, count);
		// TODO Auto-generated constructor stub
	}
	
	public boolean start(Hero player) {
		MessagePrinter.print(EventConst.getARandomDungeonDesc(super.id%3));		
		return super.start(player);
	}

}
