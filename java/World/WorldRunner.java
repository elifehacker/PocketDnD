package World;

import Objects.Home;

public class WorldRunner {

	public static void main(String[] args) {

		if (args.length > 0) {
			try {
				System.err.println("Update set to " + args[0] + " seconds per update");
				World.WorldEngine.pause = Integer.parseInt(args[0]) * 1000;
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

		while (Home.getHome().getReputation() < 40) {
			int num = World.WorldEngine.getRandomInteger(0, 6);
			if (num < 4) Home.getHome().train(num);
			if (num >= 4) Home.getHome().goAdventure();
		}
	}
}