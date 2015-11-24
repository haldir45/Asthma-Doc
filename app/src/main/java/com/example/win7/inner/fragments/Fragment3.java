package com.example.win7.inner.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.win7.asthmadoc.R;
import com.example.win7.dbhandler.MyDBHandler;

/**
 * Created by win7 on 11/3/2015.
 */
public class Fragment3 extends Fragment {
    TextView prediction=null;
    MyDBHandler dbHandler;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment3_layout,container,false);

        prediction = (TextView)v.findViewById(R.id.predictionTV);
        dbHandler=new MyDBHandler(this.getContext(),null,null,0);

        setPrediction();

        return v;
    }

    private  void setPrediction()
    {
        double humidity = Double.parseDouble(dbHandler.getWeathercolumnHumidity());
        if(humidity>86)
        {
            prediction.setText("There is a high possibility of having breathing discomfor, take your inhaler with you!");

        }
        else if(humidity>=25 && humidity<=85)
        {
            prediction.setText("You might experience breath discomfort, we advise you to have you inhaler with you.");
        }
        else
        {
            prediction.setText("Propably today you will not experience any discomfort");
        }

    }
}
