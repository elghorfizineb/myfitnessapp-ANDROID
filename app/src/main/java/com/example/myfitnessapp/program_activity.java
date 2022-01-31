package com.example.myfitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class program_activity extends AppCompatActivity {

    RelativeLayout day1layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        day1layout = findViewById(R.id.day1layout);
        day1layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day1layout.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                Intent i = new Intent(program_activity.this, programDetailActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(program_activity.this, MainActivity.class);
        program_activity.this.finish();
        startActivity(i);
    }
}