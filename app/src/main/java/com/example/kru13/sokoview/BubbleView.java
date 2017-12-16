package com.example.kru13.sokoview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
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

    public TextView texthrac;
    public TextView textpenize;
    public TextView texthodnota;
    public TextView textvlastnik;

    Bitmap b3;
    Bitmap b4;
    Bitmap b5;
    Bitmap[] bmp;
    int kostka1bmp=0;
    int kostka2bmp=0;
    int kartabmp=0;

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

    public void playerinit(){
        for(int i=0;i<Settings.pocethracu;i++){
            pl[i]=new Player();
        }
    }

    public void buy(){
        desk.tahlogika(pl[activeplayer].position,activeplayer,pl[activeplayer]);
    }

    private void zmenatextu(){
        texthrac.setText("Tah: Hráč "+(activeplayer+1));
        textpenize.setText("Peníze: ");
        texthodnota.setText("Hodnota: ");
        textvlastnik.setText("Vlastník: ");
    }



    private void animation(final int round, final int posxleft, final int posyup,final int posxright,final int posydown,final int predchozipozice){
        //Toast.makeText(getContext(), "pred "+predchozipozice+ "po "+pl[activeplayeranim].position,Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            public void run() {

                int y=0;
                /*if(pl[activeplayeranim].position==18){
                    pl[activeplayeranim].posy=615;
                    pl[activeplayeranim].posy=32;
                    invalidate();
                }*/
                if(predchozipozice<pl[activeplayeranim].position) {
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

                else{
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

    public void pohyb() {
        /*final MediaPlayer mp = MediaPlayer.create(this, R.raw.roll);
        mp.start();*/
        if(Settings.soundenabled) mpbview.start();// prehrani zvuku hod kostkou
        nextplayer=false;//nemuze hrat dalsi hrac

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
                //Toast.makeText(getContext(), "LOL"+player1pos,Toast.LENGTH_SHORT).show();
               // pl[activeplayer].posx-=103;
                //animation(-1, 103, "vertical");
                posxleft-=103;

                //Toast.makeText(getContext(), "DONE",Toast.LENGTH_SHORT).show();
            }

            if( pl[activeplayer].position>=7&& pl[activeplayer].position<13){
                //Toast.makeText(getContext(), player1pos,Toast.LENGTH_SHORT).show();
                //pl[activeplayer].posy-=98;
                posyup-=98;
                //animation(-1,98,"horizontal");
            }
            if( pl[activeplayer].position>=13&& pl[activeplayer].position<19){
                //Toast.makeText(getContext(), player1pos,Toast.LENGTH_SHORT).show();
                //pl[activeplayer].posx+=103;
                posxright+=103;
                //animation(1,103,"vertical");
            }
            if( pl[activeplayer].position>=19){
                //Toast.makeText(getContext(), player1pos,Toast.LENGTH_SHORT).show();
                //pl[activeplayer].posy+=98;
                posydown+=98;
                //animation(1,98,"horizontal");
            }
            if ( pl[activeplayer].position==24){
                pl[activeplayer].position=0;
            }
            //invalidate();
        }
        //Toast.makeText(getContext(), posxleft+" lefl",Toast.LENGTH_SHORT).show();
        animation(random+random2,posxleft,posyup,posxright,posydown,predchozipozice);
        desk.tahlogika(pl[activeplayer].position,activeplayer,pl[activeplayer]);
        zobrazkartu(pl[activeplayer].position);
        zmenatextu();
        //invalidate();
        if(activeplayer<Settings.pocethracu-1){
            activeplayer++;
        }
        else activeplayer=0;
    }

    public boolean onTouchEvent(MotionEvent touchevent){
        switch (touchevent.getAction()){
            case MotionEvent.ACTION_DOWN:{
                x1=touchevent.getX();
                y1=touchevent.getY();
                //Toast.makeText(getContext(), "x: " +x1 + ":y:"+y1,Toast.LENGTH_SHORT).show();
               // Toast.makeText(getContext(), "x: "+pl[1].hodnota(),Toast.LENGTH_SHORT).show();//////////////////FUNGUJE STATIC
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
        canvas.drawBitmap(bmp[kostka1bmp], 850, 530, paint);
        b4=BitmapFactory.decodeResource(getResources(), R.drawable.k1);
        canvas.drawBitmap(bmp[kostka2bmp], 1050, 530, paint);
        b5=BitmapFactory.decodeResource(getResources(), R.drawable.orange);
        canvas.drawBitmap(bmp[kartabmp], 780, 5, paint);

    }


 /*   protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        if(animace==true){
                pl[activeplayer].animposx += animacetest;
        }


        paint.setColor(Color.RED);
        for(int i=0;i<Settings.pocethracu;i++){
            canvas.drawBitmap(bmp[i+6],pl[i].animposx,pl[i].animposy,paint);
        }

        b3=BitmapFactory.decodeResource(getResources(), R.drawable.k1);
        canvas.drawBitmap(bmp[kostka1bmp], 850, 530, paint);
        b4=BitmapFactory.decodeResource(getResources(), R.drawable.k1);
        canvas.drawBitmap(bmp[kostka2bmp], 1050, 530, paint);
        b5=BitmapFactory.decodeResource(getResources(), R.drawable.orange);
        canvas.drawBitmap(bmp[kartabmp], 780, 5, paint);

        invalidate();

    }*/



    protected void move() {
        if(nextplayer==true){
            pohyb();
        }
    }
}
