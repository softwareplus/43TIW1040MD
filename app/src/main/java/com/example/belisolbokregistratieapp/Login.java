package com.example.belisolbokregistratieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity
{

    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    TableControllerLogging myLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myLogger = new TableControllerLogging(getApplicationContext());

        myLogger.messageType = getString(R.string.INFORMATION);
        myLogger.messageValue = "Application Started";
        myLogger.create();

        //myLogger.Log(getString(R.string.INFORMATION),"Application started");

        mEmail      = findViewById(R.id.Email);
        mPassword   = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn   = findViewById(R.id.btnLogin);
        mCreateBtn  = findViewById(R.id.lnkRegister);


        mCreateBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }

        });

        mLoginBtn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {


                String email    = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    mEmail.setError("Email is verplicht!");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    mPassword.setError("Wachtwoord verplicht!");
                    return;
                }

                myLogger.messageType = getString(R.string.INFORMATION);
                myLogger.messageValue = "Loging user : " + email;
                myLogger.create();


                progressBar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {

                       if(task.isSuccessful())
                       {
                           Toast.makeText(Login.this, "Aanmelding OK", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(),MainActivity.class));
                       }
                       else
                       {
                           Toast.makeText(Login.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                       }


                   }

                });

                progressBar.setVisibility(View.INVISIBLE);


            }
        });


    }
}
