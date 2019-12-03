package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PatientClinicProfile extends AppCompatActivity {

    private static User loggedInUser = LoginActivity.getLoggedInUser();

    private static WalkInClinic selectedClinic = null;
    private static String clinicId = null;

    DatabaseReference databaseClinics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_clinic_profile);

        if (PatientAllClinics.getSelectedClinic()!=null ) {
            selectedClinic = PatientAllClinics.getSelectedClinic();
        }
        else if (PatientFilteredClinicByService.getSelectedClinic()!=null ) {
            selectedClinic = PatientFilteredClinicByService.getSelectedClinic();
        }
        else if (PatientFilteredClinics.getSelectedClinic()!=null ) {
            selectedClinic = PatientFilteredClinics.getSelectedClinic();
        }
        clinicId = selectedClinic.getId();

        //want to access walkinclinic tab in database

        PatientFilteredClinics.setSelectedClinic(null);
        PatientAllClinics.setSelectedClinic(null);

        PatientSearch.setClinics(null);

        PatientFilteredClinicByService.resetSelectedClinic();
        PatientFilteredClinicByService.resetSelectedService();


        databaseClinics = FirebaseDatabase.getInstance().getReference("walkinclinic");


    }
    protected void onStart() {
        super.onStart();
        //Toast.makeText(getApplicationContext(), clinicId, Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(), loggedInEmployee.getUsername(), Toast.LENGTH_LONG).show();

        //Toast.makeText(getApplicationContext(), selectedClinic.stringInfo(), Toast.LENGTH_LONG).show();

        databaseClinics.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnap : dataSnapshot.getChildren()) {
                    WalkInClinic product = postSnap.getValue(WalkInClinic.class);
                    assert product != null;
                    if (clinicId.equals(product.getId())) {
                        selectedClinic = product;
                    }
                }

                TextView nameText = (TextView) findViewById(R.id.clinicName);
                String name = "Name : " + selectedClinic.get_name();
                nameText.setText(name);

                TextView hoursText = (TextView) findViewById(R.id.clinicHours);
                String hours = "Clinic Hours : " + selectedClinic.fullHours();
                hoursText.setText(hours);

                TextView addressText = (TextView) findViewById(R.id.address);
                String add = "Address : " + selectedClinic.get_address();
                addressText.setText(add);

                TextView phoneText = (TextView) findViewById(R.id.phoneNumber);
                String phone = "Phone Number : " + selectedClinic.getPhoneNumber();
                phoneText.setText(phone);

                //try {
                TextView paymentText = (TextView) findViewById(R.id.payment);
                ArrayList<String> paymentList = selectedClinic.getPaymentMethod();
                String strPayment = "";
                //Toast.makeText(getApplicationContext(), paymentList.toString(), Toast.LENGTH_LONG).show();
                if (paymentList.isEmpty()) {
                    strPayment = "None";
                } else {
                    for (int i = 0; i < paymentList.size() - 1; i++) {
                        strPayment += paymentList.get(i) + ", ";
                        //Toast.makeText(getApplicationContext(), paymentList.get(i), Toast.LENGTH_LONG).show();
                    }
                    strPayment += paymentList.get(paymentList.size() - 1);
                }

                String payment = "Payment methods accepted : " + strPayment;
                paymentText.setText(payment);

                TextView insuranceText = (TextView) findViewById(R.id.insurance);
                ArrayList<String> insuranceList = selectedClinic.getInsuranceType();
                String strInsurance = "";
                if (insuranceList.isEmpty()) {
                    strInsurance = "None";
                } else {
                    for (int i = 0; i < insuranceList.size() - 1; i++) {
                        strInsurance += insuranceList.get(i) + ", ";
                    }
                    strInsurance += insuranceList.get(insuranceList.size() - 1);
                }
                String insurance = "Insurances accepted : " + strInsurance;
                insuranceText.setText(insurance);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        } );
    }

    public void backBtn(View view) {
        finish();

        Context thisContext = this;
        Intent thisIntent = new Intent(thisContext, WelcomePatient.class);
        startActivity(thisIntent);
    }

    public static void setSelectedClinic(WalkInClinic something) {
        selectedClinic = something;
    }

    public static WalkInClinic getSelectedClinic(){
        return selectedClinic;
    }

    public void bookBtn(View view) {
        Context thisContext = this;
        Intent thisIntent = new Intent(thisContext, PatientBooking.class);
        startActivity(thisIntent);
    }

    public void ratingBtn(View view) {
        Context thisContext = this;
        Intent thisIntent = new Intent(thisContext, PatientClinicRating.class);
        startActivity(thisIntent);
    }

    public void reviewsBtn(View view) {
        Context thisContext = this;
        Intent thisIntent = new Intent(thisContext, PatientClinicReviews.class);
        startActivity(thisIntent);
    }

}
