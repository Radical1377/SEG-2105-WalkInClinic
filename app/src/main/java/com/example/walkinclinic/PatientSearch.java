package com.example.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_search);

        Button service = (Button) findViewById(R.id.selectService);
        //service.setText("Rainbow");
    }

    public void backBtn(View view) {
        finish();
    }
}
