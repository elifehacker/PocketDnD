package com.example.stevwang.pocketDnD;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class BootsTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boots_tab);

        TableLayout tl = (TableLayout) findViewById(R.id.boots_table_layout);
        TableRow row = new TableRow(this);
        ImageButton ib = new ImageButton(this);
        ib.setClickable(true);
        ib.setImageDrawable(getDrawable(R.drawable.e41000));
        TextView tv = new TextView(this);
        tv.setText("xyz");


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
