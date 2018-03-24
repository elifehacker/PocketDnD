package World;
import java.util.ArrayList;
import java.util.Hashtable;

public class CharConst {
	public static int LEVEL = 3;
	public static int HEALTH = 60;
	public static int STRENGTH= 30;
	public static int CHARISMA = 30;
	public static int AGILITY = 30;
	public static int INTELLIGENCE = 30;
	public static int GROWTH = 10;

	public final static int STR = 0;
	public final static int AGI = 1;
	public final static int CHM = 2;
	public final static int INT = 3;

	public final static int HTH = 4;
	public final static int GRW = 5;
	public final static int LVL = 6;


	public static ArrayList<String> races = MyFileReader.readFromFile2("races.txt");
	public static Hashtable<String, Integer> racesLoc = MyFileReader.readFromFile("racesWithLoc.txt");

	public static double bonusmultiplier = 1.1;
	public static double criticalmultiplier = 1.5;
	public final static double lvcurve = 1.7;

	public static int difficultyLevelScaling = 3;
	
	Hashtable<String, Integer> CHARCONST= MyFileReader.readFromFile("CharConst.txt");

	private static ArrayList<String> newlist() {
		// TODO Auto-generated method stub
		ArrayList<String> type = new ArrayList<String>();
		type.add("Human");
		type.add("Elf");//agi and int
		type.add("Dwarf");//strength
		type.add("Orc");//health
		type.add("Lizardman");
		type.add("Mermaid");//agi
		type.add("Gaint");//health and strength
		type.add("Hydra");//health and strength		
		
		type.add("Goblin");//agi
		type.add("Troll");//strength
		type.add("Spider");//agi
		type.add("Mimic");//health
		type.add("Spirit");//int
		type.add("Shade");//strength
		type.add("Hell Hound");//agi
		type.add("Bat");//agi
		return type;
	}
	
	public static ArrayList<String> getConterType(ArrayList<String> strength) {
		// TODO Auto-generated method stub
		ArrayList<String> weakness = new ArrayList<String> ();
		for(String str:strength) {
			/*
			if(str.equals("Swordman")) weakness.add("Lancer");
			if(str.equals("Lancer")) weakness.add("Axeman");
			if(str.equals("Axeman")) weakness.add("Swordman");
			if(str.equals("Mage")) weakness.add("Rogue");
			if(str.equals("Rogue")) weakness.add("Templar");
			if(str.equals("Templar")) weakness.add("Mage");
			 */
		}
		return weakness;
	}
	
	public static String getStatName(int index) {
		if(index == STR) return "Strength";
		if(index == AGI) return "Agility";
		if(index == CHM) return "Charisma";
		if(index == INT) return "Intelligence";
		if(index == HTH) return "Health";
		if(index == GRW) return "Growth";
		if(index == LVL) return "Level";
		return "";
	}

	public static int getStatIndex(String acronym) {
		if(acronym.equals("STR")) return STR;
		if(acronym.equals("AGI")) return AGI;
		if(acronym.equals("CHM")) return CHM;
		if(acronym.equals("INT")) return INT;
		if(acronym.equals("HTH")) return HTH;
		if(acronym.equals("GRW")) return GRW;
		if(acronym.equals("LVL")) return LVL;
		return 99;
	}
	
	public static int binaryGenerator(int loc ) {
		/*
		 * 0 Home
		 * 100 Village Y Z
		 * 200 Mountain X
		 * 300 Dungeon X Z
		 * 400 Forest X
		 * 500 River X
		 * 600 Castle Y X 
		 * 700 Road X
		 * 800 Dream Y X
		 */
		int num = 1;
		num=num<<(loc/100);
		return num;
	}
	
	public static boolean binaryDecoder(int loc, int total ) {
		/*
		 * 0 Home
		 * 100 Village Y Z
		 * 200 Mountain X
		 * 300 Dungeon X Z
		 * 400 Forest X
		 * 500 River X
		 * 600 Castle Y X 
		 * 700 Road X
		 * 800 Dream Y X
		 */
		//MessagePrinter.print(total>>(loc/100));
		return ((total>>(loc/100))%2==1);
	}
	
	public static int outdoor() {
		return binaryGenerator(200)+binaryGenerator(400)+binaryGenerator(500)+binaryGenerator(700);
	}
	
	public static String getRandomRaceByLocation(int loc) {
		int i = 0;
		for(String c : racesLoc.keySet()) {
			if(binaryDecoder(loc, racesLoc.get(c)))
				i++;
		}		
		i=World.WorldEngine.getRandomInteger(1, i);
		for(String c : racesLoc.keySet()) {
			if(binaryDecoder(loc, racesLoc.get(c))) {
				if (i==1) return c;
				i--;
			}
		}
		return "----";
	}
	
	public static void main(String[] args){
		MessagePrinter.print(""+binaryGenerator(500));
		MessagePrinter.print(""+binaryDecoder(500,1));
		for(String r: CharConst.races) {
			int total = 0; 
			if(r.equals("Human")) {
				total = binaryGenerator(100)+binaryGenerator(600)+binaryGenerator(700);
			}else if(r.equals("Elf")) {
				total = binaryGenerator(100)+binaryGenerator(400);
			}else if(r.equals("Dwarf")) {
				total = binaryGenerator(100)+binaryGenerator(200);
			}else if(r.equals("Orc")) {
				total = binaryGenerator(200)+binaryGenerator(400);
			}else if(r.equals("Lizardman")) {
				total = binaryGenerator(500)+binaryGenerator(700);
			}else if(r.equals("Mermaid")) {
				total = binaryGenerator(500)+binaryGenerator(800);
			}else if(r.equals("Gaint")) {
				total = binaryGenerator(200)+binaryGenerator(500);
			}else if(r.equals("Hydra")) {
				total = binaryGenerator(400)+binaryGenerator(500);
			}else if(r.equals("Goblin")) {
				total = binaryGenerator(300)+binaryGenerator(200);
			}else if(r.equals("Troll")) {
				total = binaryGenerator(300)+binaryGenerator(400);
			}else if(r.equals("Spider")) {
				total = binaryGenerator(200)+binaryGenerator(300)+binaryGenerator(400)+binaryGenerator(700);
			}else if(r.equals("Mimic")) {
				total = binaryGenerator(600)+binaryGenerator(300);
			}else if(r.equals("Spirit")) {
				total = binaryGenerator(400)+binaryGenerator(800);
			}else if(r.equals("Shade")) {
				total = binaryGenerator(300)+binaryGenerator(600);
			}else if(r.equals("Hound")) {
				total = binaryGenerator(100)+binaryGenerator(700);
			}else if(r.equals("Bat")) {
				total = binaryGenerator(200)+binaryGenerator(300)+binaryGenerator(400)+binaryGenerator(700);
			}else if(r.equals("Skeleton")) {
				total = binaryGenerator(300);
			}
			MessagePrinter.print(r+"="+total);
			//MessagePrinter.print(r+"="+total+" "+binaryDecoder(200,total));
		}

	}
			
}
