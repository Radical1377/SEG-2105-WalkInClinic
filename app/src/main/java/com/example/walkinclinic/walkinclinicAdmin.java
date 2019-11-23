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
                if (addName.getText().toString().replaceAll(" ","").equals("") || addOpeningHourWD.getText().toString().replaceAll(" ","").equals("")
                        || addClosingHourWD.getText().toString().replaceAll(" ","").equals("") || addOpeningHourWE.getText().toString().replaceAll(" ","").equals("")
                        || addClosingHourWE.getText().toString().replaceAll(" ","").equals("") || addAddress.getText().toString().replaceAll(" ","").equals("")) {
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

                        if (ohD<chD || ohE<chE) {
                            if (chD <= 24 || chE <= 24) {
                                if (ohD >= 0 || ohE >= 0) {
                                    WalkInClinic clinic = new WalkInClinic(id, name, address, ohD, chD, ohE, chE);
                                    databaseClinics.child(clinic.getId()).setValue(clinic);
                                    b.dismiss();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Opening hours must be bigger or equal to 0", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Closing hours must be less or equal to 24", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Closing hours  must be after opening hours", Toast.LENGTH_LONG).show();
                        }
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
                if (editName.getText().toString().replaceAll(" ","").equals("") || editOpeningHourWD.getText().toString().replaceAll(" ","").equals("") ||
                        editClosingHourWD.getText().toString().replaceAll(" ","").equals("") || editOpeningHourWE.getText().toString().replaceAll(" ","").equals("")
                        || editClosingHourWE.getText().toString().replaceAll(" ","").equals("") || editAddress.getText().toString().replaceAll(" ","").equals("")) {
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
                            if (ohD<chD && ohE<chE) {
                                updateClinic(clinicId, name, address, ohD, chD, ohE, chE);
                                b.dismiss();
                            } else {
                                Toast.makeText(getApplicationContext(), "Closing hours  must be after opening hours", Toast.LENGTH_LONG).show();
                            }
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

        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Clinic Deleted", Toast.LENGTH_LONG).show();
        return true;
    }

}
