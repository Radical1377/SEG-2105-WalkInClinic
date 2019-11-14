package com.example.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomePatient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_patient);
    }

    public void logoffButton(View view){
        // HAVE TO CLEAR THE USERNAME AND PASSWORD
        //finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
