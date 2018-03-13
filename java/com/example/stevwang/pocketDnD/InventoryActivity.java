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

        TabHost.TabSpec newtab = tabHost.newTabSpec("Weapon");
        newtab.setIndicator("Weapon");
        newtab.setContent(R.id.weapon_tab);

        TableLayout tl = (TableLayout) findViewById(R.id.weapon_table_layout);
        TableRow row = new TableRow(this);
        ImageButton ib = new ImageButton(this);
        ib.setClickable(true);
        ib.setImageDrawable(getDrawable(R.drawable.e41000));
        TextView tv = new TextView(this);
        tv.setText("abc");


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


        ///
        TabHost.TabSpec newtab2 = tabHost.newTabSpec("Armor");
        newtab2.setIndicator("Armor");
        newtab2.setContent(R.id.armor_tab);

        TableLayout tl2 = (TableLayout) findViewById(R.id.armor_table_layout);
        TableRow row2 = new TableRow(this);
        ImageButton ib2 = new ImageButton(this);
        ib2.setClickable(true);
        ib2.setImageDrawable(getDrawable(R.drawable.e41000));
        TextView tv2 = new TextView(this);
        tv2.setText("bcd");


        TableRow.LayoutParams param3 = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT,
                0.2f);
        tv2.setLayoutParams(param);
        TableRow.LayoutParams param4 = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT,
                0.8f);
        ib2.setLayoutParams(param3);
        tv2.setLayoutParams(param4);

        Log.d("debug", "began");

        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("debug", "clicked");

            }
        });
        row2.addView(ib2);
        row2.addView(tv2);
        tl2.addView(row2);
        ///


        TabHost.TabSpec spec; // Reusable TabSpec for each tab
        Intent intent; // Reusable Intent for each tab

        spec = tabHost.newTabSpec("Boots"); // Create a new TabSpec using tab host
        spec.setIndicator("Boots"); // set the “HOME” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, BootsTabActivity.class);
        spec.setContent(intent);
        tabHost.addTab(spec);



        tabHost.addTab(newtab);
        tabHost.addTab(newtab2);


    }


    void setTab(){

    }


}
