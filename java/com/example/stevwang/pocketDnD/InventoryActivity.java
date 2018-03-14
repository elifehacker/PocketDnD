package com.example.stevwang.pocketDnD;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import Objects.Home;
import Objects.Items.Equipment;

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

        tabHost.addTab(setTab(tabHost, "Weapon", R.id.weapon_tab, R.id.weapon_table_layout));
        tabHost.addTab(setTab(tabHost, "Armor", R.id.armor_tab, R.id.armor_table_layout));
        tabHost.addTab(setTab(tabHost, "Boots", R.id.boots_tab, R.id.boots_table_layout));

    }


    private TabHost.TabSpec setTab(TabHost tabHost, String tagname, int tabid, int layoutid){
        TabHost.TabSpec newtab = tabHost.newTabSpec(tagname);
        newtab.setIndicator(tagname);
        newtab.setContent(tabid);

        TableLayout tl = (TableLayout) findViewById(layoutid);
        ArrayList<Equipment> list = null;
        if(tagname.equals("Weapon")){
            list = Home.getHome().getEquipments();
        }
        if(list!= null){
            for(Equipment eqp: list){

                TableRow row = new TableRow(this);
                ImageButton ib = new ImageButton(this);
                ib.setClickable(true);
                int id = getResources().getIdentifier("e"+eqp.getId(), "drawable", getPackageName());
                ib.setImageDrawable(getDrawable(id));
                TextView tv = new TextView(this);
                tv.setText(eqp.getName()+"\n"+eqp.getDescription()+"\n"+eqp.getEffect());


                TableRow.LayoutParams param = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        0.2f);
                tv.setLayoutParams(param);
                TableRow.LayoutParams param2 = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        0.8f);
                ib.setLayoutParams(param);
                tv.setLayoutParams(param2);

                Log.d("debug", "began");

                ib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("debug", "clicked");

                    }
                });
                row.addView(ib);
                row.addView(tv);
                tl.addView(row);
            }
        }


        return newtab;
    }


}
