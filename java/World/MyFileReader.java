package World;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

import Objects.Items.Consumable;
import Objects.Items.Equipment;
import Objects.Items.Item;
import Objects.Items.Souvenir;
import Objects.Location;

public class MyFileReader {

    static Hashtable<String, Integer> readFromFile(String path) {
		
		Hashtable<String, Integer> charconst  = new Hashtable<String, Integer>();

        String line = null;
        
	    try {

            InputStream is = App.getContext().getAssets().open(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            
            while((line = br.readLine()) != null) {
                String[] str = line.split("=");
                charconst.put(str[0], Integer.valueOf(str[1]));
            }   

            // Always close files.
            br.close();
        }  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //MessagePrinter.print(charconst);
		return charconst;
	}

    /**
     * Used to read a plain description in one line.
     * @param path
     * @return
     */
	static ArrayList<String> readFromFile2(String path) {
		
		ArrayList<String> charconst  = new ArrayList<String>();
        String line = null;
        
	    try {
            // FileReader reads text files in the default encoding.
            InputStream is = App.getContext().getAssets().open(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            
            while((line = br.readLine()) != null) {
                //MessagePrinter.print(line);

                charconst.add(line);
            }   

            // Always close files.
            br.close();
        }  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //MessagePrinter.print(charconst);
		return charconst;
	}

    /**
     * it is used to read line such as code=name
     * 101=Lakeshore
     * @param path
     * @return
     */
	static ArrayList<Location> readFromFile3(String path) {

		ArrayList<Location> charconst  = new ArrayList<Location>();
        String line = null;

	    try {
            InputStream is = App.getContext().getAssets().open(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            while((line = br.readLine()) != null) {
                String[] str = line.split("=");
                Location l = new Location(Integer.valueOf(str[0]), str[1]);
                charconst.add(l);
            }

            // Always close files.
            br.close();
        }  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //MessagePrinter.print(charconst);
		return charconst;
	}

    static ArrayList<Item> readItems(String path) {

        ArrayList<Item> items  = new ArrayList<Item>();
        String line = null;

        try {
            InputStream is = App.getContext().getAssets().open(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            while((line = br.readLine()) != null) {
                String[] str = line.split("=");
                Item item = null;
                if(path.contains("consumables")){
                    item = new Consumable(Integer.valueOf(str[0]), str[1], str[2], str[3]);
                }else if(path.contains("equipments")){
                    item = new Equipment(Integer.valueOf(str[0]), str[1], str[2], str[3]);
                }else if(path.contains("sourvenirs")){
                    item = new Souvenir(Integer.valueOf(str[0]), str[1], str[2]);
                }
                items.add(item);
            }

            // Always close files.
            br.close();
        }  catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //MessagePrinter.print(charconst);
        return items;
    }

	public static void main(String[] args){
	    readFromFile2("FightDialog.txt");
	}
}
