package World;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import Objects.Items.Consumable;
import Objects.Items.Equipment;
import Objects.Items.Item;
import Objects.Items.Souvenir;
import Objects.Location;

public class MyFileReader {

    /**
     * Read in file content such as
     * Human=194
     * @param path
     * @return
     */
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

    static public void writeToFileInternalStorage(String filename, String content){
        try{
            Log.d("debug","writing files");
            File file = new File(App.getContext().getFilesDir(), filename);
            if(!file.exists())
                file.mkdir();
            Log.d("debug","path is "+file.getPath());
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(content.getBytes());
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception","File write failed");
        }
    }

    /**
     * This is the same as readFromFile2 except it does not read from asset
     * @param filename
     * @return
     */
    static public ArrayList<String> readFileFromInternalStorage(String filename) {
        ArrayList<String> charconst  = new ArrayList<String>();
        File file = new File(App.getContext().getFilesDir(), filename);
        if(!file.exists())
            file.mkdir();
        try {
            FileInputStream is = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = null;
            while((line = br.readLine()) != null) {
                charconst.add(line);
            }
            // Always close files.
            br.close();

        }catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception","File read failed");
        }
        return charconst;
    }

    public static void main(String[] args){
	    readFromFile2("FightDialog.txt");
	}

}
