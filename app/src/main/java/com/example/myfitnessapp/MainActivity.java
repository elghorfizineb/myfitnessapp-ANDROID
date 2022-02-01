package com.example.myfitnessapp;

import static com.google.android.material.bottomnavigation.BottomNavigationView.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.midi.MidiDeviceService;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    MaterialToolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FrameLayout frameLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //This line hide
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        frameLayout=findViewById(R.id.main_frame_layout);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new home_fragment()).commit();

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new home_fragment()).commit();
            navigationView.setCheckedItem(R.id.fragment_container);
        }




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new home_fragment()).commit();
                break;
            case R.id.diagnostic:
                Intent i = new Intent(MainActivity.this,bmi_calculator_activity.class);
                startActivity(i);
                break;
            case R.id.diets:
                Intent i2 = new Intent(MainActivity.this,countDownTimer.class);
                startActivity(i2);
                break;

            case R.id.programm:

                Intent i3 = new Intent(MainActivity.this,program_activity.class);
                startActivity(i3);
                break;



            case R.id.setting:
                Intent i4 = new Intent(MainActivity.this,updateProfileActivity.class);
                startActivity(i4);
                break;

            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to logout ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {
                        FirebaseAuth.getInstance().signOut();
                        Intent i = new Intent(MainActivity.this, login_layout.class);
                        startActivity(i);
                        // When the user click yes button
                        // then app will close

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        // If user click no
                        // then dialog box is canceled.
                        dialog.cancel();
                    }
                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();

                // Show the Alert Dialog box
                alertDialog.show();
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);




        return true;
    }




}