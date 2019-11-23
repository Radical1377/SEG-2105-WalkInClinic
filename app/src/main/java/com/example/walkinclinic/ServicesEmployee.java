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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ServicesEmployee extends AppCompatActivity {

    DatabaseReference databaseServices;
    DatabaseReference databaseServicesClinic;
    Button buttonAddService;
    ListView listViewServices;
    List<Service> servicesAdmin;
    //List<Service> services;
    List<ServicesClinic> servicesClinics;

    private static User loggedInUser = LoginActivity.getLoggedInUser();
    private static Employee loggedInEmployee = LoginActivity.getLoggedInEmployee();

    private static String clinicId = loggedInEmployee.getClinic();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_employee);

        listViewServices = (ListView) findViewById(R.id.listServicesClinic);
        buttonAddService = (Button) findViewById(R.id.addService);

        // all the services from all clinics
        databaseServicesClinic = FirebaseDatabase.getInstance().getReference("servicesClinic");
        // all services availables
        databaseServices = FirebaseDatabase.getInstance().getReference("services");

        servicesClinics = new ArrayList<>();
        //services = new ArrayList<>();
        servicesAdmin = new ArrayList<>();

        //Toast.makeText(getApplicationContext(), clinicId, Toast.LENGTH_LONG).show();


        //adding an onclicklistener to button
        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addService();
            }
        });

        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                // HERE SHOULD BE SERVICES CLINIC
                ServicesClinic service = servicesClinics.get(i);
                //Toast.makeText(getApplicationContext(), service.stringInfo(), Toast.LENGTH_LONG).show();
                showUpdateDeleteDialog(service.getId(), service.getService().getName());
                return true;
            }
        });
    }

    // TO CHANGE
    @Override
    protected void onStart() {
        super.onStart();

        databaseServicesClinic.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                servicesClinics.clear();
                //services.clear();

                for (DataSnapshot postSnap : dataSnapshot.getChildren()){
                    ServicesClinic serviceC = postSnap.getValue(ServicesClinic.class);
                    if (serviceC.getClinicId().equals(clinicId)){
                        //Service service = serviceC.getService();

                        servicesClinics.add(serviceC);
                        //services.add(service);
                    }
                }

                ServicesClinicList productsAdapter = new ServicesClinicList(ServicesEmployee.this, servicesClinics);
                listViewServices.setAdapter(productsAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Toast.makeText(getApplicationContext(), clinicId, Toast.LENGTH_LONG).show();

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

    public void backBtn(View view){
        finish(); //redirect to the welcome page
    }

    public void addService(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_add_service_clinic, null);
        dialogBuilder.setView(dialogView);

        final Button buttonCancel = (Button) dialogView.findViewById(R.id.cancel);
        final ListView listServices = (ListView) dialogView.findViewById(R.id.listServicesClinic);

        ServiceList productsAdapter = new ServiceList(ServicesEmployee.this, servicesAdmin);

        listServices.setAdapter(productsAdapter);

        final AlertDialog b = dialogBuilder.create();
        b.show();

        listServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Service service = servicesAdmin.get(position);
                String serviceId = databaseServicesClinic.push().getKey();
                ServicesClinic serviceClinic = new ServicesClinic(serviceId, clinicId, service, 0);
                databaseServicesClinic.child(serviceId).setValue(serviceClinic);
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

    private void showUpdateDeleteDialog(final String serviceId, String serviceName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_delete_service_clinic, null);
        dialogBuilder.setView(dialogView);

        final Button submitButton = (Button) dialogView.findViewById(R.id.submit);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.deleteService);
        final Button buttonCancel = (Button) dialogView.findViewById(R.id.cancel);
        final EditText addRate = (EditText) dialogView.findViewById(R.id.addRate);



        dialogBuilder.setTitle(serviceName);
        final AlertDialog b = dialogBuilder.create();

        b.show();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // VALIDATE IF DATA IN IT
                if (addRate.getText().toString().replaceAll(" ","").equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill a rate", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        int rate = Integer.parseInt(addRate.getText().toString());
                        if (rate > 5 || rate <1){
                            Toast.makeText(getApplicationContext(), "Please enter a number from 1 to 5", Toast.LENGTH_LONG).show();
                        } else {
                            updateRate(serviceId, rate);
                            b.dismiss();
                        }
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Please enter a number from 1 to 5", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteService(serviceId);
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

    private void updateRate(String id, int rate) {
        DatabaseReference dR = databaseServicesClinic.child(id);

        dR.child("rate").setValue(rate);


        Toast.makeText(getApplicationContext(), "Service Updated", Toast.LENGTH_LONG).show();

//        DatabaseReference dR = databaseClinics.child(id);
//        //Toast.makeText(getApplicationContext(), "Clinic Updated", Toast.LENGTH_LONG).show();
//
//        dR.child("name").setValue(name);
//        dR.child("address").setValue(address);
//        dR.child("openingHour").setValue(open);
//        dR.child("closingHour").setValue(close);
//
////        WalkInClinic clinic = new WalkInClinic(id, name, address, open, close);
////        dR.setValue(clinic);

    }

    private boolean deleteService(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("servicesClinic").child(id);

        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Service Deleted", Toast.LENGTH_LONG).show();
        return true;
    }

}
