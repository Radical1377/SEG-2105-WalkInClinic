package com.example.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PatientSearchByService extends AppCompatActivity {

    private static Service service = new Service();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_search_by_service);

        service = PatientSelectService.getSelectedService();
        PatientSelectService.resetSelectedService();

        //Toast.makeText(getApplicationContext(), service.stringInfo(), Toast.LENGTH_LONG).show();

        TextView selectedService = (TextView) findViewById(R.id.serviceName);
        String name = service.getName();
        if (name == null)
            name ="None";
        selectedService.setText("Selected Service : "+ name);

    }

    public void backBtn(View view){
        Intent intent = new Intent(this, WelcomePatient.class);
        startActivity(intent);
    }

    public void selectService(View view){
        Intent intent = new Intent(this, PatientSelectService.class);
        startActivity(intent);
    }

    public void search(View view){
        if (service.getName() == null){
            Toast.makeText(getApplicationContext(), "Please select a service", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, PatientFilteredClinicByService.class);
            startActivity(intent);
        }
    }

    public static Service getService(){
        return service;
    }
}
