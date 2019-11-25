package com.example.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class PatientBooking extends AppCompatActivity {

    User loggedInPatient = null;
    WalkInClinic selectedClinic = null;

    Service selectedService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_booking);

        loggedInPatient = LoginActivity.getLoggedInUser();
        selectedClinic = PatientClinicProfile.getSelectedClinic();

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



    }
}
