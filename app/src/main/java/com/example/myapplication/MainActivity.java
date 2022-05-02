package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button register;
    private EditText name,pass;
    private TextView bb;
    private Button login;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        // register
        register = (Button) findViewById(R.id.button2);
        register.setOnClickListener((View.OnClickListener) this);
        mAuth = FirebaseAuth.getInstance();
        // login
        login = (Button) findViewById(R.id.button1);
        login.setOnClickListener((View.OnClickListener) this);
        // user email and password
        name = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        progressBar=(ProgressBar) findViewById(R.id.ProgressBar);
    }

    @Override
    public void onClick(View view) {
     switch (view.getId()){
         case R.id.button2:
             startActivity(new Intent(this,signup.class));
             break;
       // appel la methode userLogin()
         case R.id.button1:
             userLogin();
             break;

     }
    }
// teste les champs email et password
    private void userLogin() {
        String email= name.getText().toString().trim();
        String password= pass.getText().toString().trim();
        if(email.isEmpty()){
            name.setError("Email is required");
            name.requestFocus();
            return;
        }

        if(password.isEmpty()){
            pass.setError("password is required");
            pass.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        // verifcation to login
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            // to dashbord
                            startActivity(new Intent(MainActivity.this,MainActivity2.class));

                        }
                        //failed error
                        else{
                            Toast.makeText(MainActivity.this,"Failed to LOGIN",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.VISIBLE);
                        }
    }});
}}
