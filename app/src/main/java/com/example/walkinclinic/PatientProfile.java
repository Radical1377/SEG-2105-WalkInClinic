package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientProfile extends AppCompatActivity {

    private static User loggedInUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        loggedInUser = LoginActivity.getLoggedInUser();

        TextView ename = (TextView) findViewById(R.id.name);
        ename.setText("Name: " + loggedInUser.getFirst_name()+" "+loggedInUser.getLast_name());

        TextView eEmail = (TextView) findViewById(R.id.email);
        eEmail.setText("Email: "+ loggedInUser.getEmail());

        TextView eUsername = (TextView) findViewById(R.id.username);
        eUsername.setText("Username: "+ loggedInUser.getUsername());

        TextView ePassword = (TextView) findViewById(R.id.password);
        ePassword.setText("Password: *****");

    }

    public void backBtn(View view){
        Intent intent = new Intent(this, WelcomePatient.class);
        startActivity(intent);
        //redirect to the welcome page
    }
}
