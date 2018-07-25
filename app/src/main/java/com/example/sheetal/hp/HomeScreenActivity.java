package com.example.sheetal.hp;

import android.*;
import android.Manifest;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.tapadoo.alerter.Alerter;

import org.w3c.dom.Text;
import android.view.View;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.sheetal.hp.App.CHANNEL_2_ID;

public class HomeScreenActivity extends AppCompatActivity {


    private Toolbar mainScreenToolbar;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;
    private FirebaseDatabase mDatabase;
    private RecyclerView recyclerView;
    private BlogRecyclerAdapter blogRecyclerAdapter;
    private List<blog> blogList;

    private NotificationManagerCompat notificationManagerCompat;
    private ListView listView;



    private static final String TAG = "FireLog";


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //private void showAlerter(View v){


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference= mDatabase.getReference().child("Blog").child(mUser.getUid());
        mDatabaseReference.keepSynced(true);
        //mDatabaseReference = mDatabase.getReference(); //checking for particular post.


        notificationManagerCompat = NotificationManagerCompat.from(this);

       // LocalDate localDate = new LocalDate(Context);
        LocalDate localDate = LocalDate.now();

        mainScreenToolbar = findViewById(R.id.homeScreenToolbar);
        setSupportActionBar(mainScreenToolbar);
        getSupportActionBar().setTitle("Children Details ");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        blogList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                Toast.makeText(HomeScreenActivity.this, "Single Click on position :"+ position,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
               showingchilddetails();
            }
        }));




        ///FIREBASE NOTIFICATION NEW ONE START HERE

     /*   public void sendOnChannel2 (){
            String title = "Hello 2";
            String message = "Hey i am also a Notification";

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                    .setSmallIcon(R.drawable.ic_add_alert)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .build();

            notificationManagerCompat.notify(2, notification);
        }*/




        // FIREBASE NOTIFICATION NEW ONE ENDS HERE

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

    private void showingchilddetails() {
        final Dialog dialog = new Dialog(HomeScreenActivity.this);
        dialog.setContentView(R.layout.clickdialogechild);
        ListView listView = dialog.findViewById(R.id.listViewchild);
        dialog.setCancelable(true);
        dialog.show();

        String [] items = {"Call Parent","View Full Description","Delete Child Details","See previous Activities","Call Doctor/Details "
                ,"Send Notification"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i ==1){
                    Toast.makeText(HomeScreenActivity.this, "Coming Soon..",
                            Toast.LENGTH_SHORT).show();

                    //  Toast.makeText(SchemeActivity.this, , Toast.LENGTH_SHORT).show();
                    //  Uri uri = Uri.parse("http://icds-wcd.nic.in");
                    //Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    //startActivity(intent);
                }
                if(i==5){
                    //mAuth.getCurrentUser();

                    sendNotification();

                    dialog.dismiss();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendNotification() {
        LocalDate today = LocalDate.now();
        LocalDateTime time = LocalDateTime.now();
        String title = today.toString();
        String message1 = time.toString();

        String[] seprated = message1.split("T");
        String message = seprated[1];

        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.mom_outline_png_12)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);

    }

    //RECYCLER VIEW ONCLICK METHOND
    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }
    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


//RECYCLER VIEW ONITEM TOUCH ENDS

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            sendtologin();
        }

        //Query query = mDatabaseReference.child("blog").child(mUser.getUid());
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
                finish();
                return true;

            case R.id.AddChild:
                if(mUser !=null && mAuth !=null ){
                    Intent intent = new Intent(HomeScreenActivity.this,AddChild.class);
                    startActivity(intent);
                    finish();
                    return true;
                }

            case R.id.menuSettings:
                if(mUser !=null && mAuth !=null ){
                    Intent intent = new Intent(HomeScreenActivity.this,accountSettingActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
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
