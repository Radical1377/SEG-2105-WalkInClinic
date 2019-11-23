package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class PatientFilteredClinics extends AppCompatActivity {

    ListView listViewClinics;

    private static List<WalkInClinic> clinics;

    private static WalkInClinic selectedClinic = null;


    private Context thisContext = this;
    private static Intent thisIntent = null;

    DatabaseReference databaseServicesClinics = FirebaseDatabase.getInstance().getReference("servicesClinic");


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_filtered_clinics);

        listViewClinics = (ListView) findViewById(R.id.listClinics);

        // IF CLINICS COME SEARCH BY VALUES
        if (PatientSearch.getClinics() != null ) {
            clinics = PatientSearch.getClinics();
        }
        //IF CLINICS COMES FROM SEARCH BY SERVICE
        else if (PatientSearchService.getClinics() != null) {
            clinics = PatientSearchService.getClinics();
        }


        if (clinics.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Could not find such clinics. Consider another search.", Toast.LENGTH_LONG).show();
        } else {

            listViewClinics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectedClinic = clinics.get(position);

                    startActivity(getIntent());
                    thisIntent = new Intent(thisContext, PatientClinicProfile.class);
                    startActivity(thisIntent);
                }
            });
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (clinics != null ) {
            ClinicList productsAdapter = new ClinicList(PatientFilteredClinics.this, clinics);
            listViewClinics.setAdapter(productsAdapter);
        }

    }

    public void backBtn(View view){
        finish();
    }

    public static WalkInClinic getSelectedClinic() {
        return selectedClinic;
    }

    public static void setSelectedClinic(WalkInClinic something) {
        selectedClinic = something;
    }
}
