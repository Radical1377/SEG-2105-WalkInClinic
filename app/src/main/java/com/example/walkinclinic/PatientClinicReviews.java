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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientClinicReviews extends AppCompatActivity {

    DatabaseReference databaseReviews;
    ListView listViewReviews;
    List<Review> reviews;

    WalkInClinic selectedClinic = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_clinic_reviews);

        listViewReviews = (ListView) findViewById(R.id.listReviews);

        databaseReviews = FirebaseDatabase.getInstance().getReference("reviews");

        reviews = new ArrayList<>();

        selectedClinic = PatientClinicProfile.getSelectedClinic();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReviews.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                reviews.clear();

                for (DataSnapshot postSnap : dataSnapshot.getChildren()){
                    Review product = postSnap.getValue(Review.class);
                    Toast.makeText(getApplicationContext(), product.stringInfo(), Toast.LENGTH_SHORT).show();

                    if(selectedClinic.getId().equals(product.getClinicId())) {
                        reviews.add(product);
                    }
                }

                ReviewList productsAdapter = new ReviewList(PatientClinicReviews.this, reviews);

                listViewReviews.setAdapter(productsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }
        );
    }

    public void backBtn(View view){
        finish(); //redirect to the clinic profile page
    }

}
