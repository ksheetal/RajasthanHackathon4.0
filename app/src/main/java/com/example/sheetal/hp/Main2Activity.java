package com.example.sheetal.hp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Main2Activity extends AppCompatActivity {

    private TextView mtextView;
    private EditText mloginemail;
    private EditText mloginPass;

    private SignInButton mGoogleBtn;
    // GoogleSignInClient
    private GoogleApiClient mGoogleApiClient;
    GoogleApiClient mGoogleSignInCLient;
    private Button mloginbtn;

    Animation uptodown;
    private ProgressDialog progressDialog;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();


        mGoogleBtn = findViewById(R.id.googlebtn);

        // ******************Google SignIn Start here ************

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mGoogleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        //** *** google SignIn***

        mtextView = findViewById(R.id.textView);
        mloginbtn = findViewById(R.id.loginBtn);
        mloginemail = findViewById(R.id.loginEmail);
        mloginPass = findViewById(R.id.loginPass);


        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        mloginbtn.setAnimation(uptodown);
        mGoogleBtn.setAnimation(uptodown);

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

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, 101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                progressDialog.setTitle("Sign In");
                progressDialog.setMessage("Signing into your Account..");
                progressDialog.show();

                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // [START_EXCLUDE]

                // [END_EXCLUDE]
            }
        }

    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("LoginActivity", "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        try {

            //   dialog.show();
        } catch (Exception e) {

        }
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("LoginActivity", "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("LoginActivity", "signInWithCredential", task.getException());
                            Toast.makeText(Main2Activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [START_EXCLUDE]
                        Toast.makeText(Main2Activity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Main2Activity.this, VolunteerHomeScreenAfterLogin.class);
                        startActivity(intent);
                        finish();

                        try {

                            // dialog.dismiss();
                        } catch (Exception e) {

                        }
                        // [END_EXCLUDE]
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }



}