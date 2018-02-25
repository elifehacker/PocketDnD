package Events;

import Creatures.Creature;
import Creatures.Hero;
import Objects.Location;
import World.EventConst;
import World.MessagePrinter;

public class ForestEvent extends Event{

	public ForestEvent(String type, Location loc, Creature ch, int diff, int count) {
		super(type, loc, ch, diff, count);
		// TODO Auto-generated constructor stub
	}
	
	public int start(Hero player) {
		MessagePrinter.print(EventConst.getARandomForestDesc(super.id%3));		
		return super.start(player);
	}

}
