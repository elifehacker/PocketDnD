package Objects;

import android.util.Log;

import com.example.stevwang.pocketDnD.TableActivity;

import java.util.ArrayList;

import Objects.Items.Consumable;
import Objects.Items.Equipment;
import Objects.Items.Item;

/**
 * Created by stevwang on 24/3/18.
 */

public class Backpack {
    private Equipment weapon = null;
    private Equipment armor = null;
    private Equipment boots = null;
    private ArrayList<Consumable> items = new ArrayList<Consumable>();

    static int item_counter = 0;

    public Equipment getWeapon() {
        return weapon;
    }

    public Equipment getArmor() {
        return armor;
    }

    public Equipment getBoots() {
        return boots;
    }

    public ArrayList<Consumable> getItems() {
        return items;
    }

    public void setWeapon(Equipment weapon) {
        this.weapon = weapon;
    }

    public void setArmor(Equipment armor) {
        this.armor = armor;
    }

    public void setBoots(Equipment boots) {
        this.boots = boots;
    }

    public void setItems(ArrayList<Consumable> items) {
        this.items = items;
    }

    public void clearBackpack(){
        this.weapon = null;
        this.armor = null;
        this.boots = null;
        this.items = new ArrayList<Consumable>();
    }

    public Item getItem(int index){
        if(this.items == null)
            return null;
        if(this.items.size() > index )
            return this.items.get(index);
        return null;
    }

    private Equipment getEquipmentFromHome(String id){
        int int_id = Integer.parseInt(id);
        Log.d("debug", "id is "+id);

        for(Equipment eqp : Home.getHome().getEquipments()){
            if(eqp.getId() == int_id){
                Log.d("debug", "found equipment at home ");

                Equipment ret = eqp;
                if(Home.getHome().getEquipments().remove(eqp))
                    return ret;
            }
        }
        return null;
    }

    private Consumable getItemsFromHome(String id){
        int int_id = Integer.parseInt(id);
        for(Consumable eqp : Home.getHome().getConsumables()){
            if(eqp.getId() == int_id){
                Log.d("debug", "found item at home ");
                Consumable ret = eqp;
                if(Home.getHome().getConsumables().remove(eqp))
                    return ret;
            }
        }
        return null;
    }

    public int putIntoBackPack(String tagname, String item_id) {

        int item_int_id = Integer.parseInt(item_id);

        if(tagname.equals("weapon")){
            if(this.weapon!=null && item_int_id != this.weapon.getId())
                Home.getHome().getEquipments().add(this.weapon);
            this.weapon = getEquipmentFromHome(item_id);

        }else if (tagname.equals("armor")){
            if(this.armor!=null && item_int_id != this.armor.getId()) Home.getHome().getEquipments().add(this.armor);
            this.armor = getEquipmentFromHome(item_id);

        }else if (tagname.equals("boots")){
            if(this.boots!=null && item_int_id != this.boots.getId()) Home.getHome().getEquipments().add(this.boots);
            this.boots = getEquipmentFromHome(item_id);

        }else if (tagname.contains("item")){
            if(this.items == null)
                this.items = new ArrayList<Consumable>();
            Consumable newConsumable = getItemsFromHome(item_id);
            if(newConsumable != null) {
                this.items.add(newConsumable);
            }else{
                TableActivity.showPopup("Not enough quantity. Maybe the backpack already had it.");
                return item_counter;
            }

            if(this.items.size() == 4) {
                //remove and add the item back
                Consumable con = this.items.remove(0);
                Home.getHome().getConsumables().add(con);
            }
            item_counter++;
            item_counter %= 3;
            Log.d("debug", "item_counter is "+item_counter);

        }
        return item_counter;
    }
}
