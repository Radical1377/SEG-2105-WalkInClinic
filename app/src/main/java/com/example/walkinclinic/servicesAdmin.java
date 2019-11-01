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

    // ADDING WORKS
    public void addService(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_add_services, null);
        dialogBuilder.setView(dialogView);

        final EditText addName = (EditText) dialogView.findViewById(R.id.addName);
        final EditText addStaff  = (EditText) dialogView.findViewById(R.id.addStaff);
        final Button buttonAdd = (Button) dialogView.findViewById(R.id.buttonAddClinic);
        final Button buttonCancel = (Button) dialogView.findViewById(R.id.cancel);

        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = addName.getText().toString().trim();
                int staff = Integer.parseInt(addStaff.getText().toString());
                Service service = new Service(name, staff);
                databaseServices.child(service.getName()).setValue(service);
                b.dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });

        //Toast.makeText(this, "Clinic added", Toast.LENGTH_LONG).show();

    }

    private void showUpdateDeleteDialog(final String serviceId, String serviceName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_update_delete_service, null);
        dialogBuilder.setView(dialogView);

        final EditText editName = (EditText) dialogView.findViewById(R.id.editName);
        final EditText editStaff  = (EditText) dialogView.findViewById(R.id.editStaff);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateClinic);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteClinic);
        final Button buttonCancel = (Button) dialogView.findViewById(R.id.cancel);


        dialogBuilder.setTitle(serviceName);
        final AlertDialog b = dialogBuilder.create();

        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString().trim();

                int staff = Integer.parseInt(editStaff.getText().toString());
                if (!TextUtils.isEmpty(name)) {
                    updateService(serviceId, name, staff);
                    b.dismiss();
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

    // UPDATING DOESN'T WORK
    private void updateService(String id, String name, int staff) {
        DatabaseReference dR = databaseServices.child(id);

        Service service = new Service(id, name, staff);
        dR.setValue(service);

        Toast.makeText(getApplicationContext(), "Service Updated", Toast.LENGTH_LONG).show();
    }

    // DELETING DOESN'T WORK
    private boolean deleteService(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(id);

        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Service Deleted", Toast.LENGTH_LONG).show();
        return true;
    }
}
