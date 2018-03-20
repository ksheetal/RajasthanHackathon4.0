package com.example.sheetal.hp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tapadoo.alerter.Alerter;

public class MotherHomeScreenActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button showAlert;
    public String mMessage = null;
    public String mTitle =null;

    public TextView nTitle;
    public TextView nMessage;
//      DatabaseReference databaseReference1 =  FirebaseDatabase.getInstance().getReference().child("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_home_screen);

        mAuth = FirebaseAuth.getInstance();
        showAlert = findViewById(R.id.btnAlert);
        showAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlerter(view);
            }
        });


        nTitle = findViewById(R.id.mParentName);
        nMessage = findViewById(R.id.mvolunteerId);

    }
    /*    databaseReference1.addValueEventListener(new ValueEventListener() {
           // String mMessage = null;
            //String mTitile = null;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot doc : dataSnapshot.getChildren()){
                    Users users = doc.getValue(Users.class);
                    mMessage = users.getMessage();
                    mTitle =users.getTitle();
                }
                nTitle.setText(mMessage);
                nMessage.setText(mTitle);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);

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
        mAuth.signOut();
        sendtologin();
    }

   private void sendtologin() {
        Intent intent = new Intent(MotherHomeScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void showAlerter(View v){
        Alerter.create(this)
                .setTitle("Hello Brother")
                .setText("Where are You?")
                .show();

    }

}

