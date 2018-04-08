package World;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import Creatures.Creature;


public class WorldEngine {

	public static int backpack_repacking = 600;
	public static int pause = 10;
	public static int training = 10;
	public static Date previousTime = new Date();;
	public static Date currentTime = new Date();;
	private static long duration = getSeconds();
	private static long counter = 0;

	public static long getSeconds() {
		ArrayList<String> fileContent = MyFileReader.readFileFromInternalStorage("datetime.txt");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

		if(fileContent!= null && !fileContent.isEmpty()){
			try {
				previousTime = sdf.parse(fileContent.get(0));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		MyFileReader.writeToFileInternalStorage("datetime.txt", sdf.format(currentTime));
		Log.d("debug", "compare times: "+previousTime.getTime()+" "+currentTime.getTime());
		return (currentTime.getTime()-previousTime.getTime())/1000;
	}

	public static void catchingUpTime(long sec){
		Log.d("debug", "increase time counter: "+counter+" by "+sec);
		counter += sec;
	}

	public static boolean catchingUpTime(){
		Log.d("debug", "time counter and durations are: "+counter+" and "+duration);
		if(counter < duration)
			return true;

		return false;
	}

	public static int getRandomInteger(int from, int var) {
        Random rnd = new Random();
        return (from+(rnd.nextInt(var)));
    }
	
	private static int calculateDamage(Creature c1, Creature c2) {
		int atk = c1.getStrength()>c1.getIntelligence()?c1.getStrength():c1.getIntelligence();
		double mul = getMultiplier(c1, c2);
		atk *= mul;
		//atk equal 90%-110% of strength
		atk = getRandomInteger((int)(atk*0.9),(int)(atk*0.2));
		//10% of critical attack, dealing 150%
		//if the attacker has higher intelligence, chance doubles
		if(getRandomInteger(0,10)<(c1.getIntelligence()>=c2.getIntelligence()?2:1)) {
			atk*= CharConst.criticalmultiplier;
			MessagePrinter.print("Critical Attack!");			
		}
		//MessagePrinter.print("Attack is "+atk+" Defense is "+c2.getStrength()/2);
		return (atk-c2.getStrength()/2) > 0 ? atk-c2.getStrength()/2:1;
	}
	
	private static double getMultiplier(Creature c1, Creature c2) {
		// TODO Auto-generated method stub
		
		for(String c2w: c2.getWeakness())
			if(c1.getTypes().contains(c2w)) {
				//MessagePrinter.print("Counter Bonus! ");
				return CharConst.bonusmultiplier;
			}
		return 1;
	}
	
	public static boolean fight(Creature c1, Creature c2) {

		MessagePrinter.print("A close encounter! Battle started between lv"+c1.getLevel()+" "+c1.getTypes().get(0)+" "+c1.getName()
		+" and lv"+c2.getLevel()+" "+c2.getTypes().get(0)+" "+c2.getName());
		
		int dmg = 0;
		int op_dmg = 0;
		int spd = c1.getAgility();
		int op_spd = c2.getAgility();
		int newdmg = 0;
		while(dmg< c1.getCurrentHealth() && op_dmg < c2.getHealth()) {
			if(spd>=op_spd) {
				newdmg = calculateDamage(c1, c2);
				op_dmg += newdmg;
				//MessagePrinter.print("Dealing "+newdmg+" to "+c2.getName()+" Health "+(c2.getHealth()-op_dmg));
				MessagePrinter.print("Dealing damage:"+newdmg+" to "+c2.getName()+" Health "+(c2.getHealth()-op_dmg));
				op_spd += c2.getAgility();

			}else {
				newdmg = calculateDamage(c2, c1);
				if(getRandomInteger(0,10) < 3) fightDialog();
				c1.incDamage(newdmg);
				MessagePrinter.print("Taking damage: "+newdmg+" Health "+(c1.getCurrentHealth()));
				spd+= c1.getAgility();
			}
			
			WorldEngine.catchingUpTime(pause);

		}
		
		if(c1.getCurrentHealth()>0) {
			MessagePrinter.print("Victory!");
			return true;
		}			
		return false;
	}
	
	private static void fightDialog() {
		// TODO Auto-generated method stub
		MessagePrinter.print(DialogConst.getAFightDialog());
	}

	public static void main(String[] args){

		Creature one = new Creature();
		one.printAll();
		Creature two = new Creature();
		two.printAll();
		
		if(fight(one, two)) {
			MessagePrinter.print("Player one won");
		}else {
			MessagePrinter.print("Player two won");			
		}
		
		//MessagePrinter.print(Constants.type.size());
	}
	
}
