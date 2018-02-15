package World;

import Creatures.Hero;
import Objects.Adventure;

public class WorldRunner {

public static void main(String[] args){
		
		if (args.length > 0) {
		    try {
		        System.err.println("Update set to " + args[0]+" seconds per update");
		        WorldEngine.pause = Integer.parseInt(args[0])*1000;
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
