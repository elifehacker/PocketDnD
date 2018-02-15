package World;

import java.util.ArrayList;

import Objects.Location;


public class EventConst {

	public static int HOME = 0;
	public static int GOLDCLEAR = 2; //gold will be cleared to 0 if the player fainted above this level of difficulty 

	static ArrayList<Location> LOCS= MyFileReader.readFromFile3("./LocConst.txt");
	static ArrayList<String> TYPE= getType();
	
	static ArrayList<String> DUNP1 = MyFileReader.readFromFile2("./locationDesc/dungeonp1.txt");
	static ArrayList<String> DUNP2 = MyFileReader.readFromFile2("./locationDesc/dungeonp2.txt");
	static ArrayList<String> DUNP3 = MyFileReader.readFromFile2("./locationDesc/dungeonp3.txt");

	static ArrayList<String> FORP1 = MyFileReader.readFromFile2("./locationDesc/Dreamp1.txt");
	static ArrayList<String> FORP2 = MyFileReader.readFromFile2("./locationDesc/Dreamp2.txt");
	static ArrayList<String> FORP3 = MyFileReader.readFromFile2("./locationDesc/Dreamp3.txt");
	
	static ArrayList<String> MNTP1 = MyFileReader.readFromFile2("./locationDesc/mountainp1.txt");
	static ArrayList<String> MNTP2 = MyFileReader.readFromFile2("./locationDesc/mountainp2.txt");

	static ArrayList<String> VILP1 = MyFileReader.readFromFile2("./locationDesc/villagep1.txt");
	static ArrayList<String> VILP2 = MyFileReader.readFromFile2("./locationDesc/villagep2.txt");
	
	static ArrayList<String> CASP1 = MyFileReader.readFromFile2("./locationDesc/castlep1.txt");
	static ArrayList<String> CASP2 = MyFileReader.readFromFile2("./locationDesc/castlep2.txt");
	static ArrayList<String> CASP3 = MyFileReader.readFromFile2("./locationDesc/castlep3.txt");
	
	static ArrayList<String> WTRP1 = MyFileReader.readFromFile2("./locationDesc/waterp1.txt");
	static ArrayList<String> WTRP2 = MyFileReader.readFromFile2("./locationDesc/waterp2.txt");
	
	static ArrayList<String> RDP1 = MyFileReader.readFromFile2("./locationDesc/roadp1.txt");
	
	static ArrayList<String> DRMP1 = MyFileReader.readFromFile2("./locationDesc/dreamp1.txt");
	static ArrayList<String> DRMP2 = MyFileReader.readFromFile2("./locationDesc/dreamp2.txt");
	static ArrayList<String> DRMP3 = MyFileReader.readFromFile2("./locationDesc/dreamp3.txt");

	static ArrayList<String> ACC1 = MyFileReader.readFromFile2("./eventDesc/accidentDesc1.txt");
	static ArrayList<String> ACC2 = MyFileReader.readFromFile2("./eventDesc/accidentDesc2.txt");

	static ArrayList<String> Recognition = MyFileReader.readFromFile2("./eventDesc/Recognition.txt");
	static ArrayList<String> Persuasion = MyFileReader.readFromFile2("./eventDesc/Persuasion.txt");
	static ArrayList<String> Persuasion2 = MyFileReader.readFromFile2("./eventDesc/Persuasion2.txt");
	static ArrayList<String> Conversation = MyFileReader.readFromFile2("./eventDesc/Conversation.txt");
	static ArrayList<String> Investigation = MyFileReader.readFromFile2("./eventDesc/Investigation.txt");
	static ArrayList<String> Miracle = MyFileReader.readFromFile2("./eventDesc/Miracle.txt");
	static ArrayList<String> Stealth = MyFileReader.readFromFile2("./eventDesc/Stealth.txt");
	static ArrayList<String> Exploration = MyFileReader.readFromFile2("./eventDesc/Exploration.txt");
	
	public static Location getARandomPlace() {
		int num = WorldEngine.getRandomInteger(1, LOCS.size()-1);
		//int num = 11; 

		return LOCS.get(num);
	}
	private static ArrayList<String> getType() {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		list.add("Recognition");//Level, equipments. other status
		list.add("Conversation");//Charisma
		list.add("Persuasion");
		list.add("Fight"); //Strength
		//list.add("Intimation");
		list.add("Investigation"); //Intelligence
		list.add("Miracle");
		list.add("Stealth"); // Agility
		list.add("Exploration");
		//list.add("Sleight of hand"); 
		list.add("Surpirse");//Could be anything
		return list;
	}
	
