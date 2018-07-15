package com.example.sheetal.hp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

public class ViewDetailsActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar addDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);


        addDetails = findViewById(R.id.homeScreenToolbar);
        setSupportActionBar(addDetails);
        getSupportActionBar().setTitle("View Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
}
