package Creatures;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import Objects.Items.Consumable;
import Objects.Items.Equipment;
import Objects.Items.Souvenir;
import World.CharConst;
import World.MessagePrinter;
import World.WorldEngine;

public class Creature {
	private String name;
	private int level = CharConst.LEVEL;
	private int health = CharConst.HEALTH;
	private int damage = 0;
	
	private ArrayList<Integer> stats = null;	
	private int growth = CharConst.GROWTH;
	
	protected ArrayList<String> types = new ArrayList<String>();
	protected ArrayList<String> weakness ;
	
	private HashMap<Character,Integer> checker = null;
	final int rollDelimiter = 50;

	protected ArrayList<Consumable> consumbales ;
	protected ArrayList<Equipment> equipments ;
	protected ArrayList<Souvenir> souvenirs ;

	public Creature() {
		randomnCh();
		setcheker();
	}
	
	public Creature(int diff, int loc) {
		randomnCh(loc);
		setcheker();
		levelup(diff*CharConst.difficultyLevelScaling);
	}

	private void set4stats() {
		// TODO Auto-generated method stub

		ArrayList<Integer> list  = new ArrayList<Integer>();

		if(stats==null) {
			list.add(WorldEngine.getRandomInteger(CharConst.STRENGTH,CharConst.GROWTH));
			list.add(WorldEngine.getRandomInteger(CharConst.AGILITY,CharConst.GROWTH));
			list.add(WorldEngine.getRandomInteger(CharConst.CHARISMA,CharConst.GROWTH));
			list.add(WorldEngine.getRandomInteger(CharConst.INTELLIGENCE,CharConst.GROWTH));
		}else {
			//called from levelup
			for(int i : stats) {
				list.add(i+WorldEngine.getRandomInteger(growth,growth/10));
			}
		}
		stats = list;			
	}
	
	public void improve1stats(int index) {
		int value = stats.get(index);
		value+=WorldEngine.getRandomInteger(growth,growth/10);
		MessagePrinter.print("Improved "+CharConst.getStatName(index)+" from "+stats.get(index)+" to "+value);
		stats.remove(index);
		stats.add(index, value);
	}

	private void setcheker() {
		// TODO Auto-generated method stub
		checker = new HashMap<Character,Integer>();
		
		checker.put('R',level*10);
		checker.put('C', stats.get(CharConst.CHM));
		checker.put('P', stats.get(CharConst.CHM));
		checker.put('I', stats.get(CharConst.INT));
		checker.put('M', stats.get(CharConst.INT));
		checker.put('S', stats.get(CharConst.AGI));
		checker.put('E', stats.get(CharConst.AGI));

	}

	public void levelup(int lv) {
		while(this.level<lv) {
			this.level++;
			this.health+=health/10+growth*2;
			this.damage = 0;
			set4stats();		
			this.growth=WorldEngine.getRandomInteger(growth*11,growth)/10;
			setcheker();

		}
		printAll();
	}
	
	private void randomnCh() {
		// TODO Auto-generated method stub
		this.name = getRandomString(8);
		this.level = WorldEngine.getRandomInteger(CharConst.LEVEL,1);
		this.health = WorldEngine.getRandomInteger(CharConst.HEALTH,CharConst.GROWTH);
		set4stats();
		this.growth = CharConst.GROWTH;
		this.types.add(CharConst.races.get(WorldEngine.getRandomInteger(0,CharConst.races.size())));

		this.weakness = CharConst.getConterType(this.types);
	}
	
	private void randomnCh(int loc) {
		// TODO Auto-generated method stub
		this.name = getRandomString(8);
		this.level = WorldEngine.getRandomInteger(CharConst.LEVEL,1);
		this.health = WorldEngine.getRandomInteger(CharConst.HEALTH,CharConst.GROWTH);
		set4stats();
		this.growth = CharConst.GROWTH;
		
		this.types.add(CharConst.getRandomRaceByLocation(loc));
		this.weakness = CharConst.getConterType(this.types);
	}
	
	private String getRandomString(int length) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        while (sb.length() < length) { // length of the random string.
            sb.append(SALTCHARS.charAt(rnd.nextInt(SALTCHARS.length())));
        }
        String saltStr = sb.toString();
        return saltStr;

    }
	
	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public int getHealth() {
		return health;
	}

	public int getStrength() {
		return stats.get(CharConst.STR);
	}

	public int getCharisma() {
		return stats.get(CharConst.CHM);
	}

	public int getGrowth() {
		return growth;
	}

	public int getAgility() {
		return stats.get(CharConst.AGI);
	}
	
	public int getIntelligence() {
		return stats.get(CharConst.INT);
	}

	public int getStat(int index) {
		return stats.get(index);
	}
	
	public ArrayList<String> getTypes() {
		return types;
	}

	public ArrayList<String> getWeakness() {
		return weakness;
	}
	
	public void setPerks(ArrayList<String> perks) {
		this.types = perks;
	}

	public void setWeakness(ArrayList<String> weakness) {
		this.weakness = weakness;
	}
	
	public void makeADragon(int lv) {
		types.clear();
		weakness.clear();
		types.add("Dragon");
		growth*=1.5;
		levelup(lv);
	}

	public void printAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("name "+this.name+'\n');
        sb.append("level "+this.level+"\n");
        sb.append("health "+this.health+"\n");
        sb.append("strength "+stats.get(CharConst.STR)+"\n");
        sb.append("charisma "+stats.get(CharConst.CHM)+"\n");
        sb.append("speed "+stats.get(CharConst.AGI)+"\n");
        sb.append("intel "+stats.get(CharConst.INT)+"\n");
        sb.append("growth "+this.growth+"\n");
        sb.append("Class:");
        for(String str: types)
        		sb.append(str+" ");
        sb.append("\nWeakness:");
        for(String wk: weakness)
    			sb.append(wk+" ");  
        //sb.append("\n");
        MessagePrinter.print(sb.toString());
	}

	public boolean check(char type, int diff) {
		// TODO Auto-generated method stub
				
		int sum = 0;
		int target = 0;
		for(int i= 0; i < (diff/1.5>0?diff/1.5:1);i++) {
			target+=WorldEngine.getRandomInteger(3,2);
		}
		int diceCount = checker.get(type)/rollDelimiter;
		if(diceCount==0) diceCount = 1;
		MessagePrinter.print("Check "+getType(type)+" against "+target+" Dice count:"+checker.get(type)+"/50="+diceCount);
		for(int i = 0; i < diceCount;i++) {
			sum+=WorldEngine.getRandomInteger(1,6);
			try {
				Thread.sleep(World.WorldEngine.pause);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MessagePrinter.print("You rolled "+sum);
			if(sum > target) break;

		}
		return sum > target;
	}
	
	private String getType(char c) {
		switch(c) {
		case 'R':
			return "Recognition";
		case 'C':
			return "Conversation";
		case 'P':
			return "Persuasion";
		case 'I':
			return "Investigation";
		case 'M':
			return "Miracle";
		case 'S':
			return "Stealth";
		case 'E':
			return "Exploration";
		}
		return "";
	}

	public static void main(String[] args){
		Creature player = new Creature();
		player.levelup(10);
		player.printAll();
		
		player = new Creature();
		player.levelup(10);
		player.printAll();
		
		player = new Creature();
		player.levelup(10);
		player.printAll();
		
	}
	public int getDamage() {
		return this.damage;
	}
	public int getCurrentHealth() {
		return this.health-this.damage;
	}
	public void incDamage(int dmg) {
		// TODO Auto-generated method stub
		damage+= dmg; 
	}
	public void clearDamge() {
		damage = 0;
	}

}
