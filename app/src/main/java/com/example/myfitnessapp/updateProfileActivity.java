package com.example.myfitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.sql.SQLOutput;

public class updateProfileActivity extends AppCompatActivity {



    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    DatabaseReference myRef = firebaseDatabase.getReference().child("Users");


    EditText editusername,editfullname,editage,editweight,editheight;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        update=findViewById(R.id.update);

        editusername = findViewById(R.id.editUsername);
        editfullname = findViewById(R.id.editFullName);
        editage = findViewById(R.id.editAge);
        editweight = findViewById(R.id.editWeight);
        editheight = findViewById(R.id.editHeight);
        mAuth = FirebaseAuth.getInstance();
        currentUser= mAuth.getCurrentUser();
        String myKey = currentUser.getUid();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(myKey)) {

                    editusername.setText(snapshot.child(myKey).child("username").getValue(String.class));
                    editfullname.setText(snapshot.child(myKey).child("fullname").getValue(String.class));
                    editage.setText(snapshot.child(myKey).child("age").getValue(long.class).toString());
                    editweight.setText(snapshot.child(myKey).child("weight").getValue(long.class).toString());
                    editheight.setText(snapshot.child(myKey).child("height").getValue(long.class).toString());

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username =editusername.getText().toString();
                String fullname =editfullname.getText().toString();
                Long age =Long.valueOf(editage.getText().toString());
                Long weight =Long.valueOf(editweight.getText().toString());
                Long height =Long.valueOf(editheight.getText().toString());

                User u = new User(username,fullname,age,weight,height);
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{

                            myRef.child(myKey).child("username").setValue(u.getUsername());
                            myRef.child(myKey).child("fullname").setValue(u.getFullname());
                            myRef.child(myKey).child("age").setValue(u.getAge());
                            myRef.child(myKey).child("weight").setValue(u.getWeight());
                            myRef.child(myKey).child("height").setValue(u.getHeight());

                            Toast.makeText(updateProfileActivity.this, "Changed Successful", Toast.LENGTH_SHORT).show();
                        }
                        catch(Exception e){
                            Toast.makeText(updateProfileActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });




    }
}