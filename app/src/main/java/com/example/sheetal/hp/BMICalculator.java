package com.example.sheetal.hp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BMICalculator extends AppCompatActivity {
    private EditText height;
    private EditText weight;
    private TextView result;
    private Toolbar mainScreenToolbar;

    private Float finalResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        result = (TextView) findViewById(R.id.result);
  //      final float i = calculateBMI();

        mainScreenToolbar = findViewById(R.id.homeScreenToolbar);
        setSupportActionBar(mainScreenToolbar);
        getSupportActionBar().setTitle("BMI Calculator ");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Button checkbtn = findViewById(R.id.check_button);
        checkbtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {


                  String heightStr = height.getText().toString();
                  String weightStr = weight.getText().toString();

                  if (heightStr != null && !"".equals(heightStr)
                          && weightStr != null && !"".equals(weightStr)) {
                      float heightValue = Float.parseFloat(heightStr) / 100;
                      float weightValue = Float.parseFloat(weightStr);

                      float bmi = weightValue / (heightValue * heightValue);
                      //result.setText((int) bmi);
                      Toast.makeText(BMICalculator.this,"BMI is :"+bmi,Toast.LENGTH_SHORT).show();
                     }
          }
      });
    }
}

