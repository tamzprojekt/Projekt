package com.example.kru13.sokoview;

/**
 * Created by Gastovsky on 03.12.2017.
 */

public class Desk {
    //public int hracipole[]={0,1,1,10,2,2,11,3,3,11,4,4,12,5,5,13,6,6,14,7,7,15,8,8,16,9,9};

    public String nazvykaret[]={"start","brown1","brown2","chance","blue1","blue2","jail","pink1","pink2",
            "chance","orange1","orange2","parking","red1","red2","chance","yellow1","yellow2","goto","green1","green2","chance","bluet1","bluet2"};


    public int majetek[]={5,-1,-1,9,-1,-1,6,-1,-1,9,-1,-1,7,-1,-1,9,-1,-1,8,-1,-1,9,-1,-1}; //9 chance  8 gotojail  7 parking  6 jail 5 start  -1 nikdo nemovitos   0-3 player nemovitost


    public int tahlogika(int position,int player,Player pl){
        if(majetek[position]==-1){
            return -1;//pokud je nemovitost prazdna
        }
        if(majetek[position]!=player&&majetek[position]<=3&&majetek[position]>=0){
            return 1;//pokud vlastni jiny hrac
        }
        if(majetek[position]==9){
            return 9;//pokud je sance
        }
        if(majetek[position]==8){
            pl.position=6;
            pl.posx=32;
            pl.posy=615;
            return 8;//pokud je gotojail
        }
        if(majetek[position]==7){
            return 7;//pokud je parking
        }
        if(majetek[position]==6){
            return 6;//pokud je jail
        }
        if(majetek[position]==5){
            return 5;//pokud je start
        }

        return 0;//pokud vlastni hrac
    }

    public void buy(int position,int player,Player pl){
        majetek[position]=player;
        pl.majetekhrace.add(position);
    }



    public int tahlogika2(int position,int player){
        if(majetek[position]==-1){
            return -1;//pokud je nemovitost prazdna
        }
        if(majetek[position]!=player&&majetek[position]<=3&&majetek[position]>=0){
            return 1;//pokud vlastni jiny hrac
        }
        if(majetek[position]==9){
            return 9;//pokud je sance
        }
        if(majetek[position]==8){
            return 8;//pokud je gotojail
        }
        if(majetek[position]==7){
            return 7;//pokud je parking
        }
        if(majetek[position]==6){
            return 6;//pokud je jail
        }
        if(majetek[position]==5){
            return 5;//pokud je start
        }

        return 0;//pokud vlastni hrac
    }


}