	public static ArrayList<String> getEventDesc(char type) {
		// TODO Auto-generated method stub
		if(type == 'R') return Recognition;
		if(type == 'C') return Conversation;
		if(type == 'P') {
			if(World.WorldEngine.getRandomInteger(0, 2)==0) return Persuasion;
			return Persuasion2;
		}
		if(type == 'I') return Investigation;
		if(type == 'M') return Miracle;
		if(type == 'S') return Stealth;
		if(type == 'E') return Exploration;
		return null;
	}
	
	public static String getARandomType() {
		int num = WorldEngine.getRandomInteger(0, TYPE.size());
		return TYPE.get(num);
	}
	
	public static void main(String[] args){
		MyFileReader.readFromFile2("./LocConst.txt");
		MessagePrinter.print(getARandomPlace().getName());
		MessagePrinter.print(getARandomType());
	}
	
	public static String getARandomDungeonDesc(int num) {
		if(num == 0) return DUNP1.get(WorldEngine.getRandomInteger(0, DUNP1.size()));
		if(num == 1) return DUNP2.get(WorldEngine.getRandomInteger(0, DUNP2.size()));
		if(num == 2) return DUNP3.get(WorldEngine.getRandomInteger(0, DUNP3.size()));
		return "";
	}
	public static String getARandomForestDesc(int num) {
		if(num == 0) return FORP1.get(WorldEngine.getRandomInteger(0, FORP1.size()));
		if(num == 1) return FORP2.get(WorldEngine.getRandomInteger(0, FORP2.size()));
		if(num == 2) return FORP3.get(WorldEngine.getRandomInteger(0, FORP3.size()));
		return "";
	}
	public static String getARandomMountainDesc(int num) {
		// TODO Auto-generated method stub
		if(num == 0) return MNTP1.get(WorldEngine.getRandomInteger(0, MNTP1.size()));
		if(num == 1) return MNTP2.get(WorldEngine.getRandomInteger(0, MNTP2.size()));
		return "";
	}
	public static String getARandomVillageDesc(int num) {
		// TODO Auto-generated method stub
		if(num == 0) return VILP1.get(WorldEngine.getRandomInteger(0, VILP1.size()));
		if(num == 1) return VILP2.get(WorldEngine.getRandomInteger(0, VILP2.size()));
		return "";
	}
	public static String getARandomCastleDesc(int num) {
		if(num == 0) return CASP1.get(WorldEngine.getRandomInteger(0, CASP1.size()));
		if(num == 1) return CASP2.get(WorldEngine.getRandomInteger(0, CASP2.size()));
		if(num == 2) return CASP3.get(WorldEngine.getRandomInteger(0, CASP3.size()));
		return "";
	}
	public static String getARandomWaterDesc(int num) {
		// TODO Auto-generated method stub
		if(num == 0) return WTRP1.get(WorldEngine.getRandomInteger(0, WTRP1.size()));
		if(num == 1) return WTRP2.get(WorldEngine.getRandomInteger(0, WTRP2.size()));
		return "";
	}
	public static String getARandomRoadDesc(int num) {
		// TODO Auto-generated method stub
		if(num == 0) return RDP1.get(WorldEngine.getRandomInteger(0, RDP1.size()));
		return "";
	}
	
	public static String getARandomDreamDesc(int num) {
		if(num == 0) return DRMP1.get(WorldEngine.getRandomInteger(0, DRMP1.size()));
		if(num == 1) return DRMP2.get(WorldEngine.getRandomInteger(0, DRMP2.size()));
		if(num == 2) return DRMP3.get(WorldEngine.getRandomInteger(0, DRMP3.size()));
		return "";
	}
	
	public static String getAccDesc(int i) {
		ArrayList<String> list = null;
		if(i == 1)list = ACC1;
		if(i == 0)list = ACC2;

		StringBuilder sb = new StringBuilder();
		for(String line : list) {
			sb.append(line).append("\n");
		}
		return sb.toString();
	}
}
