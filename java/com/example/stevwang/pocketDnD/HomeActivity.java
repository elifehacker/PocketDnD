package com.example.stevwang.pocketDnD;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import Objects.Home;
import World.MessagePrinter;
import World.WorldEngine;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class HomeActivity extends AppCompatActivity {

    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        mContentView = findViewById(R.id.fullscreen_content);
        int uioptions = View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        mContentView.setSystemUiVisibility(uioptions);

        ImageButton toggleHome = findViewById(R.id.ToGarden);
        toggleHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(HomeActivity.this,
                        GardenActivity.class);
                startActivity(myIntent);
            }
        });

        ImageButton showTable = findViewById(R.id.showTable);
        showTable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(HomeActivity.this,
                        TableActivity.class);
                startActivity(myIntent);
            }
        });


        ImageButton checkCh = findViewById(R.id.checkCharacter);
        checkCh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(HomeActivity.this,
                        CheckCharacter.class);
                startActivity(myIntent);
            }
        });


        ImageButton inventory = findViewById(R.id.inventory);
        inventory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(HomeActivity.this,
                        InventoryActivity.class);
                startActivity(myIntent);
            }
        });

        ImageView character = (ImageView)findViewById(R.id.character);
        if (character != null) {
            character.setVisibility(View.VISIBLE);
            AnimationDrawable frameAnimation = (AnimationDrawable)character.getDrawable();
            frameAnimation.setVisible(true, true);
            frameAnimation.start();
        }
        //character.setX(200);
        //character.setY(600);

    }

}
