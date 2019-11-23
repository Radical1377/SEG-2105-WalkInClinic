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

import java.util.List;

public class PatientFilteredClinics extends AppCompatActivity {

    private static User loggedInPatient = null;

    ListView listViewClinics;
    List<WalkInClinic> clinics;

    private static WalkInClinic selectedClinic = null;


    private Context thisContext = this;
    private static Intent thisIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_filtered_clinics);

        loggedInPatient = LoginActivity.getLoggedInUser();

        listViewClinics = (ListView) findViewById(R.id.listClinics);

        clinics = PatientSearch.getClinics();

        if (clinics == null) {
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
