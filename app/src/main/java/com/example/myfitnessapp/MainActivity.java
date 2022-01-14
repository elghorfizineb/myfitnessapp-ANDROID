package com.example.myfitnessapp;

import static com.google.android.material.bottomnavigation.BottomNavigationView.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This line hide

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);



        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new home_fragment()).commit();
        navigationView.setSelectedItemId(R.id.navigation_home);



        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch(item.getItemId()){
                    case R.id.navigation_home:
                        fragment = new home_fragment();
                        break;
                    case R.id.workouts:
                        fragment = new workouts_fragments();
                        break;
                    case R.id.diets:
                        fragment = new bmi_calculator();
                        break;
                    case R.id.navigation_notifications:
                        fragment = new settings_fragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();


                return true;
            }
        });



    }

}