package com.example.kru13.sokoview;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity {

    private ArrayAdapter mAdapter;

    public int lolec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        //ListView lv= (ListView) findViewById(R.id.listik);

        //setContentView(new SokoView(this));

      /*  View button = findViewById(R.id.button_reset);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_main);
            }
        });*/


        View button = findViewById(R.id.novahrabut);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Hra.class);
                MainActivity.this.startActivity(intent);
            }
        });

        View button2 = findViewById(R.id.exitbut);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        View button3 = findViewById(R.id.nastavenibut);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Settings.class);
                MainActivity.this.startActivity(intent);
            }
        });
        Player.lol=5;
    }



}
