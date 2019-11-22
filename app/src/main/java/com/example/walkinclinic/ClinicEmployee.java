package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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

public class ClinicEmployee extends AppCompatActivity {

    //private static User loggedInUser = LoginActivity.getLoggedInUser();
    private static Employee loggedInEmployee = null;

    private static String clinicId = null;
    private static WalkInClinic clinic = null;

    DatabaseReference databaseClinics;
    Button buttonModifyProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_employee);

        loggedInEmployee = LoginActivity.getLoggedInEmployee();
        clinicId = loggedInEmployee.getClinic();
        //want to access walkinclinic tab in database
        databaseClinics = FirebaseDatabase.getInstance().getReference("walkinclinic");

        buttonModifyProfile = (Button) findViewById(R.id.editClinicProfile);

        //adding an onclicklistener to button
        buttonModifyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editClinicHours();
            }
        });

    }
    protected void onStart() {
        super.onStart();
        //Toast.makeText(getApplicationContext(), clinicId, Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(), loggedInEmployee.getUsername(), Toast.LENGTH_LONG).show();


        databaseClinics.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnap : dataSnapshot.getChildren()) {
                    WalkInClinic product = postSnap.getValue(WalkInClinic.class);
                    assert product != null;
                    if (clinicId.equals(product.getId())) {
                        clinic = product;
                    }
                }
                TextView nameText = (TextView) findViewById(R.id.clinicName);
                String name = "Name : " + clinic.get_name();
                nameText.setText(name);

                TextView hoursText = (TextView) findViewById(R.id.clinicHours);
                String hours = "Clinic Hours : " + clinic.fullHours();
                hoursText.setText(hours);

                TextView addressText = (TextView) findViewById(R.id.address);
                String add = "Address : " + clinic.get_address();
                addressText.setText(add);

                TextView phoneText = (TextView) findViewById(R.id.phoneNumber);
                String phone = "Phone Number : " + clinic.getPhoneNumber();
                phoneText.setText(phone);

                //try {
                    TextView paymentText = (TextView) findViewById(R.id.payment);
                    ArrayList<String> paymentList = clinic.getPaymentMethod();
                    String strPayment = "";
                    //Toast.makeText(getApplicationContext(), paymentList.toString(), Toast.LENGTH_LONG).show();
                if (paymentList.isEmpty()){
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
                    ArrayList<String> insuranceList = clinic.getInsuranceType();
                    String strInsurance = "";
                if(insuranceList.isEmpty()){
                    strInsurance = "None";
                } else {
                    for (int i=0;i<insuranceList.size()-1;i++){
                        strInsurance += insuranceList.get(i)+", ";
                    }
                    strInsurance += insuranceList.get(insuranceList.size()-1);
                }
                    String insurance = "Insurances accepted : " + strInsurance;
                    insuranceText.setText(insurance);
//                } catch (Exception e){
//                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
//                }
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

    private void editClinicHours(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_edit_clinic_profile, null);
        dialogBuilder.setView(dialogView);

        // EDIT TEXTS
        final EditText editName = (EditText) dialogView.findViewById(R.id.editName);
        final EditText editOpeningHourWD  = (EditText) dialogView.findViewById(R.id.editOpeningHourWD);
        final EditText editClosingHourWD = (EditText) dialogView.findViewById(R.id.editClosingHourWD);
        final EditText editOpeningHourWE  = (EditText) dialogView.findViewById(R.id.editOpeningHourWE);
        final EditText editClosingHourWE = (EditText) dialogView.findViewById(R.id.editClosingHourWE);
        final EditText editAddress  = (EditText) dialogView.findViewById(R.id.editAddress);
        final EditText editPhone = (EditText) dialogView.findViewById(R.id.editPhone);

        // INSURANCE
        final CheckBox publicInsurance = (CheckBox) dialogView.findViewById(R.id.publicInsurance);
        final CheckBox privateInsurance = (CheckBox) dialogView.findViewById(R.id.privateInsurance);

        // PAYMENT METHODS
        final CheckBox debitBox = (CheckBox) dialogView.findViewById(R.id.debit);
        final CheckBox creditBox = (CheckBox) dialogView.findViewById(R.id.credit);
        final CheckBox paypalBox = (CheckBox) dialogView.findViewById(R.id.paypal);
        final CheckBox cashBox = (CheckBox) dialogView.findViewById(R.id.cash);
        final CheckBox eTransferBox = (CheckBox) dialogView.findViewById(R.id.eTransfer);
        final CheckBox checksBox = (CheckBox) dialogView.findViewById(R.id.check);

        // BUTTONS
        final Button buttonSubmit = (Button) dialogView.findViewById(R.id.editClinicHours);
        final Button buttonCancel = (Button) dialogView.findViewById(R.id.cancel);

        dialogBuilder.setTitle(clinic.get_name());
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // VALIDATE IF DATA IN IT
                if (editName.getText().toString().equals("") || editOpeningHourWD.getText().toString().equals("") ||
                        editClosingHourWD.getText().toString().equals("") || editOpeningHourWE.getText().toString().equals("") ||
                        editClosingHourWE.getText().toString().equals("") || editAddress.getText().toString().equals("") ||
                        editPhone.getText().toString().equals("") || (!publicInsurance.isChecked() && !privateInsurance.isChecked()) ||
                        (!debitBox.isChecked() && !creditBox.isChecked() && !paypalBox.isChecked() && !cashBox.isChecked() &&
                                !eTransferBox.isChecked() && !checksBox.isChecked())) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        // EDIT TEXTS
                        String name = editName.getText().toString().trim();
                        String address = editAddress.getText().toString().trim();
                        String phone = editPhone.getText().toString().trim();
                        int ohD = Integer.parseInt(editOpeningHourWD.getText().toString());
                        int chD = Integer.parseInt(editClosingHourWD.getText().toString());
                        int ohE = Integer.parseInt(editOpeningHourWE.getText().toString());
                        int chE = Integer.parseInt(editClosingHourWE.getText().toString());

                        // INSURANCE
                        ArrayList<String> insuranceList = new ArrayList<String>();
                        if (publicInsurance.isChecked()){
                            insuranceList.add("Public");
                        }
                        if (privateInsurance.isChecked()){
                            insuranceList.add("Private");
                        }
                        //Toast.makeText(getApplicationContext(), insuranceList.toString(), Toast.LENGTH_LONG).show();


                        // PAYMENT METHODS
                        ArrayList<String> paymentList = new ArrayList<String>();
                        if (debitBox.isChecked()){
                            paymentList.add("Debit");
                        }
                        if (creditBox.isChecked()){
                            paymentList.add("Credit");
                        }
                        if (paypalBox.isChecked()){
                            paymentList.add("PayPal");
                        }
                        if (cashBox.isChecked()){
                            paymentList.add("Cash");
                        }
                        if (eTransferBox.isChecked()){
                            paymentList.add("eTransfer");
                        }
                        if (checksBox.isChecked()){
                            paymentList.add("Checks");
                        }
                        //Toast.makeText(getApplicationContext(), paymentList.toString(), Toast.LENGTH_LONG).show();


                        if (ohD > 24 || ohD <0 || chD > 24 || chD <0 || ohE > 24 || ohE <0 || chE > 24 || chE <0 ){
                            Toast.makeText(getApplicationContext(), "Please enter numbers from 0 to 24", Toast.LENGTH_LONG).show();
                        } else if(!(ohD<chD) || !(ohE<chE)){
                            Toast.makeText(getApplicationContext(), "Opening hours need to be smaller than Closing hours", Toast.LENGTH_LONG).show();
                        } else {
                            updateClinic(clinicId, name, address, phone, ohD, chD, ohE, chE, insuranceList, paymentList);
                            b.dismiss();
                            Toast.makeText(getApplicationContext(), "Clinic Updated", Toast.LENGTH_LONG).show();
                            //startActivity(getIntent());
                            //finish();
                        }
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

                        Toast.makeText(getApplicationContext(), "Please enter numbers from 0 to 24 for all Opening and Closing hours", Toast.LENGTH_LONG).show();
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

    private void updateClinic(String id, String name, String address, String phone, int openD, int closeD, int openE, int closeE, ArrayList<String> insuranceList, ArrayList<String> paymentList) {
        DatabaseReference dR = databaseClinics.child(id);
        //Toast.makeText(getApplicationContext(), "Clinic Updated", Toast.LENGTH_LONG).show();

        dR.child("_name").setValue(name);
        dR.child("_address").setValue(address);
        dR.child("_openingHourWeekDay").setValue(openD);
        dR.child("_closingHourWeekDay").setValue(closeD);
        dR.child("_openingHourWeekEnd").setValue(openE);
        dR.child("_closingHourWeekEnd").setValue(closeE);
        dR.child("phoneNumber").setValue(phone);
        dR.child("insuranceType").setValue(insuranceList);
        dR.child("paymentMethod").setValue(paymentList);

//        WalkInClinic clinic = new WalkInClinic(id, name, address, open, close);
//        dR.setValue(clinic);

        Toast.makeText(getApplicationContext(), "Clinic Updated", Toast.LENGTH_LONG).show();

    }


    public void servicesClinic(View view){
        Intent intent = new Intent(this, ServicesEmployee.class);
        startActivity(intent);
    }
}
