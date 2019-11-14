package com.example.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileEmployee extends AppCompatActivity {

    private static WelcomeEmployee la = new WelcomeEmployee();
    private static User loggedInUser = la.getLoggedInUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_employee);

        String welcomeMsg;

        TextView eWelcome = (TextView) findViewById(R.id.name);
        //manipulating welcome text
        welcomeMsg = "Welcome " + loggedInUser.getFirst_name() + "!\n You're logged in as an employee.";


        eWelcome.setText(welcomeMsg);
    }

    public void modifyButton(View view){
        Intent intent = new Intent(this, EditProfileEmployee.class);
        startActivity(intent);
    }
    public void backBtn(View view){
        finish(); //redirect to the welcome page
    }

}
