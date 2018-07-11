package com.example.sheetal.hp;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout mDotLayout;
    private TextView[] mDots;
    public TextView textViewLang;
    public TextView h;
    public TextView e;

    private SlideAdapter sliderAdapter;

    private Button mNextBtn;
    private Button mPreBtn;

    private int mCurrentPage;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        mDotLayout = findViewById(R.id.dotsLayout);
        mNextBtn = findViewById(R.id.nextBtn);
        mPreBtn = findViewById(R.id.preBtn);


        sliderAdapter = new SlideAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        viewPager.addOnPageChangeListener(viewListner);


        mAuth = FirebaseAuth.getInstance();


        //Adding onClickListner on the Buttons

//        textViewLang = findViewById(R.id.textViewLang);


//        updateView((String)Paper.book().read("language"));


        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mCurrentPage == 2) {

                    Intent intent = new Intent(MainActivity.this, MainHomeActivity.class);   // sending to the Login Activity
                    startActivity(intent);
                    finish();
                }
                viewPager.setCurrentItem(mCurrentPage + 1);         // sending back to next page from the current one
            }
        });


        mPreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(mCurrentPage - 1);
            }
        });
    }

    // starting new function to add dots

    public void addDotsIndicator(int j) {

        mDots = new TextView[3];
        mDotLayout.removeAllViews();     // removing multiples dots

        // Adding All Dots on the MainScreen
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhile));

            mDotLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) {
            mDots[j].setTextColor(getResources().getColor(R.color.colorLoginBtn));
        }

    }

    // page Selector

    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage = position;

            if (position == 0) {
                mNextBtn.setEnabled(true);
                mPreBtn.setEnabled(false);
                mPreBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("Next");
                mPreBtn.setText("");

            } else if (position == mDots.length - 1) {
                mNextBtn.setEnabled(true);
                mPreBtn.setEnabled(true);
                mPreBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Let's Start");
                mPreBtn.setText("Back");

            } else {
                mNextBtn.setEnabled(true);
                mPreBtn.setEnabled(true);
                mPreBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Next");
                mPreBtn.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    protected void onStart() {
        super.onStart();

        // Checking if user is logged in or not
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            sendtoHome();
        }
    }


    private void sendtoHome() {
        Intent mainIntent = new Intent(MainActivity.this, VolunteerHomeScreenAfterLogin.class);
        startActivity(mainIntent);
        finish();
    }

}
