package com.example.sheetal.hp;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class accountSettingActivity extends AppCompatActivity {

    private Toolbar addMotherToolbar;
    private TextView tnc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);


        addMotherToolbar = findViewById(R.id.homeScreenToolbar);
        setSupportActionBar(addMotherToolbar);
        getSupportActionBar().setTitle("Account Settings");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tnc = findViewById(R.id.textView12);
        tnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(accountSettingActivity.this);
                dialog.setContentView(R.layout.termsandconditions);
                dialog.setCancelable(true);
                dialog.show();
            }
        });

    }
}
