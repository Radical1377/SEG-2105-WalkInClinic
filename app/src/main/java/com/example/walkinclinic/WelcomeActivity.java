package com.example.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

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
        welcomeMsg = "Welcome " + loggedInUser.getFirst_name() + "!\n You're logged in as an admin.";


        eWelcome.setText(welcomeMsg);
    }


    public void userAdminButton(View view){
        Intent intent = new Intent(this, userAdmin.class);
        startActivity(intent);
    }

    public void servicesAdminButton(View view){
        Intent intent = new Intent(this, servicesAdmin.class);
        startActivity(intent);
    }

    public void clinicsAdminButton(View view){
        Intent intent = new Intent(this, walkinclinicAdmin.class);
        startActivity(intent);
    }

    public void logoffButton(View view){
        // HAVE TO CLEAR THE USERNAME AND PASSWORD
        //finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }




}
