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
    List<Service> services;
    List<ServicesClinic> servicesClinics;
    String clinicId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_employee);

        listViewServices = (ListView) findViewById(R.id.listServicesClinic);
        buttonAddService = (Button) findViewById(R.id.addService);

        // all the services from all clinics
        databaseServices = FirebaseDatabase.getInstance().getReference("servicesClinic");
        // all the services from specific clinic
        databaseServicesClinic = FirebaseDatabase.getInstance().getReference("servicesClinic").child(clinicId);

        services = new ArrayList<>();
        servicesClinics = new ArrayList<>();

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
                showDeleteDialog(service.getId(), service.getService().getName());
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

                for (DataSnapshot postSnap : dataSnapshot.getChildren()){
                    ServicesClinic service = postSnap.getValue(ServicesClinic.class);

                    servicesClinics.add(service);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseServicesClinic.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                services.clear();

                for (DataSnapshot postSnap : dataSnapshot.getChildren()){
                    Service service = postSnap.getValue(ServicesClinic.class).getService();

                    services.add(service);
                }

                ServiceList productsAdapter = new ServiceList(ServicesEmployee.this, services);

                listViewServices.setAdapter(productsAdapter);
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

        // TO CHANGE WITH CLINIC ID

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_add_service_clinic, null);
        dialogBuilder.setView(dialogView);

        //final EditText addName = (EditText) dialogView.findViewById(R.id.addName);
        //final EditText addStaff  = (EditText) dialogView.findViewById(R.id.addStaff);
        final Button buttonAdd = (Button) dialogView.findViewById(R.id.addService);
        final Button buttonCancel = (Button) dialogView.findViewById(R.id.cancel);

        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // VALIDATE IF DATA IN IT
//                if (addName.getText().toString().equals("") || addStaff.getText().toString().equals("")) {
//                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
//                } else {
                    //String id = databaseServices.push().getKey();
                    //String name = addName.getText().toString().trim();
                    //int staff = Integer.parseInt(addStaff.getText().toString());

                    // VALIDATE STAFF NUMBER
                    //if (staff==0 || staff==1 || staff==2){
                        //Service service = new Service(id, name, staff);
                       // databaseServices.child(service.getId()).setValue(service);
                       // b.dismiss();
                    //} else {
                        //Toast.makeText(getApplicationContext(), "Please enter a valid staff number\n(0=Doctor, 1=Nurse, 2=Other)", Toast.LENGTH_LONG).show();
                   // }
                //}
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });

    }

    private void showDeleteDialog(final String serviceId, String serviceName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_delete_service_clinic, null);
        dialogBuilder.setView(dialogView);

        final Button buttonDelete = (Button) dialogView.findViewById(R.id.deleteService);
        final Button buttonCancel = (Button) dialogView.findViewById(R.id.cancel);


        dialogBuilder.setTitle(serviceName);
        final AlertDialog b = dialogBuilder.create();

        b.show();

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

    private boolean deleteService(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("servicesClinic").child(id);

        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Service Deleted", Toast.LENGTH_LONG).show();
        return true;
    }

}
