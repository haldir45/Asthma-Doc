package com.example.win7.weather;

/**
 * Created by win7 on 11/10/2015.
 */
public class Weather {
    private int _id;
    private double _humidity;
    private double _pollution;
    private double _beaufort;
    private int _degrees;
    private String _mood;
    private int _pollen;

    public Weather() {
    }

    public Weather( double _pollution, double _beaufort ,double _humidity, int _degrees, String _mood,int _pollen) {

        this._pollution = _pollution;
        this._beaufort = _beaufort;
        this._humidity = _humidity;
        this._degrees = _degrees;
        this._mood = _mood;
        this._pollen = _pollen;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public double get_humidity() {
        return _humidity;
    }

    public void set_humidity(double _humidity) {
        this._humidity = _humidity;
    }

    public double get_pollution() {
        return _pollution;
    }

    public void set_pollution(double _pollution) {
        this._pollution = _pollution;
    }

    public double get_beaufort() {
        return _beaufort;
    }

    public void set_beaufort(double _windspeed) {
        this._beaufort = _windspeed;
    }

    public int get_degrees() {
        return _degrees;
    }

    public void set_degrees(int _degrees) {
        this._degrees = _degrees;
    }

    public String get_mood() {
        return _mood;
    }

    public void set_mood(String _mood) {
        this._mood = _mood;
    }

    public int get_pollen() {
        return _pollen;
    }

    public void set_pollen(int _pollen) {
        this._pollen = _pollen;
    }
}
