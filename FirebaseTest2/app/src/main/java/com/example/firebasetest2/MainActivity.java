/*
***********
* Firebase Reference SEG2105 Walk-in clinic project
*
*
* Basic methods for sign in, sign out, and creation of account with email
* and password (no name or role yet)
*
* REQUIRED TO CONNECT TO FIREBASE
* Add firebase to Android studio (plugin)
* Click Tools>Firebase > Authentication and follow steps to connect to firebase
*
* TO_DO
* Create User database with names and roles
* Implement database with names.. into method
* Allow user to sign up with name and role as well
* Create second page that says user has successfully created account
 */
package com.example.firebasetest2;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {
    //log
    private static final String TAG = "MainActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    // UI references.
    private EditText mEmail, mPassword;
    private Button btnSignIn,btnSignOut,btnCreateAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declare buttons and edit texts in oncreate
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);

        //finds and associates button based on android:id in xml
        btnSignIn = (Button) findViewById(R.id.email_sign_in_button);
        btnSignOut = (Button) findViewById(R.id.email_sign_out_button);
        btnCreateAccount = (Button) findViewById(R.id.email_create_account_button);


        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    //creates toast (visible message)
                    //method for this at bottom
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out with: " + user.getEmail());
                }
                // ...
            }
        };
        //Button to SIGN_IN
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //the text fields
                String email = mEmail.getText().toString();
                String pass = mPassword.getText().toString();
                if(!email.equals("") && !pass.equals("")){
                    //call to firebase mAuth method
                    mAuth.signInWithEmailAndPassword(email,pass);
                }else{
                    toastMessage("You didn't fill in all the fields");
                }
            }
        });
        //Button to SIGN_OUT
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                toastMessage("Signing out");
            }
        });

        //Button to CREATE_ACCOUNT
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                if (!email.equals("") && !password.equals("")){
                    //call to mAuth firebase method using text inputs
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });
                    toastMessage("Successfully created account for"+email);
                } else {
                    toastMessage("Error try again");
                }

            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    //add a toast to show when successfully signed in
    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}