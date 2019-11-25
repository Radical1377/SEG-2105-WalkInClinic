package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientBooking extends AppCompatActivity {

    User loggedInPatient = null;
    WalkInClinic selectedClinic = null;

    Button buttonAddService;
    ListView listViewServices;
    List<Service> servicesAdmin;

    DatabaseReference databaseServices;

    Service selectedService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_booking);

        loggedInPatient = LoginActivity.getLoggedInUser();
        selectedClinic = PatientClinicProfile.getSelectedClinic();
        servicesAdmin = new ArrayList<>();
        buttonAddService = (Button) findViewById(R.id.serviceSelection);
        databaseServices = FirebaseDatabase.getInstance().getReference("services");

        TextView nameText = (TextView) findViewById(R.id.clinicName);
        String name = "Clinic : " + selectedClinic.get_name();
        nameText.setText(name);

        TextView usernameText = (TextView) findViewById(R.id.username);
        String userName = "Username : " + loggedInPatient.getUsername();
        usernameText.setText(userName);

        final EditText startTime = (EditText) findViewById(R.id.startTime);
        final EditText endTime  = (EditText) findViewById(R.id.endTime);
        final RadioButton searchWeekDay = (RadioButton) findViewById(R.id.weekday);
        final RadioButton searchWeekEnd = (RadioButton) findViewById(R.id.weekend);


        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addService();
            }
        });

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
        }
        );


    }

    public void addService(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_add_service_clinic, null);
        dialogBuilder.setView(dialogView);

        final Button buttonCancel = (Button) dialogView.findViewById(R.id.cancel);
        final ListView listServices = (ListView) dialogView.findViewById(R.id.listServicesClinic);

        ServiceList productsAdapter = new ServiceList(PatientBooking.this, servicesAdmin);

        listServices.setAdapter(productsAdapter);

        final AlertDialog b = dialogBuilder.create();
        b.show();

        listServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedService = servicesAdmin.get(position);
                buttonAddService.setText(selectedService.getName());
                b.dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedClinic = null;
                buttonAddService.setText("Select a service");
                b.dismiss();
            }
        });

    }

    public void backBtn(View view) {
        finish();
    }
}
