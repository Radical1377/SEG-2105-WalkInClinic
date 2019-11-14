package com.example.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ClinicEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_employee);
    }

    public void backBtn(View view){
        finish(); //redirect to the welcome page
    }

    public void editClinicHours(View view){
//        Intent intent = new Intent(this, userAdmin.class);
//        startActivity(intent);
    }
    public void servicesClinic(View view){
//        Intent intent = new Intent(this, userAdmin.class);
//        startActivity(intent);
    }
}
