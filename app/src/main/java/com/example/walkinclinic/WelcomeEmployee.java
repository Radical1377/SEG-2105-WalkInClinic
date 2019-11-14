package com.example.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WelcomeEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_employee);

        LoginActivity la = new LoginActivity();
        User loggedInUser = la.getLoggedInUser();
        String welcomeMsg;

        TextView eWelcome = (TextView) findViewById(R.id.welcomeMessage);
        //manipulating welcome text
        welcomeMsg = "Welcome " + loggedInUser.getFirst_name() + "!\n You're logged in as an employee.";


        eWelcome.setText(welcomeMsg);
    }

    public void logoffButton(View view){
        // HAVE TO CLEAR THE USERNAME AND PASSWORD
        //finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void profileEmployee(View view){
        Intent intent = new Intent(this, ProfileEmployee.class);
        startActivity(intent);
    }
    public void clinicEmployee(View view){
        Intent intent = new Intent(this, ClinicEmployee.class);
        startActivity(intent);
    }
    public void employeeHours(View view){
        Intent intent = new Intent(this, ListOfHoursEmp.class);
        startActivity(intent);
    }
}
