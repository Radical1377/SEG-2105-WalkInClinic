package com.example.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        LoginActivity la = new LoginActivity();
        User loggedInUser = la.getLoggedInUser();
        String welcomeMsg;

        TextView eWelcome = (TextView) findViewById(R.id.welcomeMessage);

        //manipulating welcome text
        welcomeMsg = "Welcome " + loggedInUser.getFirst_name() + ". You're logged in as ";

        switch(loggedInUser.getRole()){
            case 0:{
                welcomeMsg += "admin.";
                break;
            }
            case 1:{
                welcomeMsg += "employee.";
                break;
            }
            case 2:{
                welcomeMsg += "patient.";
                break;
            }
            default:{
                welcomeMsg += "user.";
                break;
            }
        }

        eWelcome.setText(welcomeMsg);
    }

}
