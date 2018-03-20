package com.example.sheetal.hp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class VolunteerHomeScreenAfterLogin extends AppCompatActivity {

    CardView childDetials;
    CardView motherDetails;
    CardView BmiCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_home_screen_after_login);

        childDetials= findViewById(R.id.ChildCard);
        motherDetails=findViewById(R.id.MotherDetailsCard);
        BmiCalculator = findViewById(R.id.BmiCalCard);

//        getSupportActionBar().setHomeButtonEnabled(true);

        childDetials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VolunteerHomeScreenAfterLogin.this,HomeScreenActivity.class);
                startActivity(intent);
            }
        });
        motherDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VolunteerHomeScreenAfterLogin.this,MotherHomeScreenActivity.class);
                startActivity(intent);
            }
        });
        BmiCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VolunteerHomeScreenAfterLogin.this,BMICalculator.class);
                startActivity(intent);
            }
        });
    }
}
