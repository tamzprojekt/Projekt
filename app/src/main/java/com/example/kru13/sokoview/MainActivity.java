package com.example.kru13.sokoview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity {

    /*public static int pocettahu=0;
    public static int pocetkoupenychdomu=0;
    public static int pocetspustenychher=0;*/

    static SharedPreferences mySharedPref;//ulozit data po vypnuti
    static SharedPreferences.Editor mySharedEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        mySharedPref=getSharedPreferences("myPref", Context.MODE_PRIVATE);

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
        View button4 = findViewById(R.id.highscorebut);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Statistic.class);
                MainActivity.this.startActivity(intent);
            }
        });
        Player.lol=5;
    }



}
