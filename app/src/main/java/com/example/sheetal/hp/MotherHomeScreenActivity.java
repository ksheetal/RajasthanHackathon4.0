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




    private TextView mnAddress;
    private TextView mParentNName;
    private TextView mnpremature;
    private TextView mnSerialnumber;
    private TextView mnProvidedVaccination;
    private TextView mSerialNumber;
    private TextView mnVolunteerId;





    private FirebaseAuth mAuth;
    Button showAlert;
    public String mMessage = null;
    public String mTitle =null;

    public TextView nTitle;
    public TextView nMessage;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
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


        mnSerialnumber = findViewById(R.id.mvolunteerId);
        mnAddress = findViewById(R.id.mAddress);
        mParentNName = findViewById(R.id.mParentName);
        mnpremature = findViewById(R.id.mPremature);
        mnProvidedVaccination = findViewById(R.id.mProvidedVaccination);
        mSerialNumber = findViewById(R.id.mSerialNumber);
        mnVolunteerId = findViewById(R.id.mvolunteerId);
        nTitle = findViewById(R.id.mParentName);
        nMessage = findViewById(R.id.mvolunteerId);


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

