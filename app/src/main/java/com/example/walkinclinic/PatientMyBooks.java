package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientMyBooks extends AppCompatActivity {

    User loggedInPatient = null;

    ListView listViewBookings;
    List<Booking> bookings;

    DatabaseReference databaseBookings = FirebaseDatabase.getInstance().getReference("bookings");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_my_books);

        loggedInPatient = LoginActivity.getLoggedInUser();

        listViewBookings = (ListView) findViewById(R.id.listBookings);
        bookings = new ArrayList<>();


    }

    protected void onStart() {
        super.onStart();

        databaseBookings.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookings.clear();
                for (DataSnapshot postSnap : dataSnapshot.getChildren()){
                    Booking product = postSnap.getValue(Booking.class);

                    if (product.getUsername().equals(loggedInPatient.getUsername())){
                        bookings.add(product);
                    }
                }

                BookingList productsAdapter = new BookingList(PatientMyBooks.this, bookings);
                listViewBookings.setAdapter(productsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }


    public void backBtn(View view){
        finish();
    }

}
