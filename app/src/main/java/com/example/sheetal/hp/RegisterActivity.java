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
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private TextView mregisterTextView;
    private EditText registerEmail;
    private EditText registerPass;
    private EditText registerConfirmPass;

    private Button registerSignup;

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


        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        registerSignup.setAnimation(uptodown);

        mAuth = FirebaseAuth.getInstance();

        //     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressDialog = new ProgressDialog(this);

        registerSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = registerEmail.getText().toString();
                String password = registerPass.getText().toString();
                String confirpassword = registerConfirmPass.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirpassword)) {

                    if (password.equals(confirpassword)) {
                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
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

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            senttomain();
        }

    }

    private void senttomain() {
        Intent intent = new Intent(RegisterActivity.this, HomeScreenActivity.class);
        startActivity(intent);
        finish();
    }
}
