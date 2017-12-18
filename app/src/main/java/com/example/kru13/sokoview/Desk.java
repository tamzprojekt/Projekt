package com.example.kru13.sokoview;

import android.util.Log;

/**
 * Created by Gastovsky on 03.12.2017.
 */

public class Desk {

    public String nazvykaret[]={"start","brown1","brown2","chance","blue1","blue2","jail","pink1","pink2",
            "chance","orange1","orange2","parking","red1","red2","chance","yellow1","yellow2","goto","green1","green2","chance","bluet1","bluet2"};

    public int hodnoty[]={0,5000,5000,0,5000,5000,0,10000,10000,0,10000,10000,0,15000,15000,0,15000,15000,0,20000,20000,0,20000,20000};

    public int domy[]={-1,0,0,-1,0,0,-1,0,0,-1,0,0,-1,0,0,-1,0,0,-1,0,0,-1,0,0};


    public int majetek[]={5,-1,-1,9,-1,-1,6,-1,-1,9,-1,-1,7,-1,-1,9,-1,-1,8,-1,-1,9,-1,-1}; //9 chance  8 gotojail  7 parking  6 jail 5 start  -1 nikdo nemovitos   0-3 player nemovitost

    public String domyvypis(int position){
        if(domy[position]!=-1){
            return String.valueOf(domy[position]);
        }
        return "-";
    }

    public String majetekvypis(int position){
        if(majetek[position]==-1){
            return "Prázdné";
        }
        else if(majetek[position]==9){
            return "Chance";
        }
        else if(majetek[position]==8){
            return "GOTO";
        }
        else if(majetek[position]==7){
            return "Parking";
        }
        else if(majetek[position]==6){
            return "Vězení";
        }
        else if(majetek[position]==5){
            return "Start";
        }
        else if(majetek[position]==0){
            return "Hráč 1";
        }
        else if(majetek[position]==1){
            return "Hráč 2";
        }
        else if(majetek[position]==2){
            return "Hráč 3";
        }
        else if(majetek[position]==3){
            return "Hráč 4";
        }
        return "None";
    }

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
            //pl.position=6;
            //pl.posx=32;
            //pl.posy=615;
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

    public void startmoney(Player pl){
        pl.money+=10000;
    }

    public int platba(int position, int player, Player pl, Player[] pls){
        if(majetek[position]!=player&&majetek[position]>=0&&majetek[position]<=3){
            if(domy[position]==0) {
                pl.money -= 2000;//hrac kteremu se odebiraji penize
                pls[majetek[position]].money += 2000;//prijemce penez
                return 2000;
            }
            else if(domy[position]==1) {
                pl.money -= 4000;//hrac kteremu se odebiraji penize
                pls[majetek[position]].money += 4000;//prijemce penez
                return 4000;
            }
            else if(domy[position]==2) {
                pl.money -= 6000;//hrac kteremu se odebiraji penize
                pls[majetek[position]].money += 6000;//prijemce penez
                return 6000;
            }
            else if(domy[position]==3) {
                pl.money -= 8000;//hrac kteremu se odebiraji penize
                pls[majetek[position]].money += 8000;//prijemce penez
                return 8000;
            }
            return 0;
        }
        return 1;
    }

    public int buy(int position,int player,Player pl){
        Log.d("XXX ",position+" POS"+player+" player");
        if(majetek[position]==-1){//pokud je parcelu mozno koupit
            majetek[position]=player;
            //pl.majetekhrace.add(position);
            if(pl.money>=hodnoty[position]){
                pl.money=pl.money-hodnoty[position];
            }
            else{
                return -2;//nedostatek penez
            }
            //pl.money=pl.money-hodnoty[position];
            return 0;//koupeno
        }
        else if(majetek[position]==player){//prodani
            majetek[position]=-1;
            domy[position]=-0;
            //pl.majetekhrace.remove(position);
            pl.money=pl.money+hodnoty[position];
            return -1;
        }
            return 1; //error
    }

    public int buyhouse(int position,int player,Player pl){
        if((domy[position]==0||domy[position]==1||domy[position]==2||domy[position]==3)&&majetek[position]==player){
            domy[position]+=1;
            if(pl.money>=5000){
                pl.money=pl.money-5000;
                Statistic.pocetkoupenychdomu+=1;
                Statistic.ulozenistatistik();
            }
            else{
                return -2;//nedostatek penez
            }
            return 0;//koupeno
        }
        return 1;//nelze koupit
    }

}
