package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {
    CardView profile;
    CardView event;
    CardView staff;
    CardView hatbox;
    CardView gallery;
    CardView exit;
    private Object v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        profile =findViewById(R.id.profile);
        event = findViewById(R.id.event);
        staff =findViewById(R.id.staff);
        hatbox =findViewById(R.id.chatbox);
        gallery=findViewById(R.id.gallery);
        exit=findViewById( R.id.exit);
        profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i =new Intent(getApplicationContext(),profile.class);
                startActivity(i);
            }
        });
        event.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i =new Intent(getApplicationContext(),event.class);
                startActivity(i);
            }
        });
        staff.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i =new Intent(getApplicationContext(),staf.class);
                startActivity(i);
            }
        });
        hatbox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i =new Intent(getApplicationContext(),chatbox.class);
                startActivity(i);
            }
        });
        gallery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i =new Intent(getApplicationContext(),gallery.class);
                startActivity(i);
            }
        });
        exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}