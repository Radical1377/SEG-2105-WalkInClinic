package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

public class PatientAllClinics extends AppCompatActivity {

    private static User loggedInPatient = null;

    DatabaseReference databaseClinics = FirebaseDatabase.getInstance().getReference("walkinclinic");

    ListView listViewClinics;
    List<WalkInClinic> clinics;

    private static WalkInClinic selectedClinic = null;


    private Context thisContext = this;
    private static Intent thisIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_all_clinics);

        loggedInPatient = LoginActivity.getLoggedInUser();

        listViewClinics = (ListView) findViewById(R.id.listClinics);

        clinics = new ArrayList<>();

        //adding an onclicklistener to button

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

                ClinicList productsAdapter = new ClinicList(PatientAllClinics.this, clinics);
                listViewClinics.setAdapter(productsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
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