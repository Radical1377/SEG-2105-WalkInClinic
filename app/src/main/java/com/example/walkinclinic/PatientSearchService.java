package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientSearchService extends AppCompatActivity {

    private static Service selectedService = null;

    DatabaseReference databaseClinics = FirebaseDatabase.getInstance().getReference("walkinclinic");
    DatabaseReference databaseServices = FirebaseDatabase.getInstance().getReference("services");
    DatabaseReference databaseServicesClinics = FirebaseDatabase.getInstance().getReference("servicesClinic");


    private ListView listViewServices;
    private static List<Service> services;

    private static List<WalkInClinic> clinics;
    private static List<ServicesClinic> serviceClinic;

    private Context thisContext = this;
    private static Intent thisIntent = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_search_service);

        listViewServices = (ListView) findViewById(R.id.listServicesClinic);

        services = new ArrayList<>();
        clinics = new ArrayList<>();
        serviceClinic = new ArrayList<>();

        listViewServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedService = services.get(position);

//                databaseServicesClinics.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        serviceClinic.clear();
//                        for (DataSnapshot postSnap : dataSnapshot.getChildren()) {
//                            //Toast.makeText(getApplicationContext(), "Here", Toast.LENGTH_SHORT).show();
//
//                            ServicesClinic product = postSnap.getValue(ServicesClinic.class);
//                            if (selectedService.getId().equals(product.getService().getId())) {
//                                serviceClinic.add(product);
//                                Toast.makeText(getApplicationContext(), product.stringInfo(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//                startActivity(getIntent());
//                thisIntent = new Intent(thisContext, PatientFilteredClinics.class);
//                startActivity(thisIntent);

                //GET ALL OF THE CLINICS IN THE DB
                databaseClinics.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        clinics.clear();
                        for (DataSnapshot postSnap : dataSnapshot.getChildren()) {
                            WalkInClinic product = postSnap.getValue(WalkInClinic.class);
                                clinics.add(product);
                        }

                        startActivity(getIntent());
                        thisIntent = new Intent(thisContext, PatientFilteredClinics.class);
                        startActivity(thisIntent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseServices.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                services.clear();
                for (DataSnapshot postSnap : dataSnapshot.getChildren()){
                    Service product = postSnap.getValue(Service.class);
                    services.add(product);
                }

                ServiceList productsAdapter = new ServiceList(PatientSearchService.this, services);
                listViewServices.setAdapter(productsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    public static void setSelectedService(Service hi) {
        selectedService = hi;
    }

    public static List<WalkInClinic> getClinics() {
        return clinics;
    }

    public static Service getSelectedService() {
        return selectedService;
    }

    public static void setClinics(List<WalkInClinic> them) {
        clinics = them;
    }

    public void backBtn(View view) {
        finish();
    }

}
