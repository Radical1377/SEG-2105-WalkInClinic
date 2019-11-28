package com.example.walkinclinic;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.List;

public class BookingList  extends ArrayAdapter<Booking> {

    private Activity context;
    List<Booking> bookings;

    DatabaseReference databaseClinics = FirebaseDatabase.getInstance().getReference("walkinclinic");
    Booking book = null;
    String clinicName = "hello";

    public BookingList(Activity cont, List<Booking> books) {
        super(cont, R.layout.layout_bookings_list, books);
        this.context = cont;
        this.bookings = books;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View listViewItem = inflater.inflate(R.layout.layout_bookings_list, null, true);

        final TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        final TextView textViewAll = (TextView) listViewItem.findViewById(R.id.textViewAll);

        book = bookings.get(position);
        final String date = "Date: "+book.getDate()+"  \n";

        databaseClinics.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnap : dataSnapshot.getChildren()){
                    WalkInClinic product = postSnap.getValue(WalkInClinic.class);

                    if (product.getId().equals(book.getClinicId())) {
                        clinicName = "Clinic: "+product.get_name();
                    }
                }

                String all = date+clinicName;

                textViewName.setText(book.getStartTime());
                textViewAll.setText(all);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        return listViewItem;

    }

}
