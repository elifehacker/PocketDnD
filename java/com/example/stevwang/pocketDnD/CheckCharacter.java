package com.example.stevwang.pocketDnD;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

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

        TextView tv = findViewById(R.id.textView3);
        ArrayList<String> charconst  = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        String line = null;
        /*
        try {
            InputStream is = getAssets().open("notes");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while((line = br.readLine()) != null) {
                //MessagePrinter.print(line);
                //charconst.add(line);
                sb.append(line);
            }
            // Always close files.
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(tv!=null)tv.setText(DialogConst.getAFightDialog());
        */

    }

}
