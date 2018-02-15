package World;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import Objects.Location;

public class MyFileReader {

	static Hashtable<String, Integer> readFromFile(String path) {
		
		Hashtable<String, Integer> charconst  = new Hashtable<String, Integer>();
	    //File charContst = new File("./CharConst.txt");
	    File charContst = new File(path);

        String line = null;
        
	    try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(charContst);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {
                String[] str = line.split("=");
                charconst.put(str[0], Integer.valueOf(str[1]));
            }   

            // Always close files.
            bufferedReader.close();         
        }  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //MessagePrinter.print(charconst);
		return charconst;
	}
	
	static ArrayList<String> readFromFile2(String path) {
		
		ArrayList<String> charconst  = new ArrayList<String>();
	    //File charContst = new File("./CharConst.txt");
	    File charContst = new File(path);

        String line = null;
        
	    try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(charContst);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {
                //MessagePrinter.print(line);

                charconst.add(line);
            }   

            // Always close files.
            bufferedReader.close();         
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
            FileReader fileReader = new FileReader(charContst);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {
                String[] str = line.split("=");
                Location l = new Location(Integer.valueOf(str[0]), str[1]);
                charconst.add(l);
            }   

            // Always close files.
            bufferedReader.close();         
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
