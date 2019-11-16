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


public class walkinclinicAdmin extends AppCompatActivity {

    DatabaseReference databaseClinics;
    Button buttonAddClinic;
    ListView listViewClinics;
    List<WalkInClinic> clinics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkinclinic_admin);

        listViewClinics = (ListView) findViewById(R.id.listClinics);
        buttonAddClinic = (Button) findViewById(R.id.addClinic);

        databaseClinics = FirebaseDatabase.getInstance().getReference("walkinclinic");

        clinics = new ArrayList<>();

        //adding an onclicklistener to button
        buttonAddClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addClinic();
            }
        });

        listViewClinics.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                WalkInClinic clinic = clinics.get(i);
                //Toast.makeText(getApplicationContext(), clinic.stringInfo(), Toast.LENGTH_LONG).show();
                showUpdateDeleteDialog(clinic.getId(), clinic.get_name());
                return true;
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
//                long numdata = dataSnapshot.getChildrenCount();
//                WalkInClinic oldclinic = dataSnapshot.getValue(WalkInClinic.class);
//                clinics.add(oldclinic);
//                for (int i =1; i <= numdata; i++){
//                    WalkInClinic clinic = dataSnapshot.child(String.valueOf(i)).getValue(WalkInClinic.class);
//                    clinics.add(clinic);
//                }


//                WalkInClinic p = new WalkInClinic("lol", "lol", 8, 9);
//                WalkInClinic p2 = new WalkInClinic("lol", "lol", 8, 9);
//                WalkInClinic p3 = new WalkInClinic("lol", "lol", 8, 9);
//                WalkInClinic p4 = new WalkInClinic("lol", "lol", 8, 9);
//                WalkInClinic p5 = new WalkInClinic("lol", "lol", 8, 9);
//
//
//                clinics.add(p);
//                clinics.add(p2);
//                clinics.add(p3);
//                clinics.add(p4);
//                clinics.add(p5);

//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                    WalkInClinic clinic = postSnapshot.getValue(WalkInClinic.class);
//                }

                for (DataSnapshot postSnap : dataSnapshot.getChildren()){
                    WalkInClinic product = postSnap.getValue(WalkInClinic.class);

                    clinics.add(product);
                }

                ClinicList productsAdapter = new ClinicList(walkinclinicAdmin.this, clinics);

                listViewClinics.setAdapter(productsAdapter);
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
    public void addClinic(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_add_clinic, null);
        dialogBuilder.setView(dialogView);

        final EditText addName = (EditText) dialogView.findViewById(R.id.addName);
        final EditText addOpeningHourWD  = (EditText) dialogView.findViewById(R.id.addOpeningHourWD);
        final EditText addClosingHourWD  = (EditText) dialogView.findViewById(R.id.addClosingHourWD);
        final EditText addOpeningHourWE  = (EditText) dialogView.findViewById(R.id.addOpeningHourWE);
        final EditText addClosingHourWE  = (EditText) dialogView.findViewById(R.id.addClosingHourWE);
        final EditText addAddress = (EditText) dialogView.findViewById(R.id.addAddress);
        final Button buttonAdd = (Button) dialogView.findViewById(R.id.buttonAddClinic);
        final Button buttonCancel = (Button) dialogView.findViewById(R.id.cancel);


        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // VALIDATE IF DATA IN IT
                if (addName.getText().toString().equals("") || addOpeningHourWD.getText().toString().equals("") || addClosingHourWD.getText().toString().equals("") || addOpeningHourWE.getText().toString().equals("") || addClosingHourWE.getText().toString().equals("") || addAddress.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        String id = databaseClinics.push().getKey();
                        String name = addName.getText().toString().trim();
                        String address = addAddress.getText().toString().trim();
                        int ohD = Integer.parseInt(addOpeningHourWD.getText().toString());
                        int chD = Integer.parseInt(addClosingHourWD.getText().toString());
                        int ohE = Integer.parseInt(addOpeningHourWE.getText().toString());
                        int chE = Integer.parseInt(addClosingHourWE.getText().toString());
                        WalkInClinic clinic = new WalkInClinic(id, name, address, ohD, chD, ohE, chE);
                        databaseClinics.child(clinic.getId()).setValue(clinic);
                        b.dismiss();
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Please enter numbers from 0 to 24 for Opening and Closing hours", Toast.LENGTH_LONG).show();
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


        //Toast.makeText(this, "Clinic added", Toast.LENGTH_LONG).show();

    }

    private void showUpdateDeleteDialog(final String clinicId, String clinicName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_update_delete_clinic, null);
        dialogBuilder.setView(dialogView);

        final EditText editName = (EditText) dialogView.findViewById(R.id.editName);
        final EditText editOpeningHourWD  = (EditText) dialogView.findViewById(R.id.editOpeningHourWD);
        final EditText editClosingHourWD  = (EditText) dialogView.findViewById(R.id.editClosingHourWD);
        final EditText editOpeningHourWE  = (EditText) dialogView.findViewById(R.id.editOpeningHourWE);
        final EditText editClosingHourWE  = (EditText) dialogView.findViewById(R.id.editClosingHourWE);
        final EditText editAddress = (EditText) dialogView.findViewById(R.id.editClinic);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateClinic);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteClinic);
        final Button buttonCancel = (Button) dialogView.findViewById(R.id.cancel);


        // TRY TO FILL ALL THE STUFF ALREADY SO THEY JUST HAVE TO MODIFY
