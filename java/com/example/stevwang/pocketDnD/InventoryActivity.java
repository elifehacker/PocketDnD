package com.example.stevwang.pocketDnD;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import Objects.Home;
import Objects.Items.Consumable;
import Objects.Items.Equipment;
import Objects.Items.Item;
import World.ItemConst;

public class InventoryActivity extends ActivityGroup {

    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inventory);
        mContentView = findViewById(R.id.fullscreen_content);

        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this.getLocalActivityManager());

        /*
        //The following code works but I decide to handle it in the same activity.
        TabHost.TabSpec spec; // Reusable TabSpec for each tab
        Intent intent; // Reusable Intent for each tab

        spec = tabHost.newTabSpec("Boots"); // Create a new TabSpec using tab host
        spec.setIndicator("Boots"); // set the “HOME” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, BootsTabActivity.class);
        spec.setContent(intent);
        tabHost.addTab(spec);
        */

        String intent_value = null;

        if(getIntent().getExtras()!=null){
            intent_value = getIntent().getExtras().getString("type");
        }

        tabHost.addTab(setTab(tabHost, "weapon", R.id.weapon_tab, R.id.weapon_table_layout));
        tabHost.addTab(setTab(tabHost, "armor", R.id.armor_tab, R.id.armor_table_layout));
        tabHost.addTab(setTab(tabHost, "boots", R.id.boots_tab, R.id.boots_table_layout));
        tabHost.addTab(setTab(tabHost, "item", R.id.item_tab, R.id.item_table_layout));

        if(intent_value != null ) {

            //int id_tab = getResources().getIdentifier(intent_value+"_tab", "id", getPackageName());
            //int id_layout = getResources().getIdentifier(intent_value+"_table_layout", "id", getPackageName());
            //tabHost.addTab(setTab(tabHost, intent_value, id_tab, id_layout));

            //lock on a tab

        }


        Button back = (Button) findViewById(R.id.inventory_back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InventoryActivity.this.onBackPressed();
            }
        });

    }


    private TabHost.TabSpec setTab(TabHost tabHost, String tagname, int tabid, int layoutid){
        TabHost.TabSpec newtab = tabHost.newTabSpec(tagname);
        newtab.setIndicator(tagname);
        newtab.setContent(tabid);

        TableLayout tl = (TableLayout) findViewById(layoutid);
        String tag = "e";
        ArrayList<Item> list = new ArrayList<Item>();
        if(tagname.equals("weapon")){
            for (Equipment eqp: Home.getHome().getEquipments()){
                if(eqp.isWeapon()){
                    list.add(eqp);
                }
            }
        }else if (tagname.equals("armor")){
            for (Equipment eqp: Home.getHome().getEquipments()){
                if(eqp.isArmor()){
                    list.add(eqp);
                }
            }
        }else if (tagname.equals("boots")){
            for (Equipment eqp: Home.getHome().getEquipments()){
                if(eqp.isBoots()){
                    list.add(eqp);
                }
            }
        }else if (tagname.equals("item")){
            list.addAll(Home.getHome().getConsumables());
            tag = "c";
        }


        if(!list.isEmpty()){

            //items at home are readily sorted.
            //Collections.sort(list);

            int previd = 0;
            int amount = 1;
            TableRow row;
            ImageButton ib;
            TextView tv;
            String text;
            TextView tv_amount = null;

            for(Item item: list){

                int id = getResources().getIdentifier(tag+item.getId(), "drawable", getPackageName());
                if(id == previd){
                    amount++;
                    tv_amount.setText("x"+amount);
                }else{

                    row = new TableRow(this);
                    ib = new ImageButton(this);
                    ib.setClickable(true);

                    previd = id;
                    amount = 1;

                    ib.setImageDrawable(getDrawable(id));
                    tv = new TextView(this);

                    text = item.getName()+"\n"+item.getDescription()+"\n";
                    if(item instanceof Equipment) text += ((Equipment) item).getEffect();
                    if(item instanceof Consumable) text += ((Consumable) item).getEffect();

                    tv.setText(text);

                    Log.d("debug", "began");

                    ib.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("debug", "clicked");

                        }
                    });

                    tv_amount = new TextView(this);
                    tv_amount.setText("x"+amount);

                    TableRow.LayoutParams param = new TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT,
                            0f);
                    tv.setLayoutParams(param);
                    TableRow.LayoutParams param2 = new TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT,
                            1f);
                    ib.setLayoutParams(param);
                    tv.setLayoutParams(param2);
                    TableRow.LayoutParams param3 = new TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT,
                            0f);
                    tv_amount.setLayoutParams(param3);
                    tv_amount.setSingleLine();

                    row.addView(ib);
                    row.addView(tv);
                    row.addView(tv_amount);

                    tl.addView(row);
                }

            }
        }

        return newtab;
    }


}
