package com.example.stevwang.pocketDnD;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import Objects.Home;
import World.MessagePrinter;
import World.WorldEngine;

import static World.WorldEngine.getSeconds;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GardenActivity extends AppCompatActivity {

    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_garden);
        mContentView = findViewById(R.id.fullscreen_content);
        int uioptions = View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        mContentView.setSystemUiVisibility(uioptions);

        ImageButton toggleHome = findViewById(R.id.toHome);
        toggleHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(GardenActivity.this,
                        HomeActivity.class);
                startActivity(myIntent);
            }
        });

        ImageButton inventory = findViewById(R.id.inventory);
        inventory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(GardenActivity.this,
                        InventoryActivity.class);
                startActivity(myIntent);
            }
        });

        final long sec = WorldEngine.getSeconds();
        Log.e("Debug","time difference is "+sec);

        Thread thread = new Thread(){

            public void run(){
                MessagePrinter.print("A young hero, hoping to achieve great wonders.");
                MessagePrinter.print("Would you witness and guide him through his journey?");
                Home.getHome().printAll();
                boolean waitedForPacking = false;

                while(WorldEngine.catchingUpTime()) {
                    int num = World.WorldEngine.getRandomInteger(0, 6);
                    if(num<4) Home.getHome().train(num);
                    if(num >= 4) {

                        if(!waitedForPacking && TableActivity.isBackpackEmpty()){
                            MessagePrinter.print("Backpack is empty. Wait for a bit.");
                            waitedForPacking = true;
                            WorldEngine.catchingUpTime(WorldEngine.backpack_repacking);
                            continue;
                        }

                        Home.getHome().goAdventure();
                        waitedForPacking = false;
                    }
                }

            }
        };
        thread.start();

    }

}
