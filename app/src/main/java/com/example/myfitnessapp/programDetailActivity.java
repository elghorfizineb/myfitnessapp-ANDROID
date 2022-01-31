package com.example.myfitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class programDetailActivity extends AppCompatActivity {

    YouTubePlayerView youTubePlayerView;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = firebaseDatabase.getReference().child("Programs").child("program1");
    DatabaseReference myRef2 = firebaseDatabase.getReference().child("Programs").child("program2");
    TextView food1, food2, food3, food4, food5, food6, food7, food8, food9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_detail);

        food1 = findViewById(R.id.food1);
        food2 = findViewById(R.id.food2);
        food3 = findViewById(R.id.food3);
        food4 = findViewById(R.id.food4);
        food5 = findViewById(R.id.food5);
        food6 = findViewById(R.id.food6);
        food7 = findViewById(R.id.food7);
        food8 = findViewById(R.id.food8);
        food9 = findViewById(R.id.food9);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                food1.setText(snapshot.child("Meal_1").child("food1").getValue(String.class));
                food2.setText(snapshot.child("Meal_1").child("food2").getValue(String.class));
                food3.setText(snapshot.child("Meal_1").child("food3").getValue(String.class));
                food4.setText(snapshot.child("Meal_2").child("food1").getValue(String.class));
                food5.setText(snapshot.child("Meal_2").child("food2").getValue(String.class));
                food6.setText(snapshot.child("Meal_2").child("food3").getValue(String.class));
                food7.setText(snapshot.child("Meal_3").child("food1").getValue(String.class));
                food8.setText(snapshot.child("Meal_3").child("food2").getValue(String.class));
                food9.setText(snapshot.child("Meal_3").child("food3").getValue(String.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        youTubePlayerView =findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                String id="ml6cT4AZdqI";
                youTubePlayer.loadVideo(id,0);


            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(programDetailActivity.this, program_activity.class);
        programDetailActivity.this.finish();
        startActivity(i);
    }
}