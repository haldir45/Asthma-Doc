package com.example.win7.dbhandler;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteStatement;

import com.example.win7.user.User;
import com.example.win7.weather.Weather;

/**
 * Created by win7 on 11/10/2015.
 */
public class MyDBHandler extends SQLiteOpenHelper
{
    // kathe fora poy kaneis allages sthn database allaxe to version

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "AsthmaDoc.db";
    private static final String TABLE_USER = "user";
    private static final String TABLE_WEATHER = "weather";

    private static final String USERCOLUMN_ID = "_id";
    private static final String USERCOLUMN_SURNAME = "surname";
    private static final String USERCOLUMN_NAME = "name";
    private static final String USERCOLUMN_SEX = "sex";



    private static final String USERCOLUMN_AGE = "age";
    private static final String USERCOLUMN_HEIGHT = "height";
    private static final String USERCOLUMN_SMOKER = "smoker";
    //city

    private static final String WEATHERCOLUMN_ID = "_id";
    private static final String WEATHERCOLUMN_HUMIDITY = "humidity";
    private static final String WEATHERCOLUMN_POLLUTON = "pollution";
    private static final String WEATHERCOLUMN_DEGREES = "degrees";
    private static final String WEATHERCOLUMN_MOOD = "mood";
    private static final String WEATHERCOLUMN_BEAUFORT = "beaufort";
    private static final String WEATHERCOLUMN_POLLEN = "pollen";
    private static final String WEATHERCOLUMN_DATETIME = "datetime";



    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userQuerry = "CREATE TABLE " + TABLE_USER + "(" +
                USERCOLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                USERCOLUMN_SURNAME + " TEXT ," +
                USERCOLUMN_NAME + " TEXT ," +
                USERCOLUMN_SEX + " TEXT ," +
                USERCOLUMN_AGE + " INTEGER ," +
                USERCOLUMN_HEIGHT + " REAL ," +
                USERCOLUMN_SMOKER + " INTEGER " +
                ");";

        String weatherQuerry = "CREATE TABLE " + TABLE_WEATHER + "(" +
                WEATHERCOLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                WEATHERCOLUMN_HUMIDITY + " REAL ," +
                WEATHERCOLUMN_POLLUTON + " REAL ," +
                WEATHERCOLUMN_DEGREES + " REAL ," +
                WEATHERCOLUMN_MOOD + " TEXT ," +
                WEATHERCOLUMN_POLLEN + " REAL ," +
                WEATHERCOLUMN_BEAUFORT + " REAL ," +
                WEATHERCOLUMN_DATETIME + " TEXT " +

