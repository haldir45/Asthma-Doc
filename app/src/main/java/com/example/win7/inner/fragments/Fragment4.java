package com.example.win7.inner.fragments;

import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.asthmadoc.R;
import com.example.win7.dbhandler.MyDBHandler;
import com.example.win7.user.User;
import com.example.win7.weather.Weather;

import java.util.concurrent.RecursiveAction;

/**
 * Created by win7 on 11/3/2015.
 */
public class Fragment4 extends Fragment {

    private EditText surname;
    private EditText name;
    private EditText age;
    private EditText height;
    private RadioButton male;
    private RadioButton female;
    private RadioButton smoker;
    private RadioButton noSmoker;

    private String heightD;
    private String ageI;
    private String sexS;
    private boolean isSmokerBoolean;
    private String isSmoker ;

   // private TextView printuser;
    private Button submitB;
    MyDBHandler dbHandler;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment4_layout, container, false);

        surname = (EditText)v.findViewById(R.id.surnameET);
        name = (EditText)v.findViewById(R.id.nameET);
        age = (EditText)v.findViewById(R.id.ageET);
        height = (EditText)v.findViewById(R.id.heightET);
        male = (RadioButton)v.findViewById(R.id.maleRB);
        female = (RadioButton)v.findViewById(R.id.femaleRB);
        smoker = (RadioButton)v.findViewById(R.id.smokerRB);
        noSmoker = (RadioButton)v.findViewById(R.id.nosmokerRB);



       // printuser = (TextView)v.findViewById(R.id.printUserTV);


        submitB = (Button)v.findViewById(R.id.submitButton);
        submitB.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                addUser(v);
            }
        });

        dbHandler = new MyDBHandler(this.getContext(),null,null,0);

        printUserTable(v);





        return v;
    }

    //add a user to the database----prepei na perasoyme san parametro to view v
    // giati alliws den tha tin kataxwrisei san methodo toy button
    public void addUser(View v)
    {

            validate();
        if(isEmpty()){
            Toast.makeText(getActivity(),
                    getActivity().getString(R.string.empty_fields),
                    Toast.LENGTH_LONG).show();
        }else{

            User user = new User(surname.getText().toString(),name.getText().toString(),sexS,Double.parseDouble(heightD),
                    Integer.parseInt(ageI),isSmokerBoolean);
            dbHandler.addUser(user);
            printUserTable(v);

            //tous diagrafw gia na mhn exw axrista pragmata pros to paron stin basi moy
            dbHandler.deleteUsers();
        }




    }



    public boolean isEmpty()
    {

        if(surname.getText().equals("") | name.getText().equals("") | heightD.equals("")
                | sexS.equals("nothing")| ageI.equals("")| isSmoker.equals("nothing")){
            resetFields();
              return true;
        }

        return false;
    }
    //validate the info
    public void validate()
    {

        heightD = height.getText().toString();
        ageI = age.getText().toString();
        sexS ="";

        if(male.isChecked()){
            sexS = "male";
        }else if(female.isChecked()){
            sexS = "female";
        }
        else{
            sexS="nothing";
        }

        if(smoker.isChecked()){
            isSmokerBoolean = true;
            isSmoker = "true";
        }else if(noSmoker.isChecked()){
            isSmokerBoolean = false;
            isSmoker = "false";
        }else{
            isSmokerBoolean = false;
            isSmoker = "nothing";
        }
    }
    //print database user
    public void printUserTable(View v)
    {
        String dbString = dbHandler.tableUserToString();
       // printuser.setText(dbString);

        //Ta fairnw sthn arxikh toys katastash


          resetFields();
        age.setText("");
        height.setText("");

        //emfanisi tou pinaka sto android Monitor
        Log.i("User", dbString);

    }
    private void resetFields(){
        surname.setText("");
        name.setText("");
        male.setChecked(false);
        female.setChecked(false);
        smoker.setChecked(false);
        noSmoker.setChecked(false);
    }

}
