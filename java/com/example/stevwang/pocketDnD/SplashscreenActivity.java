package com.example.stevwang.pocketDnD;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import Objects.Home;
import World.MessagePrinter;
import World.WorldEngine;

public class SplashscreenActivity extends AppCompatActivity {

    private View mContentView;
    private int SplashTimeout = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        mContentView = findViewById(R.id.fullscreen_content);
        int uioptions = View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        mContentView.setSystemUiVisibility(uioptions);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                MessagePrinter.print("A young hero, hoping to achieve great wonders.");
                MessagePrinter.print("Would you witness and guide him through his journey?");

                final long sec = WorldEngine.getTimePassedSinceLastTime();
                Log.e("Debug","time difference is "+sec);

                if(sec< WorldEngine.processlimit) //avoid abusing quit and start too quickly.
                    return;

                WorldEngine.storeCurrentTime();
                WorldEngine.loadData(SplashscreenActivity.this, WorldEngine.saveHome);
                if(!Home.getHome().getAtHome()){
                    //not at home
                    Home.getHome().resumeAdventure();
                }else{
                    Home.getHome().printAll();
                }

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
                WorldEngine.saveData(SplashscreenActivity.this, WorldEngine.saveHome);
            }
        });
        thread.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent (SplashscreenActivity.this, GardenActivity.class);
                startActivity(intent);
                finish();
            }
        }, SplashTimeout);

    }
}
