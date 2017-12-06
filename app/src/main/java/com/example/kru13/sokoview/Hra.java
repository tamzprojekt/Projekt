package com.example.kru13.sokoview;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class Hra extends Activity implements SensorEventListener {

    private SensorManager manager;
    private Sensor accel;
    private SokoView sokoView;
    private BubbleView bubbleView=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //sokoView.findViewById(R.id.sokoview);
        //sokoView=new SokoView(this);
        //sokoView= (SokoView) findViewById(R.id.sokoView);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(R.layout.activity_hra);
        //bubbleView = new BubbleView(this);
        //bubbleView= (BubbleView) findViewById(R.id.bubbleView);
        setContentView(R.layout.activity_hra);
       manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accel = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(this, accel,
                SensorManager.SENSOR_DELAY_GAME);
        if(Settings.soundenabled==true){
            sendsound();
        }


    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        //final MediaPlayer mp = MediaPlayer.create(this, R.raw.roll);
        BubbleView bubbleView= (BubbleView) findViewById(R.id.bubbleView);//musi byt tady a ne v oncreate
        bubbleView.move(event.values[0], event.values[1]);
        //bubbleView.mpbview=mp;
        bubbleView.invalidate();

        //Toast.makeText(getBaseContext(), "x: "+bubbleView.player1x,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onResume() {
        super.onResume();
        manager.registerListener(this, accel,
                SensorManager.SENSOR_DELAY_GAME);
    }
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }

    public void sendsound(){
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.roll);
        BubbleView bubbleView= (BubbleView) findViewById(R.id.bubbleView);//musi byt tady a ne v oncreate
        bubbleView.mpbview=mp;
    }

}
