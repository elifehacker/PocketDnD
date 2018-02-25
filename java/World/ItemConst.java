package World;

import java.util.ArrayList;

import Objects.Items.Consumable;
import Objects.Items.Equipment;
import Objects.Items.Item;

public class ItemConst {
    static public ArrayList<Item> consumables= MyFileReader.readItems("consumables.txt");
    static public ArrayList<Item> equipments= MyFileReader.readItems("equipments.txt");
    static public ArrayList<Item> souvenirs= MyFileReader.readItems("souvenirs.txt");

    static public Item getRandomItem(int rank, String type){
        int rnd = 0;
        Item eq = null;
        ArrayList<Item> list = null;
        if(type.equals("consumables")) list =consumables;
        if(type.equals("equipments")) list =equipments;
        if(list!=null &&list.size()!=0){
            do{
                rnd = WorldEngine.getRandomInteger(0, list.size());
                eq = list.get(rnd);
                rnd = eq.getId();
            } while (rnd%10000> rank*1000);
        }
        return eq;
    }

    public static void main(String[] args){
        for(Item it : consumables) {
            System.out.println();
        }
    }

}