                ");";
        db.execSQL(userQuerry);
        db.execSQL(weatherQuerry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEATHER);
        onCreate(db);
    }

    // add a new row to the TABLE_USER
    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(USERCOLUMN_SURNAME, user.get_surname());
        values.put(USERCOLUMN_NAME, user.get_name());
        values.put(USERCOLUMN_SEX, user.get_sex());
        values.put(USERCOLUMN_AGE, user.getAge());
        values.put(USERCOLUMN_HEIGHT, user.get_height());
        values.put(USERCOLUMN_SMOKER, user.is_smoker());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    // add a new row to the TABLE_WEATHER
    public void addWeather(Weather weather,String date ) {
        ContentValues values = new ContentValues();
        values.put(WEATHERCOLUMN_HUMIDITY, weather.get_humidity());
        values.put(WEATHERCOLUMN_POLLUTON, weather.get_pollution());
        values.put(WEATHERCOLUMN_DEGREES, weather.get_degrees());
        values.put(WEATHERCOLUMN_MOOD, weather.get_mood());
        values.put(WEATHERCOLUMN_POLLEN, weather.get_pollen());
        values.put(WEATHERCOLUMN_BEAUFORT, weather.get_beaufort());
        values.put(WEATHERCOLUMN_DATETIME,date);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_WEATHER, null, values);
        db.close();
    }

    //delete all rows from TABLE_USER
    public void deleteUsers() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String querry = "DELETE FROM " + TABLE_USER;
        db.execSQL(querry);
        db.close();
    }

    public void deleteWeather(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String querry = "DELETE FROM " + TABLE_WEATHER;
        db.execSQL(querry);
        db.close();
    }
    //Print out the TABLE_USER as a String
    public String tableUserToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String querry = "SELECT * FROM " + TABLE_USER + " WHERE 1";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(querry, null);

        //Move to the first row in your resutls
        c.moveToFirst();

        while (!c.isAfterLast()) {
            dbString += c.getString(c.getColumnIndex("_id")) + " ";
            dbString += c.getString(c.getColumnIndex("surname")) + " ";
            dbString += c.getString(c.getColumnIndex("name")) + " ";
            dbString += c.getString(c.getColumnIndex("sex")) + " ";
            dbString += c.getString(c.getColumnIndex("age")) + " ";
            dbString += c.getString(c.getColumnIndex("height")) + " ";
            dbString += c.getString(c.getColumnIndex("smoker")) + " ";
            dbString += "\n";
            c.moveToNext();
        }

        db.close();
        return dbString;
    }

    //Print out the TABLE_WEATHER as a String
    public String tableWeatherToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String querry = "SELECT * FROM " + TABLE_WEATHER + " WHERE 1";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(querry, null);

        //Move to the first row in your resutls
        c.moveToFirst();

        while (!c.isAfterLast()) {
            dbString += c.getString(c.getColumnIndex("_id")) + " ";
            dbString += c.getString(c.getColumnIndex("humidity")) + " ";
            dbString += c.getString(c.getColumnIndex("pollution")) + " ";
            dbString += c.getString(c.getColumnIndex("degrees")) + " ";
            dbString += c.getString(c.getColumnIndex("mood")) + " ";
            dbString += c.getString(c.getColumnIndex("beaufort")) + " ";
            dbString += c.getString(c.getColumnIndex("pollen")) + " ";
            dbString += c.getString(c.getColumnIndex("datetime")) + " ";
            dbString += "\n";
            c.moveToNext();
        }

        db.close();
        return dbString;

    }
    public boolean setWeathercolumnMood(String mood) {

 /*
        SQLiteDatabase db = getWritableDatabase();
        String querry = "UPDATE " + TABLE_WEATHER + " SET " + WEATHERCOLUMN_MOOD + " = " + mood  + " WHERE " + WEATHERCOLUMN_ID
                + " = " + "(SELECT max( " + WEATHERCOLUMN_ID + " ) FROM " + TABLE_WEATHER  + " )";
        SQLiteStatement stmt = db.compileStatement(querry);
        stmt.execute();



        db.close();
        */
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WEATHERCOLUMN_MOOD, mood);
        db.update(TABLE_WEATHER, contentValues,  WEATHERCOLUMN_ID + " = (SELECT max( " + WEATHERCOLUMN_ID + " ) FROM " + TABLE_WEATHER + " ) ",null);
        return true;


    }



    public String getWeathercolumnDegrees() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String querry = "SELECT degrees FROM " + TABLE_WEATHER ;

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(querry, null);

        //Move to the first row in your resutls
        c.moveToLast();

        dbString += c.getString(c.getColumnIndex("degrees")) ;



        db.close();
        return dbString;
    }

    public String getWeathercolumnHumidity() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String querry = "SELECT humidity FROM " + TABLE_WEATHER ;

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(querry, null);

        //Move to the first row in your resutls
        c.moveToLast();

        dbString += c.getString(c.getColumnIndex("humidity")) + " ";



        db.close();
        return dbString;
    }

    public  String getWeathercolumnMood() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String querry = "SELECT mood FROM " + TABLE_WEATHER ;

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(querry, null);

        //Move to the first row in your resutls
        c.moveToLast();

        dbString += c.getString(c.getColumnIndex("mood")) + " ";



        db.close();
        return dbString;
    }

    public String getWeathercolumnBeaufort() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String querry = "SELECT beaufort FROM " + TABLE_WEATHER ;

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(querry, null);

        //Move to the first row in your resutls
        c.moveToLast();

        dbString += c.getString(c.getColumnIndex("beaufort")) + " ";



        db.close();
        return dbString;
    }

    public  String getWeathercolumnPollen() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String querry = "SELECT pollen FROM " + TABLE_WEATHER ;

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(querry, null);

        //Move to the first row in your resutls
        c.moveToLast();

        dbString += c.getString(c.getColumnIndex("pollen")) + " ";



        db.close();
        return dbString;
    }

    public  String getWeathercolumnDatetime() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String querry = "SELECT datetime FROM " + TABLE_WEATHER ;

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(querry, null);

        //Move to the first row in your resutls
        c.moveToLast();

        dbString += c.getString(c.getColumnIndex("datetime")) + " ";



        db.close();
        return dbString;
    }

}
