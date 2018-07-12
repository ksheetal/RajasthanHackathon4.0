package com.example.sheetal.hp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

public class VolunteerHomeScreenAfterLogin extends AppCompatActivity {

    CardView childDetials;
    CardView motherDetails;
    CardView BmiCalculator;
    private Toolbar mainScreenToolbar;

    FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_home_screen_after_login);

        childDetials = findViewById(R.id.ChildCard);
        motherDetails = findViewById(R.id.MotherDetailsCard);
        BmiCalculator = findViewById(R.id.BmiCalCard);

//        getSupportActionBar().setHomeButtonEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        mainScreenToolbar = findViewById(R.id.homeScreenToolbar);
        setSupportActionBar(mainScreenToolbar);
        getSupportActionBar().setTitle("HomeScreen");
        getSupportActionBar().setHomeButtonEnabled(true);

      //  childDetials.setBackgroundResource(R.drawable.cardbackground);


        childDetials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VolunteerHomeScreenAfterLogin.this, HomeScreenActivity.class);
                startActivity(intent);
            }
        });
        motherDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VolunteerHomeScreenAfterLogin.this, MotherHomeScreenActivity.class);
                startActivity(intent);
            }
        });
        BmiCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VolunteerHomeScreenAfterLogin.this, BMICalculator.class);
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu1, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuLogout:
                logout();
                return true;

            default:
                return false;


        }
    }

    private void logout() {
        // Firebase sign out
        mAuth.signOut();
        Intent i = new Intent(VolunteerHomeScreenAfterLogin.this,Main2Activity.class);
        startActivity(i);

    }
}