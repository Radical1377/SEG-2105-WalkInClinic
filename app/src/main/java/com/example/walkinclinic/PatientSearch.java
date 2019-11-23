package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class PatientSearch extends AppCompatActivity {

    DatabaseReference databaseClinics;


    private static List<WalkInClinic> clinics = null;

    private Context thisContext = this;
    private static Intent thisIntent = null;


    //Search criteria

    private static String name = null;
    private static String address = null;
    private static int intTime = -1;
    private static WalkInClinic product = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_search);

        databaseClinics = FirebaseDatabase.getInstance().getReference("walkinclinic");
        clinics = new ArrayList<>();


        final EditText searchName = (EditText) findViewById(R.id.name);
        final EditText searchAddress  = (EditText) findViewById(R.id.address);
        final EditText searchTime  = (EditText) findViewById(R.id.time);
        final RadioButton searchWeekDay = (RadioButton) findViewById(R.id.weekday);
        final RadioButton searchWeekEnd = (RadioButton) findViewById(R.id.weekend);

        final Button searchButton = (Button) findViewById(R.id.submitBtn);

        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                boolean valid = true;                   //checking if searchtime is a number


                if (searchName != null) {
                    name = searchName.getText().toString().trim();
                }
                if (searchAddress != null ) {
                    address = searchAddress.getText().toString().trim();
                }
                if (searchTime != null && !searchTime.getText().toString().equals("")) {

                    try {
                        intTime = Integer.parseInt(searchTime.getText().toString());
                    } catch (Exception s) {
                        Toast.makeText(getApplicationContext(), "Hours must be a number.", Toast.LENGTH_LONG).show();
                        valid = false;                  //didn't enter a number for the hours
                    }
                }
                if (searchTime!=null && !searchTime.getText().toString().equals("") && !searchWeekDay.isChecked() && !searchWeekEnd.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Choose a day of the week (for the specified hours).", Toast.LENGTH_LONG).show();
                    valid = false;
                }
                if (searchWeekDay.isChecked() && !searchWeekEnd.isChecked() && searchTime.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Choose a time (for the specified week day).", Toast.LENGTH_LONG).show();
                    valid = false;
                }
                if (searchWeekEnd.isChecked() && !searchWeekDay.isChecked() && searchTime.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Choose a time (for the specified week day).", Toast.LENGTH_LONG).show();
                    valid = false;
                }

                //Getting all of the clinics from the db
                if (valid) {                                                                    //entered a number for hours
                    databaseClinics.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            clinics.clear();

                            for (DataSnapshot postSnap : dataSnapshot.getChildren()) {
                                product = postSnap.getValue(WalkInClinic.class);

                                //Address of the clinic
                                if (!searchAddress.getText().toString().equals("") && searchAddress!= null && product.get_address().equals(address) && !clinics.contains(product)) {
                                    clinics.add(product);
                                }
                                //Name of the clinic
                                else if (!searchName.getText().toString().equals("") && searchName!= null && product.get_name().equals(name) && !clinics.contains(product)) {
                                    clinics.add(product);
                                }
                                //Weekday in the interval
                                else if (!searchWeekDay.isChecked() && product.get_openingHourWeekDay() <= intTime && intTime <= product.get_closingHourWeekDay() && !clinics.contains(product)) {
                                    clinics.add(product);
                                }
                                //Weekend in the interval
                                else if (!searchWeekEnd.isChecked() && product.get_openingHourWeekEnd() <= intTime && intTime <= product.get_closingHourWeekEnd() && !clinics.contains(product)) {
                                    clinics.add(product);
                                }


                            }

                            thisIntent = new Intent(thisContext, PatientFilteredClinics.class);
                            startActivity(thisIntent);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }


            }
        });

    }


    protected void onStart() {
        super.onStart();

    }

    public static void setClinics(List<WalkInClinic> them) {
        clinics = them;
    }

    public void backBtn(View view) {
        finish();
    }

    public static List<WalkInClinic> getClinics(){
        return clinics;
    }


}
