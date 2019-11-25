package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientFilteredClinicByService extends AppCompatActivity {

    ListView listViewClinics;

    private static List<WalkInClinic> clinics;
    private static List<ServicesClinic> serviceClinic;


    private static WalkInClinic selectedClinic = null;
    private static Service selectedService;

    DatabaseReference databaseServicesClinics;
    DatabaseReference databaseClinics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_filtered_clinic_by_service);

        selectedService = PatientSearchByService.getService();
        databaseServicesClinics = FirebaseDatabase.getInstance().getReference("servicesClinic");
        databaseClinics = FirebaseDatabase.getInstance().getReference("walkinclinic");

        listViewClinics = (ListView) findViewById(R.id.listClinics);

        serviceClinic = new ArrayList<>();
        clinics = new ArrayList<>();

        listViewClinics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedClinic = clinics.get(position);

                Intent intent = new Intent(getApplicationContext(),PatientClinicProfile.class);
                startActivity(intent);

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        databaseServicesClinics.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Toast.makeText(getApplicationContext(), "SERVICE CLINICS", Toast.LENGTH_LONG).show();

                for (DataSnapshot postSnap : dataSnapshot.getChildren()) {
                    ServicesClinic product = postSnap.getValue(ServicesClinic.class);
                    if (selectedService.getId().equals(product.getService().getId()))
                        serviceClinic.add(product);
                }

                databaseClinics.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Toast.makeText(getApplicationContext(), serviceClinic.size(), Toast.LENGTH_LONG).show();

                        for (DataSnapshot postSnap : dataSnapshot.getChildren()) {
                            WalkInClinic product = postSnap.getValue(WalkInClinic.class);
                            for (int i = 0; i < serviceClinic.size(); i++) {
                                if (serviceClinic.get(i).getClinicId().equals(product.getId())){
                                    clinics.add(product);
                                }
                            }
                        }

                        ClinicList productsAdapter = new ClinicList(PatientFilteredClinicByService.this, clinics);
                        listViewClinics.setAdapter(productsAdapter);
                        //Toast.makeText(getApplicationContext(), "DONE 2", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    public static WalkInClinic getSelectedClinic() {
        return selectedClinic;
    }

    public static void resetSelectedClinic(){
        selectedClinic = null;
    }

    public static void resetSelectedService(){
        selectedService = null;
    }

    public void backBtn(View view){
        finish();
    }
}
