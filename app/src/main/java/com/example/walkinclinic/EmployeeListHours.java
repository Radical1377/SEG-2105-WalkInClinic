package com.example.walkinclinic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EmployeeListHours extends AppCompatActivity {
    private static User loggedInUser = null;
    private static Employee loggedInEmployee = null;


    DatabaseReference databaseEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_hours_emp);

        loggedInEmployee = LoginActivity.getLoggedInEmployee();
        loggedInUser = LoginActivity.getLoggedInUser();

        databaseEmployees = FirebaseDatabase.getInstance().getReference("employees");

        final String username = loggedInUser.getUsername();

        final EditText _openHoursMonday = (EditText) findViewById(R.id.openMondayHours);
        final EditText _openHoursTuesday = (EditText) findViewById(R.id.openTuesdayHours);
        final EditText _openHoursWednesday = (EditText) findViewById(R.id.openWednesdayHours);
        final EditText _openHoursThursday = (EditText) findViewById(R.id.openThursdayHours);
        final EditText _openHoursFriday = (EditText) findViewById(R.id.openFridayHours);
        final EditText _openHoursSat = (EditText) findViewById(R.id.openSaturdayHours);
        final EditText _openHoursSun = (EditText) findViewById(R.id.openSundayHours);

        //Edit hours for days of the week (close)
        final EditText _closeHoursMonday = (EditText) findViewById(R.id.closeMondayHours);
        final EditText _closeHoursTuesday = (EditText) findViewById(R.id.closeTuesdayHours);
        final EditText _closeHoursWednesday = (EditText) findViewById(R.id.closeWednesdayHours);
        final EditText _closeHoursThursday = (EditText) findViewById(R.id.closeThursdayHours);
        final EditText _closeHoursFriday = (EditText) findViewById(R.id.closeFridayHours);
        final EditText _closeHoursSat = (EditText) findViewById(R.id.closeSaturdayHours);
        final EditText _closeHoursSun = (EditText) findViewById(R.id.closeSundayHours);

        Button setMonBtn =(Button) findViewById(R.id.setMonday);
        Button setTuesBtn =(Button) findViewById(R.id.setTuesday);
        Button setWedBtn =(Button) findViewById(R.id.setWednesday);
        Button setThurBtn =(Button) findViewById(R.id.setThursday);
        Button setFriBtn =(Button) findViewById(R.id.setFriday);
        Button setSatBtn =(Button) findViewById(R.id.setSaturday);
        Button setSunBtn =(Button) findViewById(R.id.setSunday);

        setMonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double open=Double.parseDouble(_openHoursMonday.getText().toString().trim());
                double close=Double.parseDouble(_closeHoursMonday.getText().toString().trim());

                //changeHours("Monday", open, close, username); //call to change hours for this day
            }
        });
        setTuesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double open=Double.parseDouble(_openHoursTuesday.getText().toString().trim());
                double close=Double.parseDouble(_closeHoursTuesday.getText().toString().trim());

                //changeHours("Tuesday", open, close, username); //call to change hours for this day
            }
        });
        setWedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double open=Double.parseDouble(_openHoursWednesday.getText().toString().trim());
                double close=Double.parseDouble(_closeHoursWednesday.getText().toString().trim());

                //changeHours("Wednesday", open, close, username); //call to change hours for this day
            }
        });
        setThurBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double open=Double.parseDouble(_openHoursThursday.getText().toString().trim());
                double close=Double.parseDouble(_closeHoursThursday.getText().toString().trim());

                //changeHours("Thursday", open, close, username); //call to change hours for this day
            }
        });
        setFriBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double open=Double.parseDouble(_openHoursFriday.getText().toString().trim());
                double close=Double.parseDouble(_closeHoursFriday.getText().toString().trim());

                //changeHours("Friday", open, close, username); //call to change hours for this day
            }
        });
        setSatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double open=Double.parseDouble(_openHoursSat.getText().toString().trim());
                double close=Double.parseDouble(_closeHoursSat.getText().toString().trim());

                //changeHours("Saturday", open, close, username); //call to change hours for this day
            }
        });
        setSunBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double open=Double.parseDouble(_openHoursSun.getText().toString().trim());
                double close=Double.parseDouble(_closeHoursSun.getText().toString().trim());

                //changeHours("Sunday", open, close, username); //call to change hours for this day
            }
        });

    }

    protected void onStart() {
        super.onStart();
        //Toast.makeText(getApplicationContext(), clinicId, Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(), loggedInEmployee.getUsername(), Toast.LENGTH_LONG).show();


        databaseEmployees.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnap : dataSnapshot.getChildren()) {
//                    WalkInClinic product = postSnap.getValue(WalkInClinic.class);
//                    assert product != null;
//                    if (clinicId.equals(product.getId())) {
//                        clinic = product;
//                    }
                }
//                TextView hoursMonday = (TextView) findViewById(R.id.hoursMonday);
//                String mon = "Monday Hours : ";
//                hoursMonday.setText(mon);
//
//                TextView hoursTuesday = (TextView) findViewById(R.id.hoursTuesday);
//                String tues = "Tuesday Hours : ";
//                hoursTuesday.setText(tues);
//
//                TextView hoursWednesday = (TextView) findViewById(R.id.hoursWednesday);
//                String wed = "Wednesday Hours : ";
//                hoursWednesday.setText(wed);
//
//                TextView hoursThursday = (TextView) findViewById(R.id.hoursThursday);
//                String thurs = "Thursday Hours : ";
//                hoursThursday.setText(thurs);
//
//                //try {
//                TextView hoursFriday = (TextView) findViewById(R.id.hoursFriday);
//                String fri = "Friday Hours : ";
//                hoursFriday.setText(fri);
//
//                TextView hoursSat = (TextView) findViewById(R.id.hoursSaturday);
//                String sat = "Saturday Hours : ";
//                hoursSat.setText(sat);
//
//                TextView hoursSun = (TextView) findViewById(R.id.hoursSunday);
//                String sun = "Sunday Hours : ";
//                hoursSun.setText(sun);



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

}
