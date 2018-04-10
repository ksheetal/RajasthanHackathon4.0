package com.example.sheetal.hp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class AddChild extends AppCompatActivity {

    ListView mlistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        mlistView = findViewById(R.id.listView);



    }
}
