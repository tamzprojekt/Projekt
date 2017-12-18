package com.example.kru13.sokoview;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Statistic extends Activity {

    public static int pocettahu=0;
    public static int pocetkoupenychdomu=0;
    public static int pocetspustenychher=0;

    /*SharedPreferences mySharedPref;//ulozit data po vypnuti
    SharedPreferences.Editor mySharedEditor;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        MainActivity.mySharedPref=getSharedPreferences("myPref", Context.MODE_PRIVATE);
        pocettahu=MainActivity.mySharedPref.getInt("pocettahu",0);
        pocetkoupenychdomu=MainActivity.mySharedPref.getInt("domy",0);
        pocetspustenychher=MainActivity.mySharedPref.getInt("hry",0);
        TextView view = (TextView) findViewById(R.id.domytext);
        view.setText("Pocet tahů: " + pocettahu);
        view = (TextView) findViewById(R.id.tahytext);
        view.setText("Pocet koupených domů: " + pocetkoupenychdomu);
        view = (TextView) findViewById(R.id.pocethertext);
        view.setText("Pocet spuštěných her: " + pocetspustenychher);
    }

   public static void ulozenistatistik(){
        MainActivity.mySharedEditor=MainActivity.mySharedPref.edit();
       MainActivity.mySharedEditor.putInt("pocettahu",pocettahu);
       MainActivity.mySharedEditor.putInt("domy",pocetkoupenychdomu);
       MainActivity.mySharedEditor.putInt("hry",pocetspustenychher);
       MainActivity.mySharedEditor.apply();
    }

}
