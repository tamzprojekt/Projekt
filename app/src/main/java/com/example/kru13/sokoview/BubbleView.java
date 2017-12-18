package com.example.kru13.sokoview;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Gastovsky on 01.12.2017.
 */

public class BubbleView extends View {

    private int animacetest=4;
    private boolean animace=false;

    public MediaPlayer mpbview;//zvuk hod kostkou
    public boolean nextplayer=true;//predani tahu
    public boolean nextroll=true;

    public TextView texthrac;
    public TextView textpenize;
    public TextView texthodnota;
    public TextView textvlastnik;
    public TextView textdomy;

    Bitmap b3;
    Bitmap b4;
    Bitmap b5;
    Bitmap[] bmp;
    int kostka1bmp=0;
    int kostka2bmp=0;
    int kartabmp=29;

    Player[] pl=new Player[5];
    Desk desk=new Desk();

    int activeplayer=0;
    int activeplayeranim=0;

    float x1=0;
    float y1=0;

    public BubbleView(Context context) {
        super(context);
        init(context);
        playerinit();
        Player.lol=4;
    }
    public BubbleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        playerinit();
        Player.lol=4;
    }

    public BubbleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        playerinit();
        Player.lol=4;
    }

    public void nextplayer(){
        if(nextroll==true) {
            if (activeplayer < Settings.pocethracu - 1) {
                activeplayer++;
            } else activeplayer = 0;
            nextplayer = true;
            nextroll = false;
            zmenatextu();
        }
        else {
            Toast.makeText(getContext(), "Roll!", Toast.LENGTH_SHORT).show();
        }
    }

    public void playerinit(){
        for(int i=0;i<Settings.pocethracu;i++){
            pl[i]=new Player();
        }
    }

    public void buy(){
        //desk.tahlogika(pl[activeplayer].position,activeplayer,pl[activeplayer]);
        if(desk.buy(pl[activeplayer].position,activeplayer,pl[activeplayer])==0){
            Toast.makeText(getContext(), "Koupena parcela",Toast.LENGTH_SHORT).show();
        }
        else if(desk.buy(pl[activeplayer].position,activeplayer,pl[activeplayer])==-1){
            Toast.makeText(getContext(), "Prodana parcela",Toast.LENGTH_SHORT).show();
        }
        else if(desk.buy(pl[activeplayer].position,activeplayer,pl[activeplayer])==-2){
            Toast.makeText(getContext(), "Nedostatek penez",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getContext(), "Nelze koupit",Toast.LENGTH_SHORT).show();
        }
        zmenatextu();
    }

    public void buyhouse(){
        if(desk.buyhouse(pl[activeplayer].position,activeplayer,pl[activeplayer])==0){
            Toast.makeText(getContext(), "Koupen dum",Toast.LENGTH_SHORT).show();
        }
        else if(desk.buyhouse(pl[activeplayer].position,activeplayer,pl[activeplayer])==1){
            Toast.makeText(getContext(), "Nelze koupit",Toast.LENGTH_SHORT).show();
        }
        else if(desk.buyhouse(pl[activeplayer].position,activeplayer,pl[activeplayer])==-2){
            Toast.makeText(getContext(), "Nedostatek penez",Toast.LENGTH_SHORT).show();
        }
        zmenatextu();
    }

    private void zmenatextu(){
        texthrac.setText("Tah: Hráč "+(activeplayer+1));
        textpenize.setText("Peníze: "+pl[activeplayer].money);
        texthodnota.setText("Cena: "+desk.hodnoty[pl[activeplayer].position]);
        textvlastnik.setText("Vlastník: "+desk.majetekvypis(pl[activeplayer].position));
        textdomy.setText("Počet domů: "+desk.domyvypis(pl[activeplayer].position));
    }


    private void animation(final int round, final int posxleft, final int posyup,final int posxright,final int posydown,final int predchozipozice){
        //Toast.makeText(getContext(), "pred "+predchozipozice+ "po "+pl[activeplayeranim].position,Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            public void run() {

                int y=0;
                if(predchozipozice<pl[activeplayeranim].position) {//animace vlevo nahoru doprava
                    while (y > posxleft) {
                        pl[activeplayeranim].posx += -1;
                        postInvalidate();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        y--;
                    }
                    y = 0;
                    while (y > posyup) {
                        Log.d("bb", "bbb");
                        pl[activeplayeranim].posy += -1;
                        postInvalidate();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        y--;
                    }
                    y = 0;
                    while (y < posxright) {
                        pl[activeplayeranim].posx += 1;
                        postInvalidate();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        y++;
                    }
                    y = 0;
                    while (y < posydown) {
                        pl[activeplayeranim].posy += 1;
                        postInvalidate();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        y++;
                    }
                }

                else{//animace vpravo-dolu-doleva
                    while (y < posxright) {
                        pl[activeplayeranim].posx += 1;
                        postInvalidate();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        y++;
                    }
                    y=0;
                    while (y < posydown) {
                        pl[activeplayeranim].posy += 1;
                        postInvalidate();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        y++;
                    }
                    y=0;
                    while (y > posxleft) {
                        pl[activeplayeranim].posx += -1;
                        postInvalidate();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        y--;
                    }
                    y=0;
                    while (y > posyup) {
                        //Log.d("bb", "bbb");
                        pl[activeplayeranim].posy += -1;
                        postInvalidate();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        y--;
                    }
                }
                if(pl[activeplayeranim].position==18){//presun do vezeni
                    //pl[activeplayer-1].position=6;
                    pl[activeplayeranim].position=6;
                    pl[activeplayeranim].posx=32;
                    pl[activeplayeranim].posy=615;
                    pl[activeplayeranim].money-=5000;
                    //Toast.makeText(getContext(), "Zaplaceno 5000 za vezeni",Toast.LENGTH_SHORT).show();
                    //zmenatextu();//nejde
                }

                if(activeplayeranim<Settings.pocethracu-1){
                    activeplayeranim++;
                }
                else activeplayeranim=0;
                animace=false;
            }
        }).start();
    }


    private void kostka1(int cislo){
        if(cislo==1){
            kostka1bmp=0;
        }
        else if(cislo==2){
            kostka1bmp=1;
        }
        else if(cislo==3){
            kostka1bmp=2;
        }
        else if(cislo==4){
            kostka1bmp=3;
        }
        else if(cislo==5){
            kostka1bmp=4;
        }
        else if(cislo==6){
            kostka1bmp=5;
        }
        invalidate();
    }

    private void kostka2(int cislo){
        if(cislo==1){
            kostka2bmp=0;
        }
        else if(cislo==2){
            kostka2bmp=1;
        }
        else if(cislo==3){
            kostka2bmp=2;
        }
        else if(cislo==4){
            kostka2bmp=3;
        }
        else if(cislo==5){
            kostka2bmp=4;
        }
        else if(cislo==6){
            kostka2bmp=5;
        }
        invalidate();
    }

    private void zobrazkartu(int pomocna){
        if(desk.nazvykaret[pomocna]=="brown1"){
            kartabmp=10;
        }
        else if(desk.nazvykaret[pomocna]=="blue1"){
            kartabmp=11;
        }
        else if(desk.nazvykaret[pomocna]=="pink1"){
            kartabmp=12;
        }
        else if(desk.nazvykaret[pomocna]=="orange1"){
            kartabmp=13;
        }
        else if(desk.nazvykaret[pomocna]=="red1"){
            kartabmp=14;
        }
        else if(desk.nazvykaret[pomocna]=="yellow1"){
            kartabmp=15;
        }
        else if(desk.nazvykaret[pomocna]=="green1"){
            kartabmp=16;
        }
        else if(desk.nazvykaret[pomocna]=="bluet1"){
            kartabmp=17;
        }
        else if(desk.nazvykaret[pomocna]=="brown2"){
            kartabmp=18;
        }
        else if(desk.nazvykaret[pomocna]=="blue2"){
            kartabmp=19;
        }
        else if(desk.nazvykaret[pomocna]=="pink2"){
            kartabmp=20;
        }
        else if(desk.nazvykaret[pomocna]=="orange2"){
            kartabmp=21;
        }
        else if(desk.nazvykaret[pomocna]=="red2"){
            kartabmp=22;
        }
        else if(desk.nazvykaret[pomocna]=="yellow2"){
            kartabmp=23;
        }
        else if(desk.nazvykaret[pomocna]=="green2"){
            kartabmp=24;
        }
        else if(desk.nazvykaret[pomocna]=="bluet2"){
            kartabmp=25;
        }
        else if(desk.nazvykaret[pomocna]=="chance"){
            kartabmp=26;
        }
        else if(desk.nazvykaret[pomocna]=="jail"){
            kartabmp=27;
        }
        else if(desk.nazvykaret[pomocna]=="parking"){
            kartabmp=28;
        }
        else if(desk.nazvykaret[pomocna]=="start"){
            kartabmp=29;
        }
    }

    private void platba(){
        if(desk.platba(pl[activeplayer].position,activeplayer,pl[activeplayer],pl)==2000){
            Toast.makeText(getContext(), "Zaplaceno 2000",Toast.LENGTH_SHORT).show();
        }
        else if(desk.platba(pl[activeplayer].position,activeplayer,pl[activeplayer],pl)==4000){
            Toast.makeText(getContext(), "Zaplaceno 4000",Toast.LENGTH_SHORT).show();
        }
        else if(desk.platba(pl[activeplayer].position,activeplayer,pl[activeplayer],pl)==6000){
            Toast.makeText(getContext(), "Zaplaceno 6000",Toast.LENGTH_SHORT).show();
        }
        else if(desk.platba(pl[activeplayer].position,activeplayer,pl[activeplayer],pl)==8000){
            Toast.makeText(getContext(), "Zaplaceno 8000",Toast.LENGTH_SHORT).show();
        }
    }

    public void pohyb() {
        /*final MediaPlayer mp = MediaPlayer.create(this, R.raw.roll);
        mp.start();*/
        if(Settings.soundenabled) mpbview.start();// prehrani zvuku hod kostkou
        nextplayer=false;//nemuze hrat dalsi hrac
        nextroll=true;

        animace=true;
        int posxleft=0;
        int posyup=0;
        int posxright=0;
        int posydown=0;
        int predchozipozice= pl[activeplayer].position;
        //animation();
        int random=random();
        kostka1(random);
        int random2=random();
        kostka2(random2);
        for(int i=0;i<random+random2;i++){
            pl[activeplayer].position+=1;
            if( pl[activeplayer].position<=6){
                posxleft-=103;
            }

            if( pl[activeplayer].position>=7&& pl[activeplayer].position<13){
                posyup-=98;
            }
            if( pl[activeplayer].position>=13&& pl[activeplayer].position<19){
                posxright+=103;
            }
            if( pl[activeplayer].position>=19){
                posydown+=98;
            }
            if ( pl[activeplayer].position==24){
                pl[activeplayer].position=0;
                desk.startmoney(pl[activeplayer]);
                Toast.makeText(getContext(), "10000 za start",Toast.LENGTH_SHORT).show();
            }
            //invalidate();
        }
        animation(random+random2,posxleft,posyup,posxright,posydown,predchozipozice);
        //desk.tahlogika(pl[activeplayer].position,activeplayer,pl[activeplayer]);
        platba();
        zobrazkartu(pl[activeplayer].position);
        zmenatextu();
        /*Statistic statistic=new Statistic();
        statistic.ulozenistatistik();*/
        Statistic.pocettahu+=1;
        Statistic.ulozenistatistik();
        //invalidate();

    }

    public boolean onTouchEvent(MotionEvent touchevent){
        switch (touchevent.getAction()){
            case MotionEvent.ACTION_DOWN:{
                break;
            }
            case MotionEvent.ACTION_UP:{
                if(nextplayer==true){
                    pohyb();
                }

                break;
            }
        }
        return true;
    }

    private int random(){
        Random rand = new Random();
        int  n = rand.nextInt(6) + 1;
        return n;
    }

    void init(Context context) {
        bmp = new Bitmap[30];

        bmp[0] = BitmapFactory.decodeResource(getResources(), R.drawable.k1);
        bmp[1] = BitmapFactory.decodeResource(getResources(), R.drawable.k2);
        bmp[2] = BitmapFactory.decodeResource(getResources(), R.drawable.k3);
        bmp[3] = BitmapFactory.decodeResource(getResources(), R.drawable.k4);
        bmp[4] = BitmapFactory.decodeResource(getResources(), R.drawable.k5);
        bmp[5] = BitmapFactory.decodeResource(getResources(), R.drawable.k6);

        bmp[6] = BitmapFactory.decodeResource(getResources(), R.drawable.klobouk);
        bmp[7] = BitmapFactory.decodeResource(getResources(), R.drawable.zehlicka);
        bmp[8] = BitmapFactory.decodeResource(getResources(), R.drawable.kolecko);
        bmp[9] = BitmapFactory.decodeResource(getResources(), R.drawable.auto);

        bmp[10] = BitmapFactory.decodeResource(getResources(), R.drawable.brown);
        bmp[11] = BitmapFactory.decodeResource(getResources(), R.drawable.blue);
        bmp[12] = BitmapFactory.decodeResource(getResources(), R.drawable.pink);
        bmp[13] = BitmapFactory.decodeResource(getResources(), R.drawable.orange);
        bmp[14] = BitmapFactory.decodeResource(getResources(), R.drawable.red);
        bmp[15] = BitmapFactory.decodeResource(getResources(), R.drawable.yellow);
        bmp[16] = BitmapFactory.decodeResource(getResources(), R.drawable.green);
        bmp[17] = BitmapFactory.decodeResource(getResources(), R.drawable.bluetmava);
        bmp[18] = BitmapFactory.decodeResource(getResources(), R.drawable.brown2);
        bmp[19] = BitmapFactory.decodeResource(getResources(), R.drawable.blue2);
        bmp[20] = BitmapFactory.decodeResource(getResources(), R.drawable.pink2);
        bmp[21] = BitmapFactory.decodeResource(getResources(), R.drawable.orange2);
        bmp[22] = BitmapFactory.decodeResource(getResources(), R.drawable.red2);
        bmp[23] = BitmapFactory.decodeResource(getResources(), R.drawable.yellow2);
        bmp[24] = BitmapFactory.decodeResource(getResources(), R.drawable.green2);
        bmp[25] = BitmapFactory.decodeResource(getResources(), R.drawable.bluetmava2);
        bmp[26] = BitmapFactory.decodeResource(getResources(), R.drawable.chancecard);
        bmp[27] = BitmapFactory.decodeResource(getResources(), R.drawable.injailcard);
        bmp[28] = BitmapFactory.decodeResource(getResources(), R.drawable.parkingcard);
        bmp[29] = BitmapFactory.decodeResource(getResources(), R.drawable.gokarta);

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();



        paint.setColor(Color.RED);
        for(int i=0;i<Settings.pocethracu;i++){
            canvas.drawBitmap(bmp[i+6],pl[i].posx,pl[i].posy,paint);
        }


        b3=BitmapFactory.decodeResource(getResources(), R.drawable.k1);
        canvas.drawBitmap(bmp[kostka1bmp], 850, 538, paint);
        b4=BitmapFactory.decodeResource(getResources(), R.drawable.k1);
        canvas.drawBitmap(bmp[kostka2bmp], 1050, 538, paint);
        b5=BitmapFactory.decodeResource(getResources(), R.drawable.orange);
        canvas.drawBitmap(bmp[kartabmp], 780, 5, paint);

    }

    protected void move() {
        if(nextplayer==true){
            pohyb();
        }
    }
}
