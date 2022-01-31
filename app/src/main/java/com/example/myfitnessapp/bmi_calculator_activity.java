package com.example.myfitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class bmi_calculator_activity extends AppCompatActivity {
    TextView currentHeight, currentage, currentweight;
    ImageView incrementage,decrementage, incrementweight, decrementweight;
    SeekBar seekbarheight;
    RelativeLayout male,female;

    int intweight=55;
    int intage=22;
    int currentprogress;
    String intprogress="170";
    String typeofuser ="0";
    String age2="22";
    String weight2 = "55";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_calculator);

        currentage=findViewById(R.id.currentAge);
        currentHeight=findViewById(R.id.currentheight);
        currentweight=findViewById(R.id.cureentweight);
        incrementage = findViewById(R.id.incrementage);
        decrementage = findViewById(R.id.decrementage);
        incrementweight = findViewById(R.id.incrementweight);
        decrementweight = findViewById(R.id.decrementweight);
        seekbarheight = findViewById(R.id.seekbar);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofuser="Male";
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofuser="Female";

            }
        });

        seekbarheight.setMax(300);
        seekbarheight.setProgress(170);
        seekbarheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromuser) {
                currentprogress=progress;
                intprogress = String.valueOf(currentprogress);
                currentHeight.setText(intprogress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        incrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intage = intage+1;
                age2=String.valueOf(intage);
                currentage.setText(age2);
            }
        });


        decrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intweight= intweight-1;
                weight2=String.valueOf(intweight);
                currentweight.setText(weight2);
            }
        });

        incrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intweight= intweight+1;
                weight2=String.valueOf(intweight);
                currentweight.setText(weight2);
            }
        });

        decrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intage = intage-1;
                age2=String.valueOf(intage);
                currentage.setText(age2);
            }
        });






        android.widget.Button calculate= findViewById(R.id.calculatebmi);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(typeofuser.equals(0)){
                    Toast.makeText(getApplicationContext(), "Select your gender", Toast.LENGTH_SHORT).show();
                }
                else if(intprogress.equals("0")){
                    Toast.makeText(getApplicationContext(), "Select your weight", Toast.LENGTH_SHORT).show();
                }
                else if(intage == 0 || intage <0) {
                    Toast.makeText(getApplicationContext(), "Age is incorrect", Toast.LENGTH_SHORT).show();
                }
                else if(intweight == 0 || intweight <0) {
                    Toast.makeText(getApplicationContext(), "Weight is incorrect", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i=new Intent(bmi_calculator_activity.this,bmiactivity.class);
                    //User u = new User(typeofuser,intprogress,weight2,age2);
                    i.putExtra("height",intprogress);
                    i.putExtra("weight",weight2);
                    startActivity(i);



                }


            }
        });




    }


}