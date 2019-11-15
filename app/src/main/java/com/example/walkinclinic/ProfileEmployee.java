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

    private static User loggedInUser = null;
    private static Employee loggedInEmployee = null;

    DatabaseReference databaseClinics = FirebaseDatabase.getInstance().getReference("walkinclinic");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_employee);

        loggedInUser = LoginActivity.getLoggedInUser();
        loggedInEmployee = LoginActivity.getLoggedInEmployee();

        TextView ename = (TextView) findViewById(R.id.name);
        ename.setText("Name: " + loggedInUser.getFirst_name()+" "+loggedInUser.getLast_name());

        TextView eEmail = (TextView) findViewById(R.id.email);
        eEmail.setText("Email: "+ loggedInUser.getEmail());

        TextView eUsername = (TextView) findViewById(R.id.username);
        eUsername.setText("Email: "+ loggedInEmployee.getUsername());

        TextView ePassword = (TextView) findViewById(R.id.password);
        ePassword.setText("Password: **********");

        final TextView eClinic = (TextView) findViewById(R.id.clinicDisplay);

        if (!loggedInEmployee.isCompleted()) {
            eClinic.setText(" ");
        }else {
            databaseClinics.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot postSnap : dataSnapshot.getChildren()){
                        WalkInClinic product = postSnap.getValue(WalkInClinic.class);

                        if (product.getId().equals(loggedInEmployee.getClinic())) {
                            eClinic.setText("Clinic: "+product.getName());
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) { }
            });
        }

        Button selectClinicButton = (Button) findViewById(R.id.clinicSelect);
        if (!loggedInEmployee.isCompleted()) {
            selectClinicButton.setVisibility(View.VISIBLE);
        }else {
            selectClinicButton.setVisibility(View.INVISIBLE);
        }

    }
    public void selectClinicBtn(View view){

        //Toast.makeText(getApplicationContext(), loggedInUser.stringInfo(), Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(), loggedInEmployee.stringInfo(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, EmployeeSelectClinic.class);
        startActivity(intent);
    }

    public void backBtn(View view){
        finish(); //redirect to the welcome page
    }

}
