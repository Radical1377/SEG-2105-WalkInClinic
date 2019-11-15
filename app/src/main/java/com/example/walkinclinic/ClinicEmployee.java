package com.example.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClinicEmployee extends AppCompatActivity {

    private static WelcomeEmployee la = new WelcomeEmployee();
    private static User loggedInUser = la.getLoggedInUser(); // MODIFY FOR EMPLOYEE
    private static String clinicId;

    DatabaseReference databaseClinics;
    Button buttonModifyHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_employee);

        //clinicId = "-LscT7pYL7z2IYKEGj_u";

//        TextView nameText = (TextView) findViewById(R.id.clinicName);
//        String name = "Name : ADD NAME";
//        nameText.setText(name);
//
//        TextView hoursText = (TextView) findViewById(R.id.clinicHours);
//        String hours = "Clinic Hours : ADD CLINIC HOURS";
//        hoursText.setText(hours);
//
//        databaseClinics = FirebaseDatabase.getInstance().getReference("walkinclinic");
//
//        //adding an onclicklistener to button
//        buttonModifyHours.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                editClinicHours();
//            }
//        });



    }

    public void backBtn(View view){
        finish(); //redirect to the welcome page
    }


    private void editClinicHours(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_edit_clinic_hours_employee, null);
        dialogBuilder.setView(dialogView);

        final EditText editOpening = (EditText) dialogView.findViewById(R.id.editOpeningHour);
        final EditText editClosing  = (EditText) dialogView.findViewById(R.id.editClosingHour);
        final Button buttonSubmit = (Button) dialogView.findViewById(R.id.editClinicHours);
        final Button buttonCancel = (Button) dialogView.findViewById(R.id.cancel);


        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // VALIDATE IF DATA IN IT
                if (editOpening.getText().toString().equals("") || editClosing.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
                } else {
                    DatabaseReference dR = databaseClinics.child(clinicId);
                    int oh = Integer.parseInt(editOpening.getText().toString());
                    int ch = Integer.parseInt(editClosing.getText().toString());
                    dR.child("openingHour").setValue(oh);
                    dR.child("closingHour").setValue(ch);
                    b.dismiss();
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });



        Toast.makeText(getApplicationContext(), "Clinic Updated", Toast.LENGTH_LONG).show();
    }


    public void servicesClinic(View view){
//        Intent intent = new Intent(this, userAdmin.class);
//        startActivity(intent);
    }
}