//        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("walkinclinic").child(clinicId);
//        dR.addValueEventListener(new ValueEventListener() {
//            @Override
//            public WalkInClinic onDataChange(DataSnapshot dataSnapshot) {
//                WalkInClinic clinic = dataSnapshot.getValue(WalkInClinic.class);
//                return clinic;
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//        Toast.makeText(getApplicationContext(), clinic.stringInfo(), Toast.LENGTH_LONG).show();




        dialogBuilder.setTitle(clinicName);
        final AlertDialog b = dialogBuilder.create();

        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // VALIDATE IF DATA IN IT
                if (editName.getText().toString().equals("") || editOpeningHourWD.getText().toString().equals("") ||
                        editClosingHourWD.getText().toString().equals("") || editOpeningHourWE.getText().toString().equals("")
                        || editClosingHourWE.getText().toString().equals("") || editAddress.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
                } else {

                    try {
                        String name = editName.getText().toString().trim();
                        String address = editAddress.getText().toString().trim();
                        int ohD = Integer.parseInt(editOpeningHourWD.getText().toString());
                        int chD = Integer.parseInt(editClosingHourWD.getText().toString());
                        int ohE = Integer.parseInt(editOpeningHourWE.getText().toString());
                        int chE = Integer.parseInt(editClosingHourWE.getText().toString());
                        if (ohD > 24 || ohD <0 || chD > 24 || chD <0 || ohE > 24 || ohE <0 || chE > 24 || chE <0 ){
                            Toast.makeText(getApplicationContext(), "Please enter numbers from 0 to 24", Toast.LENGTH_LONG).show();
                        } else {
                            updateClinic(clinicId, name, address, ohD, chD, ohE, chE);
                            b.dismiss();
                        }

                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Please enter numbers from 0 to 24 for all Opening and Closing hours", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteClinic(clinicId);
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

    private void updateClinic(String id, String name, String address, int openD, int closeD, int openE, int closeE) {
        DatabaseReference dR = databaseClinics.child(id);
        //Toast.makeText(getApplicationContext(), "Clinic Updated", Toast.LENGTH_LONG).show();

        dR.child("name").setValue(name);
        dR.child("address").setValue(address);
        dR.child("openingHourWeekDay").setValue(openD);
        dR.child("closingHourWeekDay").setValue(closeD);
        dR.child("openingHourWeekEnd").setValue(openE);
        dR.child("closingHourWeekEnd").setValue(closeE);

//        WalkInClinic clinic = new WalkInClinic(id, name, address, open, close);
//        dR.setValue(clinic);

        Toast.makeText(getApplicationContext(), "Clinic Updated", Toast.LENGTH_LONG).show();

    }

    private boolean deleteClinic(String id) {
        //Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("walkinclinic").child(id);

//        DatabaseReference drEmployee = FirebaseDatabase.getInstance().getReference("employees");
//
//        //.child("clinicId").child(id)
//
//        drEmployee.child("clinicId").equalTo(id).addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        drEmployee.child("clinicId").child(dataSnapshot.getKey()).setValue(null);
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                    }
//                });
//        drEmployee.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot postSnap : dataSnapshot.getChildren()){
//                    postSnap.getRef().removeValue();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//             }
//        }
//        );

        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Clinic Deleted", Toast.LENGTH_LONG).show();
        return true;
    }

}
