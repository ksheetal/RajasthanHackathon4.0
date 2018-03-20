package com.example.sheetal.hp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class ChildVaccinationCenterActivity extends AppCompatActivity {

    public WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_vaccination_center);

        webView = findViewById(R.id.webView);
        webView.loadUrl("http://wcd.rajasthan.gov.in/anganwadi_search.aspx");
//        webView.loadUrl("http://wcd.rajasthan.gov.in/anganwadi_search.aspx");
    }
}
