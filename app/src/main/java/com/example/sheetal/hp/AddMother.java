package com.example.sheetal.hp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddMother extends AppCompatActivity {

    private Toolbar addMotherToolbar;

    private EditText mMotherName;
    private EditText mMotherFatherName;
    private EditText mMotherDOB;
    private EditText mMotherExp;
    private EditText mMotherAdds;
    private EditText mMotherDesp;
    private Button mbtn;


    private DatabaseReference mPostDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mother);


        addMotherToolbar = findViewById(R.id.AddChild_Toolbar);
        setSupportActionBar(addMotherToolbar);
        getSupportActionBar().setTitle("Add Mother Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        mUser =mAuth.getCurrentUser();

        mPostDatabase = FirebaseDatabase.getInstance().getReference().child("MotherDetails").child(mUser.getUid());
        progressDialog = new ProgressDialog(this);


        mMotherName = findViewById(R.id.mName);
        mMotherFatherName = findViewById(R.id.mFatherName);
        mMotherDOB = findViewById(R.id.mDOB);
        mMotherExp = findViewById(R.id.mExpectingDate);
        mMotherAdds = findViewById(R.id.mAdds);
        mMotherDesp = findViewById(R.id.mDescp);
        mbtn = findViewById(R.id.AddmBtn);

    mbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startPosting();
        }
    });

    }

    private void startPosting() {
        progressDialog.setMessage("Adding Mother Details");
        progressDialog.show();

        String MN = mMotherName.getText().toString().trim();
        String MFN = mMotherFatherName.getText().toString().trim();
        String MDOB = mMotherDOB.getText().toString().trim();
        String MED = mMotherExp.getText().toString().trim();
        String MA = mMotherAdds.getText().toString().trim();
        String MD = mMotherDesp.getText().toString().trim();


        if(!TextUtils.isEmpty(MN) && !TextUtils.isEmpty(MFN) && !TextUtils.isEmpty(MDOB)&&
                !TextUtils.isEmpty(MED) && !TextUtils.isEmpty(MA) && !TextUtils.isEmpty(MD)){
            // Start uploading..
          //  blog blog1 = new blog("ChildName","ChildDB",
            //        "ChildFatherName","UserId");

            motherDetails details = new motherDetails("motherName","motherAge",
                    "motherFatherName","expectingDate",
                    "Address","desp","userid");

            DatabaseReference newPost = mPostDatabase.push();

            Map<String,String> DataToSave = new HashMap<>();
            DataToSave.put("motherName",MN);
            DataToSave.put("motherAge",MDOB);
            DataToSave.put("motherFatherName",MFN);
            DataToSave.put("expectingDate",MED);
            DataToSave.put("Address",MA);
            DataToSave.put("desp",MD);
            DataToSave.put("userId",mUser.getUid());

            newPost.setValue(DataToSave);
            Toast.makeText(getApplicationContext(),"Mother Details added. ",Toast.LENGTH_LONG).show();
            mMotherDesp.setText("");
            mMotherAdds.setText("");
            mMotherExp.setText("");
            mMotherDOB.setText("");
            mMotherFatherName.setText("");
            mMotherName.setText("");

            Intent intent = new Intent(AddMother.this,MotherHomeScreenActivity.class);
            startActivity(intent);
            progressDialog.dismiss();
        }
    }
}

