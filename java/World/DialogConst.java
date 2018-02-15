package World;

import java.util.ArrayList;

public class DialogConst {
	
	static ArrayList<String> Fight= MyFileReader.readFromFile2("FightDialog.txt");

	public static String getAFightDialog(){
		return Fight.get(WorldEngine.getRandomInteger(0, Fight.size()));
	}
}
