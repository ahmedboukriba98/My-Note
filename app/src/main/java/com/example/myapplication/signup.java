package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.BreakIterator;

public class signup extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private TextView banner,registeruser;
    private ProgressBar progressBar;
    private EditText username,email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        banner =(TextView)findViewById(R.id.banner);
        banner.setOnClickListener(this);
        registeruser=findViewById(R.id.registeruser);
        registeruser.setOnClickListener(this);
        username=(EditText) findViewById(R.id.username);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        progressBar=(ProgressBar) findViewById(R.id.ProgressBar);
    }@Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.banner:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.registeruser:
                registeruser();
                break;
        }
    }
    private void registeruser() {
        String emaiil= email.getText().toString().trim();
        String usernamee= username.getText().toString().trim();
        String pass= password.getText().toString().trim();
        if(usernamee.isEmpty()){
            username.setError("you must be full the user name");
            username.requestFocus();
            return;
        }

        if(emaiil.isEmpty()){
            email.setError("email is required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emaiil).matches()){
            email.setError("email invalied !!");
            email.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            password.setError("password is required");
            password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(emaiil,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user=new User(usernamee,emaiil,pass);
                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance()
                                    .getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(signup.this,"ueser has been registred",Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(signup.this,"Failed to register",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.VISIBLE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(signup.this,"Failed to register",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }
}