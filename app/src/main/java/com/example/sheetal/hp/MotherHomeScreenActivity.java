package com.example.sheetal.hp;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;
import java.util.List;

public class MotherHomeScreenActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar mainScreenToolbar;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;
    private FirebaseDatabase mDatabase;
    private RecyclerView recyclerView;
    private blogRecyclerAdapterMother blogRecyclerAdapter;
    //  private DataSnapshot dataSnapshot;
    private List<motherDetails> blogList;
    //    private String childKey;
    private ListView listView;


    private List<String> keyvalue;

    private static final String TAG = "FireLog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_home_screen);

        //private void showAlerter(View v){


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("MotherDetails").child(mUser.getUid());
        mDatabaseReference.keepSynced(true);
        // final DatabaseReference childData  = mDatabase.getReference().child(String.valueOf(mDatabaseReference)).child(mUser.getUid());

        //mDatabaseReference = mDatabase.getReference(); //checking for particular post.

        mainScreenToolbar = findViewById(R.id.homeScreenToolbar);
        setSupportActionBar(mainScreenToolbar);
        getSupportActionBar().setTitle("Mother Details ");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView = findViewById(R.id.listView1);

        blogList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                motherDetails details = blogList.get(position);
                // AddMother m = new AddMother();
                //String childdata = m.returnChild();

                //Intent intent = getIntent();
                //String childKeyValue = intent.getStringExtra("childKey");

//                String [] ab = getIntent().getStringArrayExtra("a");
                Toast.makeText(MotherHomeScreenActivity.this, "Showing Contact Number : " + details.getDesp(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                // motherDetails details = blogList.get(position);
                showingDialog(position);
            }
        }));


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

    private void showingDialog(int position) {
        //AlertDialog.Builder mBuilder = new AlertDialog.Builder(MotherHomeScreenActivity.this);
        //View mView  = getLayoutInflater().inflate(R.layout.clickdialog,null);
        //mBuilder.setView(mView);
        //AlertDialog dialog = mBuilder.create();
        //dialog.show();
        final int posi = position;
        Dialog dialog = new Dialog(MotherHomeScreenActivity.this);
        dialog.setContentView(R.layout.clickdialog);
        listView = dialog.findViewById(R.id.listView1);
        dialog.setCancelable(true);
        dialog.show();

        String[] items = {"Call", "View Full Description", "Delete Mother Details", "See previous Activities", "Call Doctor/Details "
                , "Send Notification"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {

                    motherDetails details = blogList.get(posi);
                    Toast.makeText(MotherHomeScreenActivity.this, "Hi"+details.getDesp(),
                            Toast.LENGTH_SHORT).show();


                    Intent callIntent = new Intent(Intent.ACTION_DIAL,Uri.fromParts("tel",details.getDesp().toString(),null));
                    //callIntent.setData(Uri.parse("tel:9540217306"));
                  //  callIntent.setData(Uri.parse("tel:9540217306"));
                    startActivity(callIntent);
                    finish();
                }
                if(i ==1){
                  //  Toast.makeText(MotherHomeScreenActivity.this, "Coming Soon..",
                    //        Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MotherHomeScreenActivity.this,ViewDetailsActivity.class);
                    startActivity(intent);
                    finish();
                    //  Toast.makeText(SchemeActivity.this, , Toast.LENGTH_SHORT).show();
                    //  Uri uri = Uri.parse("http://icds-wcd.nic.in");
                    //Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    //startActivity(intent);
                }
            }
        });
    }

    //RECYCLER VIEW ONCLICK METHOND
    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }
    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private MotherHomeScreenActivity.ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView,
                                     final MotherHomeScreenActivity.ClickListener clicklistener){

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

                //blog blog1 = dataSnapshot.getValue(blog.class);
                motherDetails details = dataSnapshot.getValue(motherDetails.class);
                blogList.add(details);


                blogRecyclerAdapter = new blogRecyclerAdapterMother(MotherHomeScreenActivity.this,blogList);
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


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mothermenu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuLogout:
                logout();
                finish();
                return true;


            case R.id.addMother:
                if(mUser !=null && mAuth !=null ){
                    Intent intent = new Intent(MotherHomeScreenActivity.this,AddMother.class);
                    startActivity(intent);
                    finish();

                    return true;
                }
            case R.id.menuSettings:
                if(mUser !=null && mAuth !=null ){
                    Intent intent = new Intent(MotherHomeScreenActivity.this,accountSettingActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }

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

