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
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.tapadoo.alerter.Alerter;

import org.w3c.dom.Text;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {


    private Toolbar mainScreenToolbar;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;
    private FirebaseDatabase mDatabase;
    private RecyclerView recyclerView;
    private BlogRecyclerAdapter blogRecyclerAdapter;
    private List<blog> blogList;



    private static final String TAG = "FireLog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //private void showAlerter(View v){


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference= mDatabase.getReference().child("Blog");
        mDatabaseReference.keepSynced(true);

        mainScreenToolbar = findViewById(R.id.homeScreenToolbar);
        setSupportActionBar(mainScreenToolbar);
        getSupportActionBar().setTitle("AAAs");
        getSupportActionBar().setHomeButtonEnabled(true);


        blogList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



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
        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                blog blog1 = dataSnapshot.getValue(blog.class);
                blogList.add(blog1);

                blogRecyclerAdapter = new BlogRecyclerAdapter(HomeScreenActivity.this,blogList);
                recyclerView.setAdapter(blogRecyclerAdapter);
                blogRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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

            case R.id.AddChild:
                if(mUser !=null && mAuth !=null ){
                    Intent intent = new Intent(HomeScreenActivity.this,AddChild.class);
                    startActivity(intent);
                    finish();
                }
            default:
                return false;


        }
    }


    private void logout() {
        if(mUser !=null && mAuth !=null ) {
            mAuth.signOut();
            sendtologin();
        }
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
