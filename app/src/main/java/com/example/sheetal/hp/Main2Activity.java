package com.example.sheetal.hp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity {

    private TextView mtextView;
    private EditText mloginemail;
    private EditText mloginPass;

    private Button mloginbtn;

    Animation uptodown;
    private ProgressDialog progressDialog;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();

        mtextView = findViewById(R.id.textView);
        mloginbtn = findViewById(R.id.loginBtn);
        mloginemail = findViewById(R.id.loginEmail);
        mloginPass = findViewById(R.id.loginPass);


        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        mloginbtn.setAnimation(uptodown);

        progressDialog = new ProgressDialog(this);

        mtextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        //      getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mloginemail.getText().toString();
                String password = mloginPass.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {


                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                progressDialog.setTitle("Sign In");
                                progressDialog.setMessage("Signing into your Account..");
                                progressDialog.show();

                                Intent mainIntent = new Intent(Main2Activity.this, VolunteerHomeScreenAfterLogin.class);
                                startActivity(mainIntent);
                                finish();

                            } else {
                                String erroMessage = task.getException().getMessage();
                                Toast.makeText(Main2Activity.this, "Error : " + erroMessage, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
            }
        });

    }

}
