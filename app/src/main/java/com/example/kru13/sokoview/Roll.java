package com.example.kru13.sokoview;

import android.widget.ImageView;

import java.util.Random;

/**
 * Created by Gastovsky on 02.12.2017.
 */

public class Roll {

    private ImageView imageView;

    public int random1(){
        Random rand = new Random();
        int  n = rand.nextInt(6) + 1;

        return n;

    }
    public int random2(){
        Random rand = new Random();
        int  n = rand.nextInt(6) + 1;
        return n;

    }



}
