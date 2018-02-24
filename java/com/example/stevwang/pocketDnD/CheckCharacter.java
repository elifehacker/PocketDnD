package com.example.stevwang.pocketDnD;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import World.MessagePrinter;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class CheckCharacter extends AppCompatActivity {

    private View mContentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_character);
        mContentView = findViewById(R.id.fullscreen_content);
        int uioptions = View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        mContentView.setSystemUiVisibility(uioptions);

        Button backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(CheckCharacter.this,
                        HomeActivity.class);
                startActivity(myIntent);
            }
        });

        ImageView progress = (ImageView)findViewById(R.id.character);
        if (progress != null) {
            progress.setVisibility(View.VISIBLE);
            AnimationDrawable frameAnimation = (AnimationDrawable)progress.getDrawable();
            frameAnimation.setVisible(true, true);
            frameAnimation.start();
        }

        Thread thread = new Thread(){
            public void run(){
                TextView herolog = (TextView) findViewById(R.id.herolog);
                StringBuffer sb = new StringBuffer();
                for(String str : MessagePrinter.messages){
                    sb.append(str);
                    sb.append("\n");

                }
                herolog.setText(sb.toString());
            }
        };
        thread.start();

    }

}
