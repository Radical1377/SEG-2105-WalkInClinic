package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;
import android.content.Intent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class EmployeeSelectClinic extends AppCompatActivity {

    DatabaseReference databaseClinics = FirebaseDatabase.getInstance().getReference("walkinclinic");
    DatabaseReference databaseEmployees  = FirebaseDatabase.getInstance().getReference("employees");
    ListView listViewClinics;
    List<WalkInClinic> clinics;


    private static Employee loggedInEmployee = null;
    private static User loggedInUser = null;

    private Context thisContext = this;
    private static Intent thisIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_select_clinic);

        loggedInEmployee = LoginActivity.getLoggedInEmployee();
        loggedInUser = LoginActivity.getLoggedInUser();

        listViewClinics = (ListView) findViewById(R.id.listClinics);

        clinics = new ArrayList<>();

        //adding an onclicklistener to button
        listViewClinics.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                WalkInClinic clinic = clinics.get(i);

                loggedInEmployee.setCompleted(true);
                loggedInEmployee.setClinic(clinic.getId());
                //Toast.makeText(getApplicationContext(), loggedInEmployee.stringInfo(), Toast.LENGTH_LONG).show();

                databaseEmployees.child(loggedInEmployee.getUsername()).setValue(loggedInEmployee);

                LoginActivity.setLoggedInEmployee(loggedInEmployee);
                LoginActivity.setLoggedInUser(loggedInUser);

                thisIntent = new Intent(thisContext, WelcomeEmployee.class);
                startActivity(thisIntent);

                return true;
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseClinics.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clinics.clear();
                for (DataSnapshot postSnap : dataSnapshot.getChildren()){
                    WalkInClinic product = postSnap.getValue(WalkInClinic.class);

                    clinics.add(product);
                }

                ClinicList productsAdapter = new ClinicList(EmployeeSelectClinic.this, clinics);
                listViewClinics.setAdapter(productsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }




}
