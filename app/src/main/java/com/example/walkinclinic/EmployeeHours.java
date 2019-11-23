package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmployeeHours extends AppCompatActivity {

    private static User loggedInUser = null;
    private static Employee loggedInEmployee = null;
    private static String username=null;


    DatabaseReference databaseEmployees;

    Button setMonday;
    Button setTuesday;
    Button setWednesday;
    Button setThursday;
    Button setFriday;
    Button setSaturday;
    Button setSunday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_hours);

        loggedInUser = LoginActivity.getLoggedInUser();
        loggedInEmployee = LoginActivity.getLoggedInEmployee();

        //Toast.makeText(getApplicationContext(), loggedInEmployee.getUsername(), Toast.LENGTH_SHORT).show();


        databaseEmployees = FirebaseDatabase.getInstance().getReference("employees");

        // BUTTONS
        setMonday = (Button) findViewById(R.id.setMonday);
        setTuesday = (Button) findViewById(R.id.setTuesday);
        setWednesday = (Button) findViewById(R.id.setWednesday);
        setThursday = (Button) findViewById(R.id.setThursday);
        setFriday = (Button) findViewById(R.id.setFriday);
        setSaturday = (Button) findViewById(R.id.setSaturday);
        setSunday = (Button) findViewById(R.id.setSunday);

        // EDIT TEXTS
        final EditText openMonday = (EditText) findViewById(R.id.openMondayHours);
        final EditText closeMonday = (EditText) findViewById(R.id.closeMondayHours);
        final EditText openTuesday = (EditText) findViewById(R.id.openTuesdayHours);
        final EditText closeTuesday = (EditText) findViewById(R.id.closeTuesdayHours);
        final EditText openWednesday = (EditText) findViewById(R.id.openWednesdayHours);
        final EditText closeWednesday = (EditText) findViewById(R.id.closeWednesdayHours);
        final EditText openThursday = (EditText) findViewById(R.id.openThursdayHours);
        final EditText closeThursday = (EditText) findViewById(R.id.closeThursdayHours);
        final EditText openFriday = (EditText) findViewById(R.id.openFridayHours);
        final EditText closeFriday = (EditText) findViewById(R.id.closeFridayHours);
        final EditText openSaturday = (EditText) findViewById(R.id.openSaturdayHours);
        final EditText closeSaturday = (EditText) findViewById(R.id.closeSaturdayHours);
        final EditText openSunday = (EditText) findViewById(R.id.openSundayHours);
        final EditText closeSunday = (EditText) findViewById(R.id.closeSundayHours);


        setMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (openMonday.getText().toString().replaceAll(" ","").equals("") || closeMonday.getText().toString().replaceAll(" ","").equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all the Monday hours", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        double open = Double.parseDouble(openMonday.getText().toString().trim());
                        double close = Double.parseDouble(closeMonday.getText().toString().trim());

                        editEmployeeHours("Monday", open, close, username);
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "The hours need to be numbers", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        setTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (openTuesday.getText().toString().replaceAll(" ","").equals("") || closeTuesday.getText().toString().replaceAll(" ","").equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all the Tuesday hours", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        double open = Double.parseDouble(openTuesday.getText().toString().trim());
                        double close = Double.parseDouble(closeTuesday.getText().toString().trim());

                        editEmployeeHours("Tuesday", open, close, username);
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "The hours need to be numbers", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        setWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (openWednesday.getText().toString().replaceAll(" ","").equals("") || closeWednesday.getText().toString().replaceAll(" ","").equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all the Wednesday hours", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        double open = Double.parseDouble(openWednesday.getText().toString().trim());
                        double close = Double.parseDouble(closeWednesday.getText().toString().trim());

                        editEmployeeHours("Wednesday", open, close, username);
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "The hours need to be numbers", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        setThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (openThursday.getText().toString().replaceAll(" ","").equals("") || closeThursday.getText().toString().replaceAll(" ","").equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all the Thursday hours", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        double open=Double.parseDouble(openThursday.getText().toString().trim());
                        double close=Double.parseDouble(closeThursday.getText().toString().trim());

                        editEmployeeHours("Thursday", open, close, username);
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "The hours need to be numbers", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        setFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (openFriday.getText().toString().replaceAll(" ","").equals("") || closeFriday.getText().toString().replaceAll(" ","").equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all the Friday hours", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        double open=Double.parseDouble(openFriday.getText().toString().trim());
                        double close=Double.parseDouble(closeFriday.getText().toString().trim());

                        editEmployeeHours("Friday", open, close, username);
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "The hours need to be numbers", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        setSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (openSaturday.getText().toString().replaceAll(" ","").equals("") || closeSaturday.getText().toString().replaceAll(" ","").equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all the Saturday hours", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        double open=Double.parseDouble(openSaturday.getText().toString().trim());
                        double close=Double.parseDouble(closeSaturday.getText().toString().trim());

                        editEmployeeHours("Saturday", open, close, username);
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "The hours need to be numbers", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        setSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (openSunday.getText().toString().replaceAll(" ","").equals("") || closeSunday.getText().toString().replaceAll(" ","").equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all the Sunday hours", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        double open=Double.parseDouble(openSunday.getText().toString().trim());
                        double close=Double.parseDouble(closeSunday.getText().toString().trim());

                        editEmployeeHours("Sunday", open, close, username);
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "The hours need to be numbers", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }

    protected void onStart() {
        super.onStart();

        databaseEmployees.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnap : dataSnapshot.getChildren()) {
                    if (postSnap.getValue().toString().contains(loggedInUser.getUsername())) {
                        Employee emp = postSnap.getValue(Employee.class);
                        emp.set_username(loggedInUser.getUsername());
                        //Toast.makeText(getApplicationContext(), emp.toString(), Toast.LENGTH_SHORT).show();
                        LoginActivity.setLoggedInEmployee(emp);
                        break;
                    }


//                    Toast.makeText(getApplicationContext(), postSnap.toString(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), emp.getClinic(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), emp.getUsername(), Toast.LENGTH_SHORT).show();

                }
                loggedInEmployee = LoginActivity.getLoggedInEmployee();
                username = loggedInEmployee.getUsername();

                //Toast.makeText(getApplicationContext(), loggedInEmployee.toString(), Toast.LENGTH_SHORT).show();


                TextView hoursMonday = (TextView) findViewById(R.id.hoursMonday);
                String mon = "Monday Hours : " + loggedInEmployee.getMon().toString();
                hoursMonday.setText(mon);

                TextView hoursTuesday = (TextView) findViewById(R.id.hoursTuesday);
                String tues = "Tuesday Hours : " + loggedInEmployee.getTues().toString();
                hoursTuesday.setText(tues);

                TextView hoursWednesday = (TextView) findViewById(R.id.hoursWednesday);
                String wed = "Wednesday Hours : " + loggedInEmployee.getWed().toString();
                hoursWednesday.setText(wed);

                TextView hoursThursday = (TextView) findViewById(R.id.hoursThursday);
                String thurs = "Thursday Hours : " + loggedInEmployee.getThurs().toString();
                hoursThursday.setText(thurs);

                TextView hoursFriday = (TextView) findViewById(R.id.hoursFriday);
                String fri = "Friday Hours : " + loggedInEmployee.getFri().toString();
                hoursFriday.setText(fri);

                TextView hoursSaturday = (TextView) findViewById(R.id.hoursSaturday);
                String sat = "Saturday Hours : " + loggedInEmployee.getSat().toString();
                hoursSaturday.setText(sat);

                TextView hoursSunday = (TextView) findViewById(R.id.hoursSunday);
                String sun = "Sunday Hours : " + loggedInEmployee.getSun().toString();
                hoursSunday.setText(sun);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }
        );

    }

    public void editEmployeeHours(String day, double open, double close, String username){
        if (open<close) {
            if (close <= 24) {
                if (open >= 0) {
                    if (day.equals("Monday")) {
                        DatabaseReference dR = databaseEmployees.child(username);

                        dR.child("mon").child("start").setValue(open);
                        dR.child("mon").child("end").setValue(close);
                    } else if (day.equals("Tuesday")) {
                        DatabaseReference dR = databaseEmployees.child(username);

                        dR.child("tues").child("start").setValue(open);
                        dR.child("tues").child("end").setValue(close);
                    } else if (day.equals("Wednesday")) {
                        DatabaseReference dR = databaseEmployees.child(username);

                        dR.child("wed").child("start").setValue(open);
                        dR.child("wed").child("end").setValue(close);
                    } else if (day.equals("Thursday")) {
                        DatabaseReference dR = databaseEmployees.child(username);

                        dR.child("thurs").child("start").setValue(open);
                        dR.child("thurs").child("end").setValue(close);
                    } else if (day.equals("Friday")) {
                        DatabaseReference dR = databaseEmployees.child(username);

                        dR.child("fri").child("start").setValue(open);
                        dR.child("fri").child("end").setValue(close);
                    } else if (day.equals("Saturday")) {
                        DatabaseReference dR = databaseEmployees.child(username);

                        dR.child("sat").child("start").setValue(open);
                        dR.child("sat").child("end").setValue(close);
                    } else if (day.equals("Sunday")) {
                        DatabaseReference dR = databaseEmployees.child(username);

                        dR.child("sun").child("start").setValue(open);
                        dR.child("sun").child("end").setValue(close);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Begin time must be bigger or equal to 0", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "End time must be less or equal to 24", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "End time must be after beginning", Toast.LENGTH_LONG).show();
        }

    }

    public void backBtn(View view){
        finish(); //redirect to the welcome page
    }

}
