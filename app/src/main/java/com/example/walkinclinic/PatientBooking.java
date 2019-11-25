package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class PatientBooking extends AppCompatActivity {

    User loggedInPatient = null;
    WalkInClinic selectedClinic = null;

    Button buttonAddService;
    Button submitButton;
    ListView listViewServices;
    List<Service> servicesAdmin;

    DatabaseReference databaseServices;

    Service selectedService = null;

    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
    int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    int intStartTimeHours = -1;
    int intEndTimeHours = -1;
    int intStartTimeMinutes = -1;
    int intEndTimeMinutes = -1;
    int intDay = -1;
    int intMonth = -1;
    int intYear = -1;

    DatabaseReference databaseBookings = FirebaseDatabase.getInstance().getReference("bookings");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_booking);

        loggedInPatient = LoginActivity.getLoggedInUser();
        selectedClinic = PatientClinicProfile.getSelectedClinic();
        servicesAdmin = new ArrayList<>();
        submitButton = (Button) findViewById(R.id.submitBtn);
        buttonAddService = (Button) findViewById(R.id.serviceSelection);
        databaseServices = FirebaseDatabase.getInstance().getReference("services");

        TextView nameText = (TextView) findViewById(R.id.clinicName);
        String name = "Clinic : " + selectedClinic.get_name();
        nameText.setText(name);

        TextView usernameText = (TextView) findViewById(R.id.username);
        String userName = "Username : " + loggedInPatient.getUsername();
        usernameText.setText(userName);


        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addService();
            }
        });

        databaseServices.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                servicesAdmin.clear();

                for (DataSnapshot postSnap : dataSnapshot.getChildren()){
                    Service service = postSnap.getValue(Service.class);

                    servicesAdmin.add(service);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }
        );

        final EditText startTimeHours = (EditText) findViewById(R.id.startTimeHours);
        final EditText startTimeMinutes  = (EditText) findViewById(R.id.startTimeMinutes);
        final EditText endTimeHours = (EditText) findViewById(R.id.endTimeHours);
        final EditText endTimeMinutes = (EditText) findViewById(R.id.endTimeMinutes);
        final EditText dateDay = (EditText) findViewById(R.id.dateDay);
        final EditText dateMonth = (EditText) findViewById(R.id.dateMonth);
        final EditText dateYear = (EditText) findViewById(R.id.dateYear);

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                boolean valid = true;

                //CHECKING THAT START/END TIME HOURS IS CORRECT ////////////////////////////////////////////////////////////
                try {
                    intStartTimeHours = Integer.parseInt(startTimeHours.getText().toString());

                    if (intStartTimeHours <0 || intStartTimeHours >24) {
                        Toast.makeText(getApplicationContext(), "Please enter a valid start time.", Toast.LENGTH_LONG).show();
                        valid = false;
                    }

                } catch (Exception s) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid start time.", Toast.LENGTH_LONG).show();
                    valid = false;                  //didn't enter a number for the hours
                }
                try {
                    intEndTimeHours = Integer.parseInt(endTimeHours.getText().toString());

                    if (intEndTimeHours <0 || intEndTimeHours >24) {
                        Toast.makeText(getApplicationContext(), "Please enter a valid end time.", Toast.LENGTH_LONG).show();
                        valid = false;
                    }

                } catch (Exception s) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid end time.", Toast.LENGTH_LONG).show();
                    valid = false;                  //didn't enter a number for the hours
                }
                //CHECKING THAT START/END TIME MINUTES IS CORRECT ////////////////////////////////////////////////////////////
                try {
                    intStartTimeMinutes = Integer.parseInt(startTimeMinutes.getText().toString());

                    if (intStartTimeMinutes <0 || intStartTimeMinutes >59) {
                        Toast.makeText(getApplicationContext(), "Please enter a valid start time.", Toast.LENGTH_LONG).show();
                        valid = false;
                    }

                } catch (Exception s) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid start time.", Toast.LENGTH_LONG).show();
                    valid = false;                  //didn't enter a number for the hours
                }
                try {
                    intEndTimeMinutes = Integer.parseInt(endTimeMinutes.getText().toString());

                    if (intEndTimeMinutes <0 || intEndTimeMinutes >59) {
                        Toast.makeText(getApplicationContext(), "Please enter a valid start time.", Toast.LENGTH_LONG).show();
                        valid = false;
                    }

                } catch (Exception s) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid end time.", Toast.LENGTH_LONG).show();
                    valid = false;                  //didn't enter a number for the hours
                }
                //CHECKING THAT START TIME IS AFTER END TIME IS CORRECT ////////////////////////////////////////////////////////////
                if (intEndTimeHours < intStartTimeHours) {
                    Toast.makeText(getApplicationContext(), "Start time must be before end time.", Toast.LENGTH_LONG).show();
                    valid = false;
                }
                else if (intEndTimeHours == intStartTimeHours && intStartTimeMinutes > intEndTimeMinutes) {
                    Toast.makeText(getApplicationContext(), "Start time must be before end time.", Toast.LENGTH_LONG).show();
                    valid = false;
                }
                //CHECKING THAT THAT DATE IS CORRECT && EXISTS ////////////////////////////////////////////////////////////
                try {
                    intMonth = Integer.parseInt(dateMonth.getText().toString());

                    if (intMonth <0 || intMonth >12) {
                        Toast.makeText(getApplicationContext(), "Please enter a valid month.", Toast.LENGTH_LONG).show();
                        valid = false;
                    }
                    if (intEndTimeHours - intStartTimeHours > 3) {
                        Toast.makeText(getApplicationContext(), "You may only book for up to 4 hours.", Toast.LENGTH_LONG).show();
                        valid = false;
                    }

                } catch (Exception s) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid month.", Toast.LENGTH_LONG).show();
                    valid = false;                  //didn't enter a number for the hours
                }
                try {
                    intYear = Integer.parseInt(dateYear.getText().toString());

                    if (intYear < currentYear) {
                        Toast.makeText(getApplicationContext(), "Please enter a valid year.", Toast.LENGTH_LONG).show();
                        valid = false;
                    }

                } catch (Exception s) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid year.", Toast.LENGTH_LONG).show();
                    valid = false;                  //didn't enter a number for the hours
                }
                try {
                    intDay = Integer.parseInt(dateDay.getText().toString());

                    if (intDay < 0) {
                        Toast.makeText(getApplicationContext(), "Please enter a valid day.", Toast.LENGTH_LONG).show();
                        valid = false;
                    }
                    else if ((intMonth==1 || intMonth==3 || intMonth==5 || intMonth==7 || intMonth==8 || intMonth==10 || intMonth==12 )
                                                && intDay >31) {
                        Toast.makeText(getApplicationContext(), "Please enter a valid day.", Toast.LENGTH_LONG).show();
                        valid = false;
                    }
                    else if ((intMonth==4 || intMonth==6 || intMonth==9 || intMonth==11)
                            && intDay >30) {
                        Toast.makeText(getApplicationContext(), "Please enter a valid day.", Toast.LENGTH_LONG).show();
                        valid = false;
                    }
                    else if ((intMonth==2 ) && intDay >28) {
                        Toast.makeText(getApplicationContext(), "Please enter a valid day.", Toast.LENGTH_LONG).show();
                        valid = false;
                    }

                } catch (Exception s) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid day.", Toast.LENGTH_LONG).show();
                    valid = false;                  //didn't enter a number for the hours
                }
                //NEED TO SELECT A SERVICE ////////////////////////////////////////////////////////////
                if (selectedService == null) {
                    Toast.makeText(getApplicationContext(), "Select a service.", Toast.LENGTH_LONG).show();
                    valid = false;
                }
                //DAY IS AFTER TODAY ////////////////////////////////////////////////////////////
                if (intYear==currentYear && currentMonth==intMonth && currentDay>intDay) {
                    Toast.makeText(getApplicationContext(), "Can only book for days starting tomorrow.", Toast.LENGTH_LONG).show();
                    valid = false;
                }

                //ALL THE VALIDATIONS ARE DONE - CAN ADD TO A NEW BOOKING ////////////////////////////////////////////////////////////

                if (valid) {
                    String id = databaseBookings.push().getKey();
                    Booking newBook = new Booking (selectedClinic.getId(), loggedInPatient.getUsername(), id, selectedService.getId(),
                            intStartTimeHours,intStartTimeMinutes,intEndTimeHours, intEndTimeMinutes,intDay,intMonth,intYear);

                    databaseBookings.child(id).setValue(newBook);

                }
            }
        });
    }

    public void addService(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_add_service_clinic, null);
        dialogBuilder.setView(dialogView);

        final Button buttonCancel = (Button) dialogView.findViewById(R.id.cancel);
        final ListView listServices = (ListView) dialogView.findViewById(R.id.listServicesClinic);

        ServiceList productsAdapter = new ServiceList(PatientBooking.this, servicesAdmin);

        listServices.setAdapter(productsAdapter);

        final AlertDialog b = dialogBuilder.create();
        b.show();

        listServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedService = servicesAdmin.get(position);
                buttonAddService.setText(selectedService.getName());
                b.dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedClinic = null;
                buttonAddService.setText("Select a service");
                b.dismiss();
            }
        });

    }

    public void backBtn(View view) {
        finish();
    }
}
