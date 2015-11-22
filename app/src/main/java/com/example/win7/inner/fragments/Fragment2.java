package com.example.win7.inner.fragments;


import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.win7.asthmadoc.MainActivity;
import com.example.win7.asthmadoc.R;
import com.example.win7.dbhandler.MyDBHandler;
import com.example.win7.weather.Weather;

/**
 * Created by win7 on 11/3/2015.
 */
public class Fragment2 extends Fragment implements SeekBar.OnSeekBarChangeListener{
    TextView valueBreath=null;
    TextView valueAllergy=null;
    SeekBar seekBarBreath=null;
    SeekBar seekBarAllergy=null;
    MyDBHandler dbHandler;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment2_layout,container,false);


        valueBreath = (TextView)v.findViewById(R.id.breathValue);
        SeekBar seekBarBreath=(SeekBar)v.findViewById(R.id.breathSB);
        valueAllergy=(TextView)v.findViewById(R.id.allergyValue);
        SeekBar seekBarAllergy=(SeekBar)v.findViewById(R.id.allergySB);
        dbHandler=new MyDBHandler(this.getContext(),null,null,0);


        seekBarBreath.setOnSeekBarChangeListener(this);
        seekBarAllergy.setOnSeekBarChangeListener(this);
        return v;
    }


        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {
            if (Integer.parseInt(String.valueOf(progress))<33)
             {
                 if (seekBar.equals(seekBarBreath))
                    valueBreath.setText("Bad");
                 else if(seekBar.equals(seekBarAllergy))
                     valueAllergy.setText("Bad");
             }
            else if (Integer.parseInt(String.valueOf(progress))>=33 && (Integer.parseInt(String.valueOf(progress))<=66 ))
            {
                if (seekBar.equals(seekBarBreath))
                    valueBreath.setText("Average");
                else if(seekBar== seekBarAllergy)
                    valueAllergy.setText("Average");
            }
            else
            {
                if (seekBar.equals(seekBarBreath))
                    valueBreath.setText("Bad");
                else if(seekBar== seekBarAllergy)
                    valueAllergy.setText("Bad");
            }
            Log.v("", "" + seekBar);

            switch (seekBar.getId())
            {

                case R.id.breathSB:
                    if (Integer.parseInt(String.valueOf(progress)) < 33)
                    {
                        valueBreath.setText("Bad");

                    } else if (Integer.parseInt(String.valueOf(progress)) >= 33 && (Integer.parseInt(String.valueOf(progress)) <= 66))
                    {
                        valueBreath.setText("Average");

                    } else
                    {
                        valueBreath.setText("Good");

                    }
                        break;

                case R.id.allergySB:
                    if (Integer.parseInt(String.valueOf(progress)) < 33)
                    {
                        valueAllergy.setText("Bad");

                    } else if (Integer.parseInt(String.valueOf(progress)) >= 33 && (Integer.parseInt(String.valueOf(progress)) <= 66))
                    {
                        valueAllergy.setText("Average");

                    } else
                    {
                        valueAllergy.setText("Good");

                    }
                    break;


                    }
            } // end on Create
        @Override
        public void onStartTrackingTouch(SeekBar breathSB)
        {
// no-op
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar)
        {

            switch (seekBar.getId())
            {

                case R.id.breathSB:
                    updateMood(valueBreath);

                    break;

                case R.id.allergySB:
                    updateMood(valueAllergy);
                    break;


            }
        } // end on Create


    private void updateMood( TextView textView){

        switch (textView.getId())
        {

            case R.id.breathValue:
                Log.i("Message",String.valueOf(valueBreath.getText()));
              dbHandler.setWeathercolumnBreath(String.valueOf(valueBreath.getText()));
                break;

            case R.id.allergyValue:
                dbHandler.setWeathercolumnAllergy(String.valueOf(valueAllergy.getText()));
                break;


        }
    }


}

