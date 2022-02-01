package com.example.myfitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sign_up_form extends AppCompatActivity {

    //Declaration des variables


    Button buttonsignin;
    TextView alreadyhaveaccout;
    EditText inputfullname , inputusername, inputemail, inputpassword ;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    //Create object of DatabaseReference to access firebase classe
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = firebaseDatabase.getReference().child("Users");


    //Now we are going to create the actual Firebase auth

    FirebaseAuth mAuth;
    FirebaseUser mUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);

        //Instanciation des variables


        inputfullname = findViewById(R.id.editFirstName);
        inputusername = findViewById(R.id.editLastName);
        inputemail=findViewById(R.id.UserName);
        inputpassword = findViewById(R.id.editPassword);



        buttonsignin = findViewById(R.id.SignUpBtn);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();

        alreadyhaveaccout = findViewById(R.id.alreadyAccount);
        alreadyhaveaccout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(sign_up_form.this,login_layout.class);
                startActivity(i);
            }
        });

        buttonsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformRegister();


                }
            });

    }

    private void PerformRegister() {
        String fullname = inputfullname.getText().toString();
        String username = inputusername.getText().toString();
        String email = inputemail.getText().toString();
        String password = inputpassword.getText().toString();
        //User u = new User(fullname,username,email,password);





        if(!email.matches(emailPattern)){
            inputemail.setError("Enter a correct email");
        }
        else if(password.isEmpty() || password.length()<6){
            inputpassword.setError("Enter a correct password");
        }else if(username.length()>16 || username.isEmpty()){
            inputusername.setError("Enter a correct username");
        }
        else if(fullname.isEmpty())
        {
            inputfullname.setError("Please enter your fullname");

        }
        else{
            progressDialog.setMessage("Please wait while register ...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        FirebaseUser firebaseUser=mAuth.getCurrentUser();
                        //Enter User data into the real time Database
                        User u = new User(fullname,username,email,password);
                        myRef.child(firebaseUser.getUid()).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    //firebaseUser.sendEmailVerification();
                                    Toast.makeText(sign_up_form.this, "Registration Successful ", Toast.LENGTH_SHORT).show();
                                    sendUserToNextActivity();


                                }else{
                                Toast.makeText(sign_up_form.this, "Registration is not Successful", Toast.LENGTH_SHORT).show();


                            }}
                        });

                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(sign_up_form.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }

    private void sendUserToNextActivity() {
        Intent i = new Intent(sign_up_form.this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

  /*  public void adduser(User u) {
        String fullname = inputfullname.getText().toString();
        String username = inputusername.getText().toString();
        String email = inputemail.getText().toString();
        String password = inputpassword.getText().toString();


        if(!email.matches(emailPattern)){
            inputemail.setError("Enter a correct email");
        }
        else if(password.isEmpty() || password.length()<6){
            inputpassword.setError("Enter a correct password");
        }else if(username.length()>16 || username.isEmpty()){
            inputusername.setError("Enter a correct username");
        }
        else if(fullname.isEmpty())
        {
            inputfullname.setError("Please enter your fullname");

        }
        else{
            progressDialog.setMessage("Please wait while register ...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            User u = new User(fullname,username,email,password);
            myRef.push().setValue(u);
            Intent i = new Intent(sign_up_form.this,MainActivity.class);
            startActivity(i);}

            //I send the whole object user to the database


            //We send data to firebase and we are making  as a unique id
            //databaseReference.child("users").child(username).child("fullname").setValue(fullname);



             //Authentification with Firbease AUth
           /*mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(sign_up_form.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        sendUserToNextActivity();

                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(sign_up_form.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }
            });*/

}





   /* private void sendUserToNextActivity() {
        Intent i = new Intent(sign_up_form.this,MainActivity.class);
        startActivity(i);

    }
}*/