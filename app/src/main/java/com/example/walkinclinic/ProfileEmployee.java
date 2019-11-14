package com.example.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfileEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_employee);
    }

    public void modifyButton(View view){
        Intent intent = new Intent(this, EditProfileEmployee.class);
        startActivity(intent);
    }
    public void backBtn(View view){
        finish(); //redirect to the welcome page
    }

}
