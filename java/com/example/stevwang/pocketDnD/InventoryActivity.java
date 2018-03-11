package com.example.stevwang.pocketDnD;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;

public class InventoryActivity extends AppCompatActivity {

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
        tabHost.setup();
        TabHost.TabSpec newtab = tabHost.newTabSpec("Weapon");
        newtab.setIndicator("Weapon");
        newtab.setContent(R.id.included_tab);

        TabHost.TabSpec newtab2 = tabHost.newTabSpec("newtab2");
        newtab2.setIndicator("newtab2");
        newtab2.setContent(R.id.included_tab);

        tabHost.addTab(newtab);
        tabHost.addTab(newtab2);


    }



}
