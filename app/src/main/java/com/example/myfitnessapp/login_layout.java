package com.example.myfitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_layout extends AppCompatActivity {

    EditText emailinput;
    EditText passwordinput;
    Button LoginBtn;
    TextView signInForm;
    ProgressDialog progressDialog;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

   FirebaseAuth mAuth;
   FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        emailinput = findViewById(R.id.UserNameLogin);
        passwordinput = findViewById(R.id.editPasswordLogin);

        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        LoginBtn = findViewById(R.id.LoginBtn);
        signInForm = findViewById(R.id.signintextview);


        //Bouton du login

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerfomLogin();

            }
        });

        //Bouton qui renvoye a l'inscription
        signInForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login_layout.this,sign_up_form.class);
                startActivity(i);

            }
        });






    }

   private void PerfomLogin() {
        String email = emailinput.getText().toString();
        String password = passwordinput.getText().toString();

        if(!email.matches(emailPattern)){
            emailinput.setError("Enter a correct email");
        }
        else if(password.isEmpty() || password.length()<6){
            passwordinput.setError("Enter a correct password");
        }
        else{
            progressDialog.setMessage("Please wait while login ...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(login_layout.this, "Login is Successful", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(login_layout.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    private void sendUserToNextActivity() {

        Intent i = new Intent(login_layout.this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}

