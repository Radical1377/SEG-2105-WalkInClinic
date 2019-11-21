package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.walkinclinic.data.WorkHours;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HoursEmployee extends AppCompatActivity {
    private static User loggedInUser = null;
    private static Employee loggedInEmployee = null;
    private static WalkInClinic clinic = null;  //to check if within clinic opening hours
    private static String clinicId=null;
    ListView listViewHours;
    List<WorkHours> workHours;//list of all the working hours for all days (Mon-Sun)

    DatabaseReference databaseEmployees;
    DatabaseReference databaseClinics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_of_hours_emp);
        //listViewHours=(ListView) findViewById(R.id.listViewHours);

        loggedInUser = LoginActivity.getLoggedInUser();
        loggedInEmployee = LoginActivity.getLoggedInEmployee();
        //access employee database tabs in firebase?
        databaseEmployees = FirebaseDatabase.getInstance().getReference("employees");
        //listViewHours = (ListView) findViewById(R.id.listViewHours);


        clinicId =loggedInEmployee.getClinic();
        databaseClinics = FirebaseDatabase.getInstance().getReference("walkinclinic");

        workHours = new ArrayList<>(); //just like in Product Catalog
        //CoPIED STUFF


        //Edit hours for days of the week (opening)
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

        //Buttons
        Button setMonBtn =(Button) findViewById(R.id.setMonday);
        Button setTuesBtn =(Button) findViewById(R.id.setTuesday);
        Button setWedBtn =(Button) findViewById(R.id.setWednesday);
        Button setThurBtn =(Button) findViewById(R.id.setThursday);
        Button setFriBtn =(Button) findViewById(R.id.setFriday);
        Button setSatBtn =(Button) findViewById(R.id.setSaturday);
        Button setSunBtn =(Button) findViewById(R.id.setSunday);


        //onClick listeners for buttons to set/update the hours
        setMonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day="Monday";
                double open=Double.parseDouble(_openHoursMonday.getText().toString().trim());
                double close=Double.parseDouble(_closeHoursMonday.getText().toString().trim());

                changeHours(day, open, close, loggedInEmployee); //call to change hours for this day

            }
        });
        setTuesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day="Tuesday";
                double open=Double.parseDouble(_openHoursTuesday.getText().toString().trim());
                double close=Double.parseDouble(_closeHoursTuesday.getText().toString().trim());

                changeHours(day, open, close, loggedInEmployee); //call to change hours for this day

            }
        });
        setWedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day="Wednesday";
                double open=Double.parseDouble(_openHoursWednesday.getText().toString().trim());
                double close=Double.parseDouble(_closeHoursWednesday.getText().toString().trim());

                changeHours(day, open, close, loggedInEmployee); //call to change hours for this day

            }
        });
        setThurBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day="Thursday";
                double open=Double.parseDouble(_openHoursThursday.getText().toString().trim());
                double close=Double.parseDouble(_closeHoursThursday.getText().toString().trim());

                changeHours(day, open, close, loggedInEmployee); //call to change hours for this day

            }
        });
        setFriBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day="Friday";
                double open=Double.parseDouble(_openHoursFriday.getText().toString().trim());
                double close=Double.parseDouble(_closeHoursFriday.getText().toString().trim());


                changeHours(day, open, close, loggedInEmployee); //call to change hours for this day

            }
        });
        setSatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day="Saturday";
                double open=Double.parseDouble(_openHoursSat.getText().toString().trim());
                double close=Double.parseDouble(_closeHoursSat.getText().toString().trim());

                changeHours(day, open, close, loggedInEmployee); //call to change hours for this day

            }
        });
        setSunBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day="Sunday";
                double open=Double.parseDouble(_openHoursSun.getText().toString().trim());
                double close=Double.parseDouble(_closeHoursSun.getText().toString().trim());

                changeHours(day, open, close, loggedInEmployee); //call to change hours for this day

            }
        });

        //END OF COPIED STUFF

    }
    protected void onStart() {
        super.onStart();

        databaseEmployees.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                /*clear previous list (of workHours)
                //workHours.clear();
                //Iterate Nodes
                for (DataSnapshot postSnap: dataSnapshot.getChildren() ) {
                    //get hours for each day
                    WorkHours Hours=postSnap.getValue(WorkHours.class);
                    //add workHours to list
                    //workHours.add(Hours);

                    //create adapter for listView
                    EmployeeHoursList hoursAdapter = new EmployeeHoursList(HoursEmployee.this, workHours);
                    //attach adapterto listView in this class
                    listViewHours.setAdapter(hoursAdapter);



                }*/
                //get the clinic
                for (DataSnapshot postSnap: dataSnapshot.getChildren()) {
                    //access the employees clinic
                    WalkInClinic product = postSnap.getValue(WalkInClinic.class);
                    if (clinicId.equals(product.getId())){
                        clinic=product; //assign the value to clinic so we can use to check hours
                    }
                }
                //TO_DO update the stuff
                loggedInEmployee = LoginActivity.getLoggedInEmployee();

                //all the hour displays for day in the week

                TextView _hoursMonday = (TextView) findViewById(R.id.hoursMonday);
                TextView _hoursTuesday = (TextView) findViewById(R.id.hoursTuesday);
                TextView _hoursWednesday = (TextView) findViewById(R.id.hoursWednesday);
                TextView _hoursThursday = (TextView) findViewById(R.id.hoursThursday);
                TextView _hoursFriday = (TextView) findViewById(R.id.hoursFriday);
                TextView _hoursSat = (TextView) findViewById(R.id.hoursSaturday);
                TextView _hoursSun = (TextView) findViewById(R.id.hoursSunday);

                //setting the text
                _hoursMonday.setText(loggedInEmployee.getDayHours("Monday").displayHours());
                _hoursTuesday.setText(loggedInEmployee.getDayHours("Tuesday").displayHours());
                _hoursWednesday.setText(loggedInEmployee.getDayHours("Wednesday").displayHours());
                _hoursThursday.setText(loggedInEmployee.getDayHours("Thursday").displayHours());
                _hoursFriday.setText(loggedInEmployee.getDayHours("Friday").displayHours());
                _hoursSat.setText(loggedInEmployee.getDayHours("Saturday").displayHours());
                _hoursSun.setText(loggedInEmployee.getDayHours("Sunday").displayHours());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void changeHours(String day, double open, double close, Employee employee){
        if (open<close) {
            if (day=="Saturday"|| day=="Sunday") {
                //weekend
                if (open >= clinic.get_openingHourWeekEnd()&& close<=clinic.get_closingHourWeekEnd()) {
                    //check for within clinic hours
                    WorkHours workHours = new WorkHours(day, open, close);
                    //employee.set_dayHours(day, workHours);

                    //save to firebase
                    String username = loggedInEmployee.getUsername(); //get the specific employee in database
                    DatabaseReference dH = databaseEmployees.child(username); //reference to specific employee
                    dH.child("hours").child(day).setValue(workHours); //update day in hours tab
                }
                else {
                    Toast.makeText(getApplicationContext(), "Not within clinic weekend hours", Toast.LENGTH_LONG).show();

                }
            } else {
                //weekdays, check those opening hours
                if (open >= clinic.get_openingHourWeekDay()&& close<=clinic.get_closingHourWeekDay()) {
                    //check for within clinic hours
                    WorkHours workHours = new WorkHours(day, open, close);
                    //employee.set_dayHours(day, workHours);

                    //save to firebase
                    String username = loggedInEmployee.getUsername(); //get the specific employee in database
                    DatabaseReference dH = databaseEmployees.child(username); //reference to specific employee
                    dH.child("hours").child(day).setValue(workHours); //update day in hours tab
                }
                else {
                    Toast.makeText(getApplicationContext(), "Not within clinic weekday hours", Toast.LENGTH_LONG).show();
                }
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "End time must be after beginning", Toast.LENGTH_LONG).show();
        }
    }



    public void backBtn(View view){
        finish(); //redirect to the welcome page
    }

}
