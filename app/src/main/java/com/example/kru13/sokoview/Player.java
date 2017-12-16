package com.example.kru13.sokoview;

import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gastovsky on 28.11.2017.
 */

public class Player {
    public static int lol=0;

    public List<Integer> majetekhrace = new ArrayList<Integer>();

    public int position=0;
    public int posx=650;
    public int posy=615;

    public int animposx=650;
    public int animposy=615;

    public int money=200000;

    public int hodnota(){
        return lol;
    }
    public void position(int i){
        position+=i;
    }
    public void posx(int i){
        posx+=i;
    }
    public void posy(int i){
        posy+=i;
    }

}
