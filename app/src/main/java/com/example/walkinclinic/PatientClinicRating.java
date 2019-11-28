package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientClinicRating extends AppCompatActivity {

    WalkInClinic selectedClinic = null;
    User loggedInPatient = null;

    DatabaseReference databaseReviews = FirebaseDatabase.getInstance().getReference("reviews");

    private static Intent thisIntent = null;
    private Context thisContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_clinic_rating);

        loggedInPatient = LoginActivity.getLoggedInUser();
        selectedClinic = PatientClinicProfile.getSelectedClinic();

        final Button submitButton = (Button) findViewById(R.id.submitBtn);

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                boolean valid = true;                   //checking if non-empty comment and valid number

                final EditText comment = (EditText) findViewById(R.id.comment);
                final RadioButton one = (RadioButton) findViewById(R.id.one);
                final RadioButton two = (RadioButton) findViewById(R.id.two);
                final RadioButton three = (RadioButton) findViewById(R.id.three);
                final RadioButton four = (RadioButton) findViewById(R.id.four);
                final RadioButton five = (RadioButton) findViewById(R.id.five);

                if (!one.isChecked() && !two.isChecked() && !three.isChecked() && !four.isChecked() && !five.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Please indicate a rating.", Toast.LENGTH_LONG).show();
                    valid = false;
                }
                else if (comment == null || comment.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please leave a comment.", Toast.LENGTH_LONG).show();
                    valid = false;
                }

                if (valid) {

                    String com = comment.getText().toString();
                    int rating = 0;

                    if (one.isChecked()) {
                        rating = 1;
                    }
                    else if (two.isChecked()) {
                        rating = 2;
                    }
                    else if (three.isChecked()) {
                        rating = 3;
                    }
                    else if (four.isChecked()) {
                        rating = 4;
                    }
                    else if (five.isChecked()) {
                        rating = 5;
                    }

                    String id = databaseReviews.push().getKey();
                    Review myAdd = new Review(selectedClinic.getId(), com, id, rating, loggedInPatient.getUsername());
                    databaseReviews.child(myAdd.getId()).setValue(myAdd);

                    thisIntent = new Intent(thisContext, PatientClinicProfile.class);
                    startActivity(thisIntent);

                }

            }
        });

    }

    public void backBtn(View view) {
        finish();
    }
}
