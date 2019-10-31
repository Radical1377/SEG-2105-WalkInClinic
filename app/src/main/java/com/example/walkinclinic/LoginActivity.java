package com.example.walkinclinic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


public class LoginActivity extends AppCompatActivity {

    private static User loggedInUser = null;
    private static Database db = new Database();

    DatabaseReference databaseUser = FirebaseDatabase.getInstance().getReference("users");;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseUser.keepSynced(true);

    }

    public void submitBtn(View view) throws Exception {

        databaseUser.keepSynced(true);

        EditText eUser = (EditText)findViewById(R.id.username);
        EditText ePassword = (EditText)findViewById(R.id.password);
        String username = eUser.getText().toString();
        final String encpassword = Sha256.encrypt(ePassword.getText().toString());



        databaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnap : dataSnapshot.getChildren()){
                    User targetUser = postSnap.getValue(User.class);

                    if (targetUser!=null && encpassword.equals(targetUser.getPassword())) {
                        loggedInUser = targetUser;
                    }


                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }
        );

        if  (loggedInUser != null) {
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(), "Incorrect Username/Password.", Toast.LENGTH_SHORT).show();
        }

        /*if(targetUser != null && Sha256.encrypt(ePassword.getText().toString()).equals(targetUser.getPassword())){
            setLoggedInUser(targetUser);
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Incorrect Username/Password.", Toast.LENGTH_SHORT).show();
        }*/


    }

    public void backBtn(View view){
        finish(); //redirect to the login page
    }

    public User getLoggedInUser(){
        return this.loggedInUser;
    }

}
