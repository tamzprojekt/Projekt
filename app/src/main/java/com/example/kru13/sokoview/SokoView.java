package com.example.kru13.sokoview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Arrays;

/**
 * Created by kru13 on 12.10.16.
 */
public class SokoView extends View{

    Bitmap[] bmp;

    Player play1=new Player();
    Player play2=new Player();
    int player1x=600;
    int player1y=615;
    int player2x=605;
    int player2y=615;

    int player1pos=0;
    int player2pos=0;


    float x1=0;
    float y1=0;


    public SokoView(Context context) {
        super(context);
        init(context);
    }

    public SokoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SokoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {

        bmp = new Bitmap[2];

        bmp[0] = BitmapFactory.decodeResource(getResources(), R.drawable.klobouk);
        bmp[1] = BitmapFactory.decodeResource(getResources(), R.drawable.zehlicka);

    }

    protected void move(float f, float g) {
        Toast.makeText(getContext(), f+"JAJA",Toast.LENGTH_SHORT).show();


    }

    public boolean onTouchEvent(MotionEvent touchevent){
        switch (touchevent.getAction()){
            case MotionEvent.ACTION_DOWN:{
                x1=touchevent.getX();
                y1=touchevent.getY();
                //Toast.makeText(getContext(), "x: " +x1 + ":y:"+y1,Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), "x: "+play1.hodnota(),Toast.LENGTH_SHORT).show();
                break;
            }
            case MotionEvent.ACTION_UP:{
                player1pos+=1;
                if(player1pos<=6){
                    //Toast.makeText(getContext(), "LOL"+player1pos,Toast.LENGTH_SHORT).show();
                    player1x-=98;
                }

                if(player1pos>=7&&player1pos<13){
                    //Toast.makeText(getContext(), player1pos,Toast.LENGTH_SHORT).show();
                    player1y-=98;
                }
                if(player1pos>=13&&player1pos<19){
                    //Toast.makeText(getContext(), player1pos,Toast.LENGTH_SHORT).show();
                    player1x+=98;
                }
                if(player1pos>=19){
                    //Toast.makeText(getContext(), player1pos,Toast.LENGTH_SHORT).show();
                    player1y+=98;
                }
                if (player1pos==24){
                    player1pos=0;
                }
                //player1y+=50;
                invalidate();
             break;
            }
        }
        return true;
    }

   /* @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w / 10;
        height = h / 11;
        super.onSizeChanged(w, h, oldw, oldh);
    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();

        paint.setColor(Color.RED);
       /* canvas.drawCircle(player1x, player1y, 20, paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(player2x, player2y, 20, paint);*/
        /*canvas.drawBitmap(bmp[1], null,
                new Rect(100,100,100,100), null);*/
        Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.klobouk);
        paint.setColor(Color.RED);
        canvas.drawBitmap(b, player1x, player1y, paint);
        Bitmap b2=BitmapFactory.decodeResource(getResources(), R.drawable.zehlicka);
        paint.setColor(Color.RED);
        canvas.drawBitmap(b2, player2x, player2y, paint);



      /*  for (int i = 0; i < lx; i++) {
            for (int j = 0; j < ly; j++) {
                canvas.drawBitmap(bmp[level[i*10 + j]], null,
                        new Rect(j*width, i*height,(j+1)*width, (i+1)*height), null);
            }
        }

        canvas.drawBitmap(bmp[6], null,
                new Rect(0*width, 10*height,(0+1)*width, (10+1)*height), null);*/
        /*canvas.drawBitmap(bmp[7], null,
                new Rect(2*width, 10*height,(2+1)*width, (10+1)*height), null);
        canvas.drawBitmap(bmp[8], null,
                new Rect(4*width, 10*height,(4+1)*width, (10+1)*height), null);

        canvas.drawBitmap(bmp[9], null,
                new Rect(6*width, 10*height,(6+1)*width, (10+1)*height), null);*/

         /*  for (int i = 10; i < 11; i++) {
            for (int j = 0; j < ly; j++) {
                canvas.drawBitmap(bmp[6], null,
                        new Rect(j*width, i*height,(j+1)*width, (i+1)*height), null);
                break;
            }
        }*/

    }

}
