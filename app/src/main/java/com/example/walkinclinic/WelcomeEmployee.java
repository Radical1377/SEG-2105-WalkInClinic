package com.example.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WelcomeEmployee extends AppCompatActivity {

    private static LoginActivity la = new LoginActivity();
    private static User loggedInUser = la.getLoggedInUser();
    private static Employee loggedInEmployee = new Employee(loggedInUser.getUsername(),null);

    private static Employee tempEmployee;

    DatabaseReference databaseEmployees = FirebaseDatabase.getInstance().getReference("employees");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_employee);

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
        if (loggedInEmployee.isCompleted()) {
            Intent intent = new Intent(this, ClinicEmployee.class);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),"Need to complete profile.", Toast.LENGTH_SHORT).show();
        }
    }
    public void employeeHours(View view){
        if (loggedInEmployee.isCompleted()) {
            Intent intent = new Intent(this, ListOfHoursEmp.class);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),"Need to complete profile.", Toast.LENGTH_SHORT).show();
        }
    }

    public User getLoggedInUser(){
        return this.loggedInUser;
    }

    public Employee getLoggedInEmployee(){
        return this.loggedInEmployee;
    }

}
