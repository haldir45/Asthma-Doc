package com.example.win7.inner.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.win7.asthmadoc.R;
import com.example.win7.dbhandler.MyDBHandler;
import com.example.win7.weather.Weather;
import com.example.win7.weatherAPI.RemoteFetch;
import com.example.win7.weather.CityPreference;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {
    public JSONObject json ;
    Typeface weatherFont;

    TextView cityField;
    TextView updateField;
    TextView detailsField;
    TextView currentTemperatureField;
    TextView weatherIcon;
    MyDBHandler dbHandler;
    Handler handler;

    public WeatherFragment() {
        handler = new Handler();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "weather.ttf");
        updateWeatherData(new CityPreference(getActivity()).getCity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_weather, container, false);

        cityField = (TextView) rootView.findViewById(R.id.city_field);
        updateField = (TextView) rootView.findViewById(R.id.update_field);
        detailsField = (TextView) rootView.findViewById(R.id.details_field);
        currentTemperatureField = (TextView) rootView.findViewById(R.id.current_temperature_field);
        weatherIcon = (TextView) rootView.findViewById(R.id.weather_icon);

        dbHandler = new MyDBHandler(this.getContext(),null,null,0);
        weatherIcon.setTypeface(weatherFont);

        renderWeatherView(json);


        return rootView;
    }


    public void  printWeatherTable(){
        String dbString = dbHandler.tableWeatherToString();
        Log.i("Weather", dbString);
    }

    private void updateWeatherData(final String city) {
        new Thread() {
            @Override
            public void run() {
                 json = RemoteFetch.getJSON(getActivity(), city);
                if(json == null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(),
                                    getActivity().getString(R.string.place_not_found),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            renderWeather(json);
                        }
                    });
                }
            }
        }.start();
    }

    private void renderWeather(JSONObject json) {
        try {
            cityField.setText(json.getString("name").toUpperCase(Locale.US) +
                    ", " + json.getJSONObject("sys").getString("country"));

            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            JSONObject wind = json.getJSONObject("wind");

            //Converting windspeed km/h to Beaufort scale
            double windSpeed = wind.getDouble("speed");
            double beaufort ;
            Log.i("message", "windspeed" + windSpeed);
            if(windSpeed <=2.0){
                beaufort = 0;
            }else if(windSpeed<=5.0){
                beaufort = 1;
            }else if(windSpeed<=11.0) {
                beaufort = 2;
            }else if(windSpeed<=19){
                    beaufort = 3;
            }else if(windSpeed<=29.0){
                    beaufort = 4;
            }else if(windSpeed<=39.0){
                    beaufort = 5;
            }else if(windSpeed<=50.0){
                    beaufort = 6;
            }else if(windSpeed<=61.0){
                    beaufort = 7;
            }else if(windSpeed<=74.0){
                    beaufort = 8;
            }else if(windSpeed<=87.0){
                    beaufort = 9;
            }else if(windSpeed<=102.0){
                    beaufort = 10;
            }else if(windSpeed<=118.0){
                    beaufort = 11;
            }else{
                    beaufort = 12;
            }


            detailsField.setText(details.getString("description").toUpperCase(Locale.US) + "\n"
                    + "Humidity: " + main.getString("humidity") + "%" + "\n" + "Pressure: "
                    + main.getString("pressure") + "hPa" + "\n" + "Beaufort: " +
                    beaufort + "B");



            currentTemperatureField.setText(main.getInt("temp") - 274 + " °C");



            DateFormat df = DateFormat.getDateTimeInstance();
            String updateOn = df.format(new Date(json.getLong("dt") * 1000));
            updateField.setText("Last update: " + updateOn);

             //Inserting into TABLE_WEATHER
            Weather weather = new Weather(0,beaufort,Double.parseDouble(main.getString("humidity")),
                    main.getInt("temp")-274,"","",0);
            dbHandler.addWeather(weather, updateOn);
            printWeatherTable();
            //dbHandler.deleteWeather();


            setWeatherIcon(details.getInt("id"), json.getJSONObject("sys").getLong("sunrise") * 1000,
                    json.getJSONObject("sys").getLong("sunset") * 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void renderWeatherView(JSONObject json) {
        try {
            cityField.setText(json.getString("name").toUpperCase(Locale.US) +
                    ", " + json.getJSONObject("sys").getString("country"));

            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");


            detailsField.setText(details.getString("description").toUpperCase(Locale.US) + "\n"
                    + "Humidity: " + dbHandler.getWeathercolumnHumidity()+ "%" + "\n" + "Pressure: "
                    + main.getString("pressure") + "hPa" + "\n" + "Beaufort: " +
                   Double.parseDouble(dbHandler.getWeathercolumnBeaufort()) + "B");

            currentTemperatureField.setText( dbHandler.getWeathercolumnDegrees() + " °C");



            updateField.setText("Last update: " + dbHandler.getWeathercolumnDatetime());




            setWeatherIcon(details.getInt("id"), json.getJSONObject("sys").getLong("sunrise") * 1000,
                    json.getJSONObject("sys").getLong("sunset") * 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setWeatherIcon(int actualId, long sunrise, long sunset) {
        int id = actualId / 100;
        String icon = "";
        if(actualId == 800) {
            long currentTime = new Date().getTime();
            if(currentTime >= sunrise && currentTime < sunset) {
                icon = getActivity().getString(R.string.weather_sunny);
            } else {
                icon = getActivity().getString(R.string.weather_clear_night);
            }
        } else {
            switch (id) {
                case 2 : icon = getActivity().getString(R.string.weather_thunder);
                    break;
                case 3 : icon = getActivity().getString(R.string.weather_drizzle);
                    break;
                case 7 : icon = getActivity().getString(R.string.weather_foggy);
                    break;
                case 8 : icon = getActivity().getString(R.string.weather_cloudy);
                    break;
                case 6 : icon = getActivity().getString(R.string.weather_snowy);
                    break;
                case 5 : icon = getActivity().getString(R.string.weather_rainy);
                    break;
            }
        }
        weatherIcon.setText(icon);
    }

    public void changeCity(String city) {
        updateWeatherData(city);
    }


}
