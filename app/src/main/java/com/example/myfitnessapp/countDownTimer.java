package com.example.myfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class countDownTimer extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 1800000;
    private TextView textViewCountDown;
    private Button Start_Pause;
    private Button Reset;

    private CountDownTimer countDownTimer;
    private boolean timerRunning;

    private long TimeLeftinMillis =START_TIME_IN_MILLIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_timer);

        textViewCountDown =findViewById(R.id.textcountdown);
        Start_Pause = findViewById(R.id.start);
        Reset = findViewById(R.id.reset);


        Start_Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerRunning){
                    pauseTimer();

                }else{
                    startTimer();
                }


            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();

            }
        });
        updateCountDownText();




    }

    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        Start_Pause.setText("START");
        Reset.setVisibility(View.VISIBLE);



    }

    private void startTimer(){
        countDownTimer = new CountDownTimer(TimeLeftinMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeLeftinMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                timerRunning = false;
                Start_Pause.setText("START");
                Start_Pause.setVisibility(View.INVISIBLE);
                Reset.setVisibility(View.VISIBLE);


            }
        }.start();

        timerRunning=true;
        Start_Pause.setText("PAUSE");
        Reset.setVisibility(View.INVISIBLE);


    }

    private void updateCountDownText() {
        int minutes =(int) (TimeLeftinMillis/1000) /60;
        int secondes = (int) (TimeLeftinMillis/1000) %60;

        String TimeLeftformated = String .format(Locale.getDefault(),"%02d:%02d",minutes,secondes);
        textViewCountDown.setText(TimeLeftformated);
    }

    private void resetTimer(){
        TimeLeftinMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        Reset.setVisibility(View.INVISIBLE);
        Start_Pause.setVisibility(View.VISIBLE);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(countDownTimer.this, MainActivity.class);
        countDownTimer.this.finish();
        startActivity(i);
    }
}