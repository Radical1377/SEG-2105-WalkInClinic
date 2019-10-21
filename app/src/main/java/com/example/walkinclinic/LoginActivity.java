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
    private Database db = new Database();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String getTest=db.getUser("mtran014").getFirst_name();
        Toast.makeText(getApplicationContext(), getTest, Toast.LENGTH_SHORT).show();
        /*
        Test existsUser

        if(db.existsUser("mtran014")){
            Toast.makeText(getApplicationContext(), "User does not exist in the database.", Toast.LENGTH_SHORT).show();
        }
        */
    }

    public void submitBtn(View view) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        EditText eUser = (EditText)findViewById(R.id.username);
        EditText ePassword = (EditText)findViewById(R.id.password);
        User targetUser;
        /*
        //test getUser
        targetUser = db.getUser(eUser.getText().toString());
        Toast.makeText(getApplicationContext(), targetUser.getUsername(), Toast.LENGTH_SHORT).show();
        */

        //check if user exists in database
        if(!db.existsUser(eUser.getText().toString())){
            Toast.makeText(getApplicationContext(), "User does not exist in the database.", Toast.LENGTH_SHORT).show();
        }
        else{
            targetUser = db.getUser(eUser.getText().toString());
            //Toast.makeText(getApplicationContext(), targetUser.getUsername(), Toast.LENGTH_SHORT).show();

            //check if password is correct
            if(!Sha256.encrypt(ePassword.getText().toString()).equals(targetUser.getPassword())){
                Toast.makeText(getApplicationContext(), "Incorrect Password.", Toast.LENGTH_SHORT).show();
            }
            else{ //activity transition happens after user and pass checks out
                setLoggedInUser(targetUser);
                Intent intent = new Intent(this, WelcomeActivity.class);
                startActivity(intent);
            }
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
