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
import java.lang.String;

public class PatientBooking extends AppCompatActivity {

    User loggedInPatient = null;
    WalkInClinic selectedClinic = null;

    Button buttonAddService;
    Button submitButton;
    ListView listViewServices;
    List<Service> servicesAdmin;

    DatabaseReference databaseServicesClinic;

    Service selectedService = null;

    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
    int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    int weekDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

    int clinicOpeningHour = -1;
    int clinicClosingHour = -1;
    String appointmentStartTime = null;
    int numberOfAppointments = 0;
    String today = currentDay+"/"+currentMonth+"/"+currentYear;

    private static List<String> hours =  new ArrayList<>();

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
        databaseServicesClinic = FirebaseDatabase.getInstance().getReference("servicesClinic");

        TextView nameText = (TextView) findViewById(R.id.clinicName);
        String name = "Clinic : " + selectedClinic.get_name();
        nameText.setText(name);

        TextView usernameText = (TextView) findViewById(R.id.username);
        String userName = "Username : " + loggedInPatient.getUsername();
        usernameText.setText(userName);

        TextView dateText = (TextView) findViewById(R.id.date);
        dateText.setText("Date: "+today);


        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addService();
            }
        });

        databaseServicesClinic.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                servicesAdmin.clear();

                for (DataSnapshot postSnap : dataSnapshot.getChildren()){
                    ServicesClinic product = postSnap.getValue(ServicesClinic.class);

                    if (product.getClinicId().equals(selectedClinic.getId())) {
                        servicesAdmin.add(product.getService());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }
        );

        ////////FIGURING OUT WHEN THE NEXT OPEN APPOINTMENT WOULD BE ////////////////////////////////////////////////////////

        final TextView textViewStartTime = (TextView) findViewById(R.id.startTime);
        final TextView textViewWaitTime = (TextView) findViewById(R.id.waitTime);

        if (weekDay==1 || weekDay==7) {                 //today is a weekend
            clinicOpeningHour = selectedClinic.get_openingHourWeekEnd();
            clinicClosingHour = selectedClinic.get_closingHourWeekEnd();
        }
        else {                                          //today is a weekday
            clinicOpeningHour = selectedClinic.get_openingHourWeekDay();
            clinicClosingHour = selectedClinic.get_closingHourWeekDay();
        }

        //Toast.makeText(getApplicationContext(),"Opening hour: "+ clinicOpeningHour, Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), "Closing hour: "+clinicClosingHour, Toast.LENGTH_SHORT).show();

        databaseBookings.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hours.clear();
                for (DataSnapshot postSnap : dataSnapshot.getChildren()){
                    Booking product = postSnap.getValue(Booking.class);

                    if (product.getClinicId().equals(selectedClinic.getId()) && product.getDate().equals(today)) {
                        hours.add(product.getStartTime());
                    }
                }

                if (hours.isEmpty()) {
                    numberOfAppointments = 0;
                    appointmentStartTime = clinicOpeningHour+":00";
                }
                else {
                    numberOfAppointments = hours.size();

                    //need to find time of next appointement - add 15 * numberOfAppointments to the clinicStartTime
                    int timeFromStart = numberOfAppointments * 15;
                    //Toast.makeText(getApplicationContext(),"Previous appointements: " +numberOfAppointments, Toast.LENGTH_SHORT).show();
                    int additionalHours = timeFromStart / 60;
                    int additionalMinutes = timeFromStart % 60;
                    appointmentStartTime = (clinicOpeningHour+additionalHours) + ":" + additionalMinutes;
                    if (additionalMinutes==0) {
                        appointmentStartTime = (clinicOpeningHour+additionalHours) + ":00";
                    }
                    //check its not the end of the day yet
                    if (numberOfAppointments == (clinicClosingHour-clinicOpeningHour)*4 ) {
                        Toast.makeText(getApplicationContext(), "No more appointments available for today.", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }

                int waitTime = numberOfAppointments * 15;

                textViewStartTime.setText("Anticipated Appointment Start Time : "+appointmentStartTime);
                textViewWaitTime.setText("Waiting time: " + waitTime +" minutes");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }
        );

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                boolean valid = true;

                if (selectedService==null) {
                    valid = false;
                    Toast.makeText(getApplicationContext(),"Must select a service.", Toast.LENGTH_SHORT).show();
                }

                if (valid) {
                    Toast.makeText(getApplicationContext(), "Appointment has been made.", Toast.LENGTH_SHORT).show();

                    String id = databaseBookings.push().getKey();
                    Booking book = new Booking (selectedClinic.getId(), loggedInPatient.getUsername(), id, selectedService.getId(), appointmentStartTime, today);

                    databaseBookings.child(id).setValue(book);

                    finish();
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
