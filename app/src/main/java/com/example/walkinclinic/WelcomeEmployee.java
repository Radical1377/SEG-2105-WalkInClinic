package com.example.walkinclinic;

import androidx.annotation.NonNull;
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
//import org.apache.commons.lang3;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WelcomeEmployee extends AppCompatActivity {

    private static User loggedInUser = null;
    private static Employee loggedInEmployee = null;


    DatabaseReference databaseEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_employee);

        loggedInUser = LoginActivity.getLoggedInUser();
        loggedInEmployee = LoginActivity.getLoggedInEmployee();

        //Toast.makeText(getApplicationContext(), loggedInEmployee.getUsername(), Toast.LENGTH_SHORT).show();


        databaseEmployees = FirebaseDatabase.getInstance().getReference("employees");

        String welcomeMsg;

        TextView eWelcome = (TextView) findViewById(R.id.welcomeMessage);
        //manipulating welcome text
        welcomeMsg = "Welcome " + loggedInUser.getFirst_name() + "!\n You're logged in as an employee.";

        eWelcome.setText(welcomeMsg);

    }

    @Override
    protected void onStart() {
        super.onStart();

            databaseEmployees.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot postSnap : dataSnapshot.getChildren()) {
//                        try {
                            if (postSnap.getValue().toString().contains(loggedInUser.getUsername())){
                                //Toast.makeText(getApplicationContext(),"GOT IT", Toast.LENGTH_SHORT).show();
                                //loggedInEmployee.setCompleted(true);
                                Employee product = postSnap.getValue(Employee.class);

                                product.set_username(loggedInUser.getUsername());
                                loggedInEmployee = product;
                                LoginActivity.setLoggedInEmployee(product);
                                //Toast.makeText(getApplicationContext(),product.getUsername(), Toast.LENGTH_SHORT).show();

                                break;
                            }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            }
            );

    }

    public void logoffButton(View view){
        // HAVE TO CLEAR THE USERNAME AND PASSWORD
        //finish();

        LoginActivity.setLoggedInEmployee(null);
        LoginActivity.setLoggedInUser(null);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void profileEmployee(View view){

        Intent intent = new Intent(this, ProfileEmployee.class);
        startActivity(intent);

    }
    public void clinicEmployee(View view){
        if (LoginActivity.getLoggedInEmployee().isCompleted()) {
            Intent intent = new Intent(this, ClinicEmployee.class);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),"Need to complete profile.", Toast.LENGTH_SHORT).show();
        }
    }
    public void employeeHours(View view){
        if (LoginActivity.getLoggedInEmployee().isCompleted()) {
            Intent intent = new Intent(this, EmployeeHours.class);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),"Need to complete profile.", Toast.LENGTH_SHORT).show();
        }
    }

    public static User getLoggedInUser(){
        return loggedInUser;
    }

    public static Employee getLoggedInEmployee(){
        return loggedInEmployee;
    }

    public void setLoggedInEmployee(Employee emp) {
        loggedInEmployee = emp;
    }

}
