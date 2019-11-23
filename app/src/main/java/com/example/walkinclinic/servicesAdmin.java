package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
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

public class servicesAdmin extends AppCompatActivity {

    DatabaseReference databaseServices;
    Button buttonAddService;
    ListView listViewServices;
    List<Service> services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_admin);

        listViewServices = (ListView) findViewById(R.id.listServices);
        buttonAddService = (Button) findViewById(R.id.addClinic);

        databaseServices = FirebaseDatabase.getInstance().getReference("services");

        services = new ArrayList<>();

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
                Service service = services.get(i);
                //Toast.makeText(getApplicationContext(), service.stringInfo(), Toast.LENGTH_LONG).show();
                showUpdateDeleteDialog(service.getId(), service.getName());
                return true;
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
                    Service service = postSnap.getValue(Service.class);

                    services.add(service);
                }

                ServiceList productsAdapter = new ServiceList(servicesAdmin.this, services);

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

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_add_services, null);
        dialogBuilder.setView(dialogView);

        final EditText addName = (EditText) dialogView.findViewById(R.id.addName);
        final EditText addStaff  = (EditText) dialogView.findViewById(R.id.addStaff);
        final Button buttonAdd = (Button) dialogView.findViewById(R.id.buttonAddService);
        final Button buttonCancel = (Button) dialogView.findViewById(R.id.cancel);

        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // VALIDATE IF DATA IN IT
                if (addName.getText().toString().replaceAll(" ","").equals("") || addStaff.getText().toString().replaceAll(" ","").equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        String id = databaseServices.push().getKey();
                        String name = addName.getText().toString().trim();
                        int staff = Integer.parseInt(addStaff.getText().toString());

                        // VALIDATE STAFF NUMBER
                        if (staff==0 || staff==1 || staff==2){

                            Service service = new Service(id, name, staff);
                            databaseServices.child(service.getId()).setValue(service);
                            b.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "Please enter a valid staff number\n(0=Doctor, 1=Nurse, 2=Other)", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Please enter a number for staff\n(0=Doctor, 1=Nurse, 2=Other)", Toast.LENGTH_LONG).show();
                    }

                }
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
        final View dialogView = inflater.inflate(R.layout.activity_update_delete_service, null);
        dialogBuilder.setView(dialogView);

        final EditText editName = (EditText) dialogView.findViewById(R.id.editName);
        final EditText editStaff  = (EditText) dialogView.findViewById(R.id.editStaff);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateService);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteService);
        final Button buttonCancel = (Button) dialogView.findViewById(R.id.cancel);


        dialogBuilder.setTitle(serviceName);
        final AlertDialog b = dialogBuilder.create();

        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // VALIDATE IF DATA IN IT
                if (editName.getText().toString().replaceAll(" ","").equals("") || editStaff.getText().toString().replaceAll(" ","").equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        String name = editName.getText().toString().trim();
                        int staff = Integer.parseInt(editStaff.getText().toString());
                        // VALIDATE STAFF NUMBER
                        if (staff==0 || staff==1 || staff==2){
                            updateService(serviceId, name, staff);
                            b.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "Please enter a valid staff number\n(0=Doctor, 1=Nurse, 2=Other)", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Please enter a number for staff\n(0=Doctor, 1=Nurse, 2=Other)", Toast.LENGTH_LONG).show();
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

    private void updateService(String id, String name, int staff) {
        DatabaseReference dR = databaseServices.child(id);

        Service service = new Service(id, name, staff);
        dR.setValue(service);

        Toast.makeText(getApplicationContext(), "Service Updated", Toast.LENGTH_LONG).show();
    }

    private boolean deleteService(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(id);

        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Service Deleted", Toast.LENGTH_LONG).show();
        return true;
    }
}
