package com.example.kru13.sokoview;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Hra extends Activity  {

    // The following are used for the shake detection
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hra);
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
				/*
				 * The following method, "handleShakeEvent(count):" is a stub //
				 * method you would use to setup whatever you want done once the
				 * device has been shook.
				 */
                handleShakeEvent(count);
            }
        });
        if(Settings.soundenabled==true){
            sendsound();
        }
        sendtextview();


        final Button button = (Button) findViewById(R.id.nextplayerbut);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nextplayer();
            }
        });
        final Button button2 = (Button) findViewById(R.id.buybut);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buy();
            }
        });

    }



    private void handleShakeEvent(int count) {
        Toast.makeText(getBaseContext(),"Shake", Toast.LENGTH_SHORT).show();
        BubbleView bubbleView= (BubbleView) findViewById(R.id.bubbleView);//musi byt tady a ne v oncreate
        bubbleView.move();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }




    public void sendsound(){
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.roll);
        BubbleView bubbleView= (BubbleView) findViewById(R.id.bubbleView);//musi byt tady a ne v oncreate
        bubbleView.mpbview=mp;
    }

    public void nextplayer(){
        BubbleView bubbleView= (BubbleView) findViewById(R.id.bubbleView);//musi byt tady a ne v oncreate
        bubbleView.nextplayer=true;
    }
    public void buy(){
        BubbleView bubbleView= (BubbleView) findViewById(R.id.bubbleView);//musi byt tady a ne v oncreate
        bubbleView.buy();
    }
    public void sendtextview(){
        TextView view= (TextView) findViewById(R.id.hractext);
        BubbleView bubbleView= (BubbleView) findViewById(R.id.bubbleView);
        bubbleView.texthrac=view;
        view= (TextView) findViewById(R.id.penizetext);
        bubbleView.textpenize=view;
        view= (TextView) findViewById(R.id.hodnotatext);
        bubbleView.texthodnota=view;
        view= (TextView) findViewById(R.id.vlastniktext);
        bubbleView.textvlastnik=view;
    }


}
