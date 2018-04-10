package com.example.sheetal.hp;

import android.*;
import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.tapadoo.alerter.Alerter;

import org.w3c.dom.Text;

import java.net.URI;

public class HomeScreenActivity extends AppCompatActivity {

    private TextView mnAddress;
    private TextView mParentNName;
    private TextView mnpremature;
    private TextView mnSerialnumber;
    private TextView mnProvidedVaccination;
    private TextView mSerialNumber;
    private TextView mnVolunteerId;


    private TextView btn1;

    private Toolbar mainScreenToolbar;
    private FirebaseAuth mAuth;

    private Button imageButton;

    private Button btnalert;

    String mParentName = "";
//    private String nDate;
    // private DocumentRefrence documentRefrence;


    private static final String TAG = "FireLog";

    private RecyclerView mrecyclerView;

    private FirebaseDatabase database;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference("Data");
//    DatabaseReference mcondition = databaseReference.child("ChildOne/Address");
    // DatabaseReference mcondition1 = databaseReference.child("Users/ChildOne");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mAuth = FirebaseAuth.getInstance();

        mainScreenToolbar = findViewById(R.id.homeScreenToolbar);
        setSupportActionBar(mainScreenToolbar);
        getSupportActionBar().setTitle("AAAs");
        getSupportActionBar().setHomeButtonEnabled(true);

        mnSerialnumber = findViewById(R.id.mvolunteerId);
        mnAddress = findViewById(R.id.mAddress);
        mParentNName = findViewById(R.id.mParentName);
        mnpremature = findViewById(R.id.mPremature);
        mnProvidedVaccination = findViewById(R.id.mProvidedVaccination);
        mSerialNumber = findViewById(R.id.mSerialNumber);
        mnVolunteerId = findViewById(R.id.mvolunteerId);

        imageButton = findViewById(R.id.imageButton);
        //  btn1 = findViewById(R.id.textView2);

        btnalert =findViewById(R.id.btnAlert);
        btnalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlerter(view);

            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:102"));
                startActivity(callIntent);

            }
        });

         databaseReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {

                 // String sheetal = null;
                    String add = null;
                    String vname = null;
                    String pv= null;
                    String pre = null;
                    String vi =null;
                    String sn= null;
                    for (DataSnapshot doc : dataSnapshot.getChildren()) {
                        //ChildOne childOne = doc.getValue(ChildOne.class);
                        Data doc1 = doc.getValue(Data.class);
                        add = doc1.getAdds();
                        vname = doc1.getName();
                        pv = doc1.getPv();
                        pre = doc1.getPre();
                        sn=doc1.getsn();
                        vi = doc1.getVi();
                 }
                 mnAddress.setText(add);
                 mParentNName.setText(vname);
                 mnProvidedVaccination.setText(pv);
                 mnpremature.setText(pre);
                 mSerialNumber.setText(vi);
                 mnSerialnumber.setText(sn);

             }
             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });


        // FireBase Notification

/*
        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    // Get updated InstanceID token.
                    String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                    Log.d(TAG, "Refreshed token: " + refreshedToken);
                Toast.makeText(HomeScreenActivity.this,"hi"+refreshedToken,Toast.LENGTH_SHORT).show();

                    // If you want to send messages to this application instance or
                    // manage this apps subscriptions on the server side, send the
                    // Instance ID token to your app server.
                 //   sendRegistrationToServer(refreshedToken);
            }
        });
*/


        // Read from the database

    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            sendtologin();
        }
    }


    @Override
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
        Intent intent = new Intent(HomeScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void showAlerter(View v){
        Alerter.create(this)
                .setTitle("Injection Activity On next Monday")
                .setText("Time - 9AM to 10PM, Date - 26 March 2018, Venue - Rajasthan College. ")
                .show();

    }

}
