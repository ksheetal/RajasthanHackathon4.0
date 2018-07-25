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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private CheckBox checkBox;
    private TextView mregisterTextView;
    private EditText registerEmail;
    private EditText registerPass;
    private EditText registerConfirmPass;

    private Button registerSignup;

    private FirebaseDatabase mDatabase;
    private DatabaseReference databaseReference, databaseReferenceparent;

    Animation uptodown;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mregisterTextView = findViewById(R.id.registerTextView);
        registerEmail = findViewById(R.id.registerLoginEmail);
        registerPass = findViewById(R.id.registerLoginPass);
        registerConfirmPass = findViewById(R.id.registerConfirmLoginPass);
        registerSignup = findViewById(R.id.registerLoginBtn);

        checkBox = findViewById(R.id.checkBox);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        registerSignup.setAnimation(uptodown);

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance();
        databaseReference = mDatabase.getReference().child("MUser");
        databaseReferenceparent = mDatabase.getReference().child("ParentDetails");

        //     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressDialog = new ProgressDialog(this);

        registerSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createNewAccount();
                }
        });

        mregisterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void createNewAccount() {
        final String email = registerEmail.getText().toString();
        String password = registerPass.getText().toString();
        String confirpassword = registerConfirmPass.getText().toString();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirpassword)) {

            if (password.equals(confirpassword)) {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(checkBox.isChecked()){
                            if (task.isSuccessful()) {
                                String userid = mAuth.getCurrentUser().getUid();
                                DatabaseReference currentUserDb = databaseReferenceparent.child(userid);
                                currentUserDb.child("Email").setValue(email);
                                progressDialog.setTitle("Success");
                                progressDialog.setMessage("We are creating your account. Please wait..");
                                progressDialog.show();
                                senttomain();
                            } else {
                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(RegisterActivity.this, "Error : " + errorMessage, Toast.LENGTH_SHORT).show();

                                }
                        }
                        if (task.isSuccessful()) {
                            String userid = mAuth.getCurrentUser().getUid();
                            DatabaseReference currentUserDb = databaseReference.child(userid);
                            currentUserDb.child("Email").setValue(email);
                            progressDialog.setTitle("Success");
                            progressDialog.setMessage("We are creating your account. Please wait..");
                            progressDialog.show();
                            senttomain();
                        } else {
                            String errorMessage = task.getException().getMessage();
                            Toast.makeText(RegisterActivity.this, "Error : " + errorMessage, Toast.LENGTH_SHORT).show();


                        }
                    }
                });

            } else {
                Toast.makeText(RegisterActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            senttomain();
            finish();
        }

    }

    private void senttomain() {
        Intent intent = new Intent(RegisterActivity.this, HomeScreenActivity.class);
        startActivity(intent);
        finish();
    }
}
