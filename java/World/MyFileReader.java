package World;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

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
	
	
	static ArrayList<Location> readFromFile3(String path) {
		
		ArrayList<Location> charconst  = new ArrayList<Location>();
	    File charContst = new File(path);
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
	
	public static void main(String[] args){
	    readFromFile2("./FightDialog.txt");
	}
}
