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

public class PatientSelectService extends AppCompatActivity {

    DatabaseReference databaseServices = FirebaseDatabase.getInstance().getReference("services");
    DatabaseReference databaseServicesClinics = FirebaseDatabase.getInstance().getReference("servicesClinic");
    private ListView listViewServices;
    private List<Service> services;
    private static Service selectedService = new Service();

    private Context thisContext = this;
    private Intent thisIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_select_service);

        listViewServices = (ListView) findViewById(R.id.listServicesClinic);

        services = new ArrayList<>();

        //adding an onclicklistener to button

        listViewServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedService = services.get(position);
                //Toast.makeText(getApplicationContext(), selectedService.stringInfo(), Toast.LENGTH_LONG).show();

                //startActivity(getIntent());
                thisIntent = new Intent(thisContext, PatientSearchByService.class);
                startActivity(thisIntent);
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

                ServiceList productsAdapter = new ServiceList(PatientSelectService.this, services);
                listViewServices.setAdapter(productsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    public static Service getSelectedService() {
        return selectedService;
    }
    public static void resetSelectedService(){
        selectedService = new Service();
    }

    public void backBtn(View view){
        finish(); //redirect to the welcome page
    }




}
