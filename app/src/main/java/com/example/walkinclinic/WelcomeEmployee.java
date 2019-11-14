package com.example.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_employee);
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
