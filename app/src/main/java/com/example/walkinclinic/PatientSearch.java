package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

    WalkInClinic selectedClinic = null;
    Service selectedService = null;
    ListView listViewServices;
    DatabaseReference databaseServices;
    List<Service> servicesAdmin;

    private static List<WalkInClinic> clinics = null;

    private Context thisContext = this;
    private static Intent thisIntent = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_search);

        //clinics.clear();

        listViewServices = (ListView) findViewById(R.id.listServicesClinic);

        databaseServices = FirebaseDatabase.getInstance().getReference("services");

        servicesAdmin = new ArrayList<>();

        Button service = (Button) findViewById(R.id.selectService);

        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addService();
            }
        });


        final EditText searchName = (EditText) findViewById(R.id.name);
        final EditText searchAddress  = (EditText) findViewById(R.id.address);
        final EditText searchTime  = (EditText) findViewById(R.id.time);
        final RadioButton searchWeekDay = (RadioButton) findViewById(R.id.weekday);
        final RadioButton searchWeekEnd = (RadioButton) findViewById(R.id.weekend);

        final Button searchButton = (Button) findViewById(R.id.submitBtn);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = null;
                String address = null;
                String serviceId = null;
                String stringTime = null;
                String day = null;

                if (searchName != null) {
                    name = searchName.getText().toString().trim();
                }
                if (searchAddress != null ) {
                    address = searchAddress.getText().toString().trim();
                }
                if (selectedService != null ) {
                    serviceId = selectedService.getId();
                }
                if (searchTime != null ) {
                    stringTime = searchAddress.getText().toString().trim();
                }
                if (searchWeekDay != null) {
                    day = "weekday";
                }
                if (searchWeekEnd != null) {
                    day = "weekend";
                }


                Toast.makeText(getApplicationContext(), name+" "+address+" "+serviceId+" "+day+" "+stringTime, Toast.LENGTH_LONG).show();


                //clinics = null ; the set of clinics after you filter

                thisIntent = new Intent(thisContext, PatientFilteredClinics.class);
                startActivity(thisIntent);

            }
        });

    }


    protected void onStart() {
        super.onStart();

        databaseServices.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               servicesAdmin.clear();

               for (DataSnapshot postSnap : dataSnapshot.getChildren()){
                    Service service = postSnap.getValue(Service.class);

                    servicesAdmin.add(service);
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addService(){

        final Button serviceBtn = (Button) findViewById(R.id.selectService);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_patient_search_service, null);
        dialogBuilder.setView(dialogView);

        final Button buttonCancel = (Button) dialogView.findViewById(R.id.cancel);
        final ListView listServices = (ListView) dialogView.findViewById(R.id.listServicesClinic);

        ServiceList productsAdapter = new ServiceList(PatientSearch.this, servicesAdmin);

        listServices.setAdapter(productsAdapter);

        final AlertDialog b = dialogBuilder.create();
        b.show();

        listServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Service service = servicesAdmin.get(position);
                serviceBtn.setText(service.getName());
                selectedService = service;
                b.dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });

    }

    public void backBtn(View view) {
        finish();
    }

    public static List<WalkInClinic> getClinics(){
        return clinics;
    }


}
