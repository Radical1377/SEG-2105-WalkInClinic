package com.example.walkinclinic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public class LoginActivity extends AppCompatActivity {

    private static User loggedInUser = null;
    private static Database db = new Database();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void submitBtn(View view) throws NoSuchAlgorithmException, UnsupportedEncodingException, InterruptedException {

        EditText eUser = (EditText)findViewById(R.id.username);
        EditText ePassword = (EditText)findViewById(R.id.password);

        Database.setUser(eUser.getText().toString());
        User targetUser = db.getUser();

        if(targetUser != null && Sha256.encrypt(ePassword.getText().toString()).equals(targetUser.getPassword())){
            setLoggedInUser(targetUser);
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        }
        else{
            //check if password is correct
            Toast.makeText(getApplicationContext(), "Incorrect Username/Password.", Toast.LENGTH_SHORT).show();
        }


    }

    public void backBtn(View view){
        finish(); //redirect to the login page
    }

    public User getLoggedInUser(){
        return this.loggedInUser;
    }

    public void setLoggedInUser(User input){
        this.loggedInUser = input;
    }
}
