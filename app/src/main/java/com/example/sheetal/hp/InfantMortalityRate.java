package com.example.sheetal.hp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfantMortalityRate extends AppCompatActivity {

    private TextView mname;
    private TextView madds;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infant_mortality_rate);

        mname = findViewById(R.id.text1);
        madds = findViewById(R.id.text2);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.addValueEventListener(new ValueEventListener() {
            String nname = null;
            String nadds =null;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot doc :dataSnapshot.getChildren()){
                    Data data = doc.getValue(Data.class);
                    nname = data.getAdds();
                    nadds = data.getName();
                }
                mname.setText(nname);
                madds.setText(nadds);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}
