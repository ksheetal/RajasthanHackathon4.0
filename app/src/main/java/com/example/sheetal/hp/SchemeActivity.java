package com.example.sheetal.hp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SchemeActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme);

        listView =findViewById(R.id.listView);

        String [] items = {" Mission Indradhanush","Integrated Child Development Services (ICDS)","Pradhan Mantri Surakshit Matritva Abhiyan","PM JAN DHAN YOJANA SCHEME- THE MATERNITY BENEFIT PROGRAM"
        ,"National Nutrition Mission"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i ==1){
                  //  Toast.makeText(SchemeActivity.this, , Toast.LENGTH_SHORT).show();
                    Uri uri = Uri.parse("http://icds-wcd.nic.in");
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                }
                if(i==0){
                    Uri uri = Uri.parse("http://www.missionindradhanush.in");
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                }
                if(i==2){
                    Uri uri = Uri.parse("https://pmsma.nhp.gov.in");
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                }
                if(i==3){
                    Uri uri = Uri.parse(" https://www.pmjdy.gov.in/scheme");
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                }
                if(i==4){
                    Uri uri = Uri.parse("http://icds-wcd.nic.in/nnm/home.htm");
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);

                }
            }
        });

    }
}
