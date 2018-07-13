package com.example.sheetal.hp;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AddChild extends AppCompatActivity {

    private EditText mChildName;
    private EditText mChildDOB;
    private EditText mChildFatherName;

    private DatabaseReference mPostDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;


    private Button mAddChildBtn;
    private ProgressDialog progressDialog;

    Toolbar addChildToolbar;

    NotificationHelper notificationHelper;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        notificationHelper = new NotificationHelper(this);

//        databaseReference = FirebaseDatabase.getInstance().getReference("Data").child("Notification").child("child2").child("name");
//ndatabaseReference = databaseReference.child("Data").child("child1");
        addChildToolbar = findViewById(R.id.AddChild_Toolbar);
        setSupportActionBar(addChildToolbar);
        getSupportActionBar().setTitle("Add Child");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mAuth = FirebaseAuth.getInstance();
        mUser =mAuth.getCurrentUser();

        mPostDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child(mUser.getUid());

        progressDialog = new ProgressDialog(this);
        mChildName = (EditText)findViewById(R.id.EnterChildName);
        mChildDOB = (EditText)findViewById(R.id.EnterChildDOB);
        mChildFatherName = (EditText)findViewById(R.id.EnterChildFatherName);
        mAddChildBtn = (Button)findViewById(R.id.AddChildBtn);

        mAddChildBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           // progressDialog.
                startposting();
            }
        });

   /* databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            String id = "Meeting on this Saturday at 1 PM. ";
            String childnumber = null;
         //   for(DataSnapshot doc : dataSnapshot.getChildren()){
                childnumber = dataSnapshot.getValue(String.class);
                sendnotification(id,childnumber);
           // }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
    }
/*
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendnotification(String id, String childnumber) {

        Toast.makeText(AddChild.this,"Notification send",Toast.LENGTH_SHORT).show();
        Notification.Builder builder = notificationHelper.getsheetalChannelNotification("Hello "+childnumber,id);
        notificationHelper.getManager().notify(new Random().nextInt(),builder.build());
    }*/

    }

    private void startposting() {
        progressDialog.setMessage("Adding child..");
        progressDialog.show();

        String Cname = mChildName.getText().toString().trim();
        String Cb = mChildDOB.getText().toString().trim();
        String CFatherName = mChildFatherName.getText().toString().trim();

        if(!TextUtils.isEmpty(Cname) && !TextUtils.isEmpty(Cb) && !TextUtils.isEmpty(CFatherName)){
            // Start uploading..
            blog blog1 = new blog("ChildName","ChildDB",
                    "ChildFatherName","UserId");

            DatabaseReference newPost = mPostDatabase.push();

            Map<String,String> DataToSave = new HashMap<>();
            DataToSave.put("ChildName",Cname);
            DataToSave.put("ChildDOB",Cb);
            DataToSave.put("ChildFatherName",CFatherName);
            DataToSave.put("UserId",mUser.getUid());

            newPost.setValue(DataToSave);
            Toast.makeText(getApplicationContext(),"Child added!",Toast.LENGTH_LONG).show();
            mChildName.setText("");
            mChildDOB.setText("");
            mChildFatherName.setText("");
            Intent intent = new Intent(AddChild.this,HomeScreenActivity.class);
            startActivity(intent);
            progressDialog.dismiss();
        }
    }
}