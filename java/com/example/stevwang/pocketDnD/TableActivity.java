package com.example.stevwang.pocketDnD;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;

import Objects.Backpack;
import Objects.Home;
import Objects.Items.Consumable;
import Objects.Items.Equipment;
import Objects.Items.Item;

public class TableActivity extends AppCompatActivity implements Serializable {

    static Backpack backpack = new Backpack();;
    static int item_counter = 0;
    static Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myContext = this;
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_table);

        View mContentView = findViewById(R.id.fullscreen_content);
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        Log.d("debug","onCreate called");

        setImageButton(R.id.bp_im_weapon, "weapon", this.backpack.getWeapon());
        setImageButton(R.id.bp_im_armor, "armor", this.backpack.getArmor());
        setImageButton(R.id.bp_im_boots, "boots", this.backpack.getBoots());
        try {
            Item item = backpack.getItem(0);
            setImageButton(R.id.bp_im_item0, "item", item);
            item = backpack.getItem(1);
            setImageButton(R.id.bp_im_item1, "item", item);
            item = backpack.getItem(2);
            setImageButton(R.id.bp_im_item2, "item", item);
        }catch(Exception e){

        }

        Button back = (Button) findViewById(R.id.bp_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableActivity.this.onBackPressed();
            }
        });

    }

    public static Equipment getWeapon() {
        return backpack.getWeapon();
    }

    public static Equipment getArmor() {
        return backpack.getArmor();
    }

    public static Equipment getBoots() {
        return backpack.getBoots();
    }

    public static ArrayList<Consumable> getItems() {
        return backpack.getItems();
    }

    public void goAdventureClearTable(){
        backpack.clearBackpack();
    }

    private void setImageButton(int buttonid, final String typevalue, Item item) {

        ImageButton ib = (ImageButton) findViewById(buttonid);
        ib.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Start NewActivity.class
                Intent myIntent = new Intent(TableActivity.this,
                        InventoryActivity.class);
                myIntent.putExtra("type", typevalue);
                startActivityForResult(myIntent, 101);//get item
            }
        });

        if (item == null) return;

        String item_id = String.valueOf(item.getId());
        String tag = "e";
        if (typevalue.equals("item")){
            tag = "c";
        }
        if(ib!=null){
            int drawable_id = getResources().getIdentifier(tag+item_id, "drawable", getPackageName());
            ib.setImageDrawable(getDrawable(drawable_id));
        }

    }


    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        if(data == null) return;

        String item_id = data.getStringExtra("selected_id");
        String tagname = data.getStringExtra("selected_type");

        if(item_id == null || tagname == null) return;

        String tag = "e";
        if (tagname.equals("item")){
            tag = "c";
            tagname += item_counter;
            Log.d("debug", "tagname is "+tagname);

        }
        Log.d("debug", "looking for "+ tag+item_id);

        int drawable_id = getResources().getIdentifier(tag+item_id, "drawable", getPackageName());
        int image_id = getResources().getIdentifier("bp_im_"+tagname, "id", getPackageName());
        ImageButton ib = (ImageButton)findViewById(image_id);;
        ib.setImageDrawable(getDrawable(drawable_id));

        item_counter = backpack.putIntoBackPack(tagname, item_id);

    }


    public static void showPopup(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(myContext);

        builder.setTitle("Ops");
        builder.setMessage(message);

        builder.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                // Do nothing, but close the dialog
                dialog.dismiss();
            }
        });

        /*
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        */
        AlertDialog alert = builder.create();
        alert.show();

    }

}
