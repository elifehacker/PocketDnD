package com.example.stevwang.pocketDnD;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;

import Objects.Home;
import Objects.Items.Consumable;
import Objects.Items.Equipment;
import Objects.Items.Item;

public class TableActivity extends AppCompatActivity implements Serializable {

    static Equipment weapon = null;
    static Equipment armor = null;
    static Equipment boots = null;
    static ArrayList<Consumable> items = null;
    static int item_counter = 1;
    static Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myContext = this;
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_table);

        View mContentView = findViewById(R.id.fullscreen_content);
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        ImageButton bp_weapon = (ImageButton) findViewById(R.id.bp_im_weapon);
        bp_weapon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(TableActivity.this,
                        InventoryActivity.class);
                myIntent.putExtra("type", "weapon");
                startActivityForResult(myIntent, 101);//get item
            }
        });

        Button back = (Button) findViewById(R.id.bp_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableActivity.this.onBackPressed();
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        String item_id = data.getStringExtra("selected_id");
        String tagname = data.getStringExtra("selected_type");
        String tag = "e";
        if (tagname.equals("item")){
            tag = "c";
            tagname += item_counter;
        }
        Log.d("debug", "looking for "+ tag+item_id);

        int drawable_id = getResources().getIdentifier(tag+item_id, "drawable", getPackageName());
        int image_id = getResources().getIdentifier("bp_im_"+tagname, "id", getPackageName());
        ImageButton ib = (ImageButton)findViewById(image_id);;
        ib.setImageDrawable(getDrawable(drawable_id));

        if(tagname.equals("weapon")){
            if(this.weapon!=null) Home.getHome().getEquipments().add(this.weapon);
            this.weapon = getEquipmentFromHome(item_id);

        }else if (tagname.equals("armor")){
            if(this.armor!=null) Home.getHome().getEquipments().add(this.armor);
            this.armor = getEquipmentFromHome(item_id);

        }else if (tagname.equals("boots")){
            if(this.boots!=null) Home.getHome().getEquipments().add(this.boots);
            this.boots = getEquipmentFromHome(item_id);

        }else if (tagname.equals("item")){
            if(this.items == null)
                this.items = new ArrayList<Consumable>();
            this.items.add(getItemsFromHome(item_id));
            if(this.items.size() == 4) {
                //remove and add the item back
                Consumable con = this.items.remove(0);
                Home.getHome().getConsumables().add(con);
            }
            item_counter++;
            item_counter %= 3;
        }


    }

    private Equipment getEquipmentFromHome(String id){
        int int_id = Integer.parseInt(id);
        for(Equipment eqp : Home.getHome().getEquipments()){
            if(eqp.getId() == int_id){
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
                Consumable ret = eqp;
                if(Home.getHome().getEquipments().remove(eqp))
                    return ret;
            }
        }
        return null;
    }

}
