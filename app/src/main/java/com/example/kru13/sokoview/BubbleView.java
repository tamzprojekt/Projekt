package com.example.kru13.sokoview;

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
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Gastovsky on 01.12.2017.
 */

public class BubbleView extends View {

    public MediaPlayer mpbview;//zvuk hod kostkou

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

    private int x;
    private int y;

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



  /*  private void animation(int player, int rychlost){
        if(activeplayer==1){
            for(int i=0;i<98;i++) {
                player1x -= 1;
                Log.d("lol","EEEE");

            }
        }
    }*/

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
        if(desk.nazvykaret[pomocna]=="brown1"||desk.nazvykaret[pomocna]=="brown2"){
            kartabmp=10;
        }
        else if(desk.nazvykaret[pomocna]=="blue1"||desk.nazvykaret[pomocna]=="blue2"){
            kartabmp=11;
        }
        else if(desk.nazvykaret[pomocna]=="pink1"||desk.nazvykaret[pomocna]=="pink2"){
            kartabmp=12;
        }
        else if(desk.nazvykaret[pomocna]=="orange1"||desk.nazvykaret[pomocna]=="orange2"){
            kartabmp=13;
        }
        else if(desk.nazvykaret[pomocna]=="red1"||desk.nazvykaret[pomocna]=="red2"){
            kartabmp=14;
        }
        else if(desk.nazvykaret[pomocna]=="yellow1"||desk.nazvykaret[pomocna]=="yellow2"){
            kartabmp=15;
        }
        else if(desk.nazvykaret[pomocna]=="green1"||desk.nazvykaret[pomocna]=="green2"){
            kartabmp=16;
        }
        else if(desk.nazvykaret[pomocna]=="bluet1"||desk.nazvykaret[pomocna]=="bluet2"){
            kartabmp=17;
        }
    }

    private void pohyb() {
        /*final MediaPlayer mp = MediaPlayer.create(this, R.raw.roll);
        mp.start();*/
        if(Settings.soundenabled) mpbview.start();// prehrani zvuku hod kostkou



        int random=random();
        kostka1(random);
        int random2=random();
        kostka2(random2);
        for(int i=0;i<random+random2;i++){
            pl[activeplayer].position+=1;
            if( pl[activeplayer].position<=6){
                //Toast.makeText(getContext(), "LOL"+player1pos,Toast.LENGTH_SHORT).show();
                pl[activeplayer].posx-=103;
            }

            if( pl[activeplayer].position>=7&& pl[activeplayer].position<13){
                //Toast.makeText(getContext(), player1pos,Toast.LENGTH_SHORT).show();
                pl[activeplayer].posy-=98;
            }
            if( pl[activeplayer].position>=13&& pl[activeplayer].position<19){
                //Toast.makeText(getContext(), player1pos,Toast.LENGTH_SHORT).show();
                pl[activeplayer].posx+=103;
            }
            if( pl[activeplayer].position>=19){
                //Toast.makeText(getContext(), player1pos,Toast.LENGTH_SHORT).show();
                pl[activeplayer].posy+=98;
            }
            if ( pl[activeplayer].position==24){
                pl[activeplayer].position=0;
            }
            invalidate();
        }
        desk.tahlogika(pl[activeplayer].position,activeplayer,pl[activeplayer]);
        zobrazkartu(pl[activeplayer].position);
        invalidate();
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
                Toast.makeText(getContext(), "x: "+pl[1].hodnota(),Toast.LENGTH_SHORT).show();//////////////////FUNGUJE STATIC
                break;
            }
            case MotionEvent.ACTION_UP:{
                pohyb();
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
        bmp = new Bitmap[18];

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

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //bubble.draw(canvas);
        Paint paint = new Paint();

        paint.setColor(Color.RED);
       /* canvas.drawCircle(player1x, player1y, 20, paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(player2x, player2y, 20, paint);*/
        /*canvas.drawBitmap(bmp[1], null,
                new Rect(100,100,100,100), null);*/
        /*Bitmap b= BitmapFactory.decodeResource(getResources(), R.drawable.klobouk);
        canvas.drawBitmap(b, pl[0].posx, pl[0].posy, paint);
        Bitmap b2=BitmapFactory.decodeResource(getResources(), R.drawable.zehlicka);
        canvas.drawBitmap(b2, pl[1].posx, pl[1].posy, paint);*/
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
    protected void move(float f, float g) {
        x = (int) (x + f);
        y = (int) (y + g);

        if(f>11||f<8) {
            Toast.makeText(getContext(), "x: " + g + " f " + f, Toast.LENGTH_SHORT).show();
            //player1x+=random();
            //pohyb();
        }


    }
}
