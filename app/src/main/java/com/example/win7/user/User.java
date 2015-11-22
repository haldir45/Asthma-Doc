package com.example.win7.user;

/**
 * Created by win7 on 11/10/2015.
 */
public class User {

    private int _id;
    private String _surname;
    private String _name;
    private String _sex;
    private double _height;
    private int age;
    private boolean _smoker;

    public User() {
    }

    public User(String _surname, String _name, String _sex, double _height, int age, boolean _smoker) {

        this._surname = _surname;
        this._name = _name;
        this._sex = _sex;
        this._height = _height;
        this.age = age;
        this._smoker = _smoker;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_surname() {
        return _surname;
    }

    public void set_surname(String _surname) {
        this._surname = _surname;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_sex() {
        return _sex;
    }

    public void set_sex(String _sex) {
        this._sex = _sex;
    }

    public double get_height() {
        return _height;
    }

    public void set_height(double _height) {
        this._height = _height;
    }

    public boolean is_smoker() {
        return _smoker;
    }

    public void set_smoker(boolean _smoker) {
        this._smoker = _smoker;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
