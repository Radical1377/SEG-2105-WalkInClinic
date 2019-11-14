package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ProfileEmployee extends AppCompatActivity {

    private static WelcomeEmployee la = new WelcomeEmployee();
    private static User loggedInUser = la.getLoggedInUser();
    private static Employee loggedInEmployee = la.getLoggedInEmployee();
    private static String username = loggedInEmployee.getUsername();

    private static String thisClinic = loggedInEmployee.getClinic();

    DatabaseReference databaseEmployees = FirebaseDatabase.getInstance().getReference("employees");
    DatabaseReference databaseClinics = FirebaseDatabase.getInstance().getReference("clinics");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_employee);

        String name;

        TextView ename = (TextView) findViewById(R.id.name);
        ename.setText("Name: " + loggedInUser.getFirst_name()+" "+loggedInUser.getLast_name());

        TextView eEmail = (TextView) findViewById(R.id.email);
        eEmail.setText("Email: "+ loggedInUser.getEmail());

        TextView eUsername = (TextView) findViewById(R.id.username);
        eUsername.setText("Email: "+ username);

        TextView ePassword = (TextView) findViewById(R.id.password);
        ePassword.setText("Password: **********");

        TextView eClinic = (TextView) findViewById(R.id.clinicDisplay);
        if (thisClinic==null) {
            eClinic.setText(" ");
        }else {
            eClinic.setText("Clinic: "+thisClinic);
        }

        Button selectClinicButton = (Button) findViewById(R.id.clinicSelect);
        if (thisClinic==null) {
            selectClinicButton.setVisibility(View.VISIBLE);
        }else {
            selectClinicButton.setVisibility(View.INVISIBLE);
        }

    }
    public void selectClinic(View view){
        Intent intent = new Intent(this, EmployeeSelectClinic.class);
        startActivity(intent);
        finish();
    }

    public void backBtn(View view){
        finish(); //redirect to the welcome page
    }

    public String getClinicId() {
        return thisClinic;
    }
    public Employee getLoggedInEmployee() {
        return loggedInEmployee;
    }

}
