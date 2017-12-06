package com.example.kru13.sokoview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Toast;


public class Settings extends Activity {

    public static boolean soundenabled=true;
    public static int pocethracu=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        CheckBox repeatChkBx = ( CheckBox ) findViewById( R.id.checkBoxzvuk );
        repeatChkBx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    soundenabled=true;
                    Toast.makeText(getBaseContext(), "Sound Enabled", Toast.LENGTH_SHORT).show();
                }
                else{
                    soundenabled=false;
                    Toast.makeText(getBaseContext(), "Sound Disabled", Toast.LENGTH_SHORT).show();
                }

            }
        });


        NumberPicker np = (NumberPicker)findViewById(R.id.number_picker);
        np.setMinValue(2);// restricted number to minimum value i.e 1
        np.setMaxValue(4);// restricked number to maximum value i.e. 31
        np.setWrapSelectorWheel(true);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                if(newVal==2) {
                    pocethracu=2;
                }
                if(newVal==3) {
                    pocethracu=3;
                }
                if(newVal==4) {
                    pocethracu=4;
                }
                Toast.makeText(getBaseContext(), "pocethracu "+pocethracu, Toast.LENGTH_SHORT).show();

            }
        });


    }


    }

