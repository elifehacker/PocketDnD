package Events;

import Creatures.Creature;
import Creatures.Hero;
import Objects.Location;
import World.EventConst;
import World.MessagePrinter;

public class CastleEvent extends Event{

	public CastleEvent(String type, Location loc, Creature ch, int diff, int count) {
		super(type, loc, ch, diff, count);
		// TODO Auto-generated constructor stub

	}
	
	public boolean start(Hero player) {
		MessagePrinter.print(EventConst.getARandomCastleDesc(super.id%3));		
		return super.start(player);
	}


}
