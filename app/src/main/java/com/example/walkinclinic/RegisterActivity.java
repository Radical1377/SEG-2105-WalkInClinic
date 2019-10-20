package com.example.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private Database db = new Database();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void submitBtn(View view){
        //To do: Add user to Database.

        EditText eFirstName = (EditText)findViewById(R.id.first_name);
        EditText eLastName = (EditText)findViewById(R.id.last_name);
        EditText eEmail = (EditText)findViewById(R.id.email);
        EditText eUsername = (EditText)findViewById(R.id.username);
        EditText ePassword = (EditText)findViewById(R.id.password);
        EditText ePassConfirm = (EditText)findViewById(R.id.password2);
        RadioButton rPatient = (RadioButton)findViewById(R.id.patient);
        RadioButton rEmployee = (RadioButton)findViewById(R.id.employee);
        String iUsername = null, iPassword = null, iFirstName = null, iLastName = null, iEmail = null;
        int iRole = -1;
        boolean valid = true;

        //First/Last Name field check
        if( eFirstName.getText().toString() == "" || eLastName.getText().toString() == ""){
            Toast.makeText(getApplicationContext(), "Please fill out your First and/or Last Name.", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        else{
            iFirstName = eFirstName.getText().toString();
            iLastName = eLastName.getText().toString();
        }

        //Email field check
        if(eEmail.getText().toString() == ""){
            Toast.makeText(getApplicationContext(), "Please fill out your Email address.", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        else {
            if(eEmail.getText().toString().contains("@") && eEmail.getText().toString().contains(".")){
                iEmail = eEmail.getText().toString();
            }
            else{
                Toast.makeText(getApplicationContext(), "Invalid Email address.", Toast.LENGTH_SHORT).show();
                valid = false;
            }
        }

        //Username field check
        if(eUsername.getText().toString() == ""){
            Toast.makeText(getApplicationContext(), "Please fill out your Username.", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        else{
            if(db.existsUser(eUsername.getText().toString())){
                Toast.makeText(getApplicationContext(), "User already exists in Database.", Toast.LENGTH_SHORT).show();
                valid = false;
            }
            else{
                iUsername = eUsername.getText().toString();
            }
        }

        //Password field check
        if(ePassword.getText().toString() == ""){
            Toast.makeText(getApplicationContext(), "Please fill out your Password.", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        //Password validation check
        if(ePassConfirm.getText().toString() == ""){
            Toast.makeText(getApplicationContext(), "Please fill out your Password validation.", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        else{
            if(!ePassword.getText().toString().equals(ePassConfirm.getText().toString())){
                Toast.makeText(getApplicationContext(), "Password validation failed.", Toast.LENGTH_SHORT).show();
                valid = false;
            }
            else{
                iPassword = ePassword.getText().toString();
            }
        }

        //Role check
        if(!rPatient.isChecked() && !rEmployee.isChecked()){
            Toast.makeText(getApplicationContext(), "Please select one of the status.", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        else{
            if(rPatient.isChecked())
                iRole = 2;
            else if(rEmployee.isChecked())
                iRole = 1;
        }

        //if everything checks out
        if(valid){
            Toast.makeText(getApplicationContext(), "Registeration successful.", Toast.LENGTH_SHORT).show();
            db.addUser(new User(iUsername, iPassword, iFirstName, iLastName, iEmail, iRole));
            finish(); //go back to main page
        }


    }

    public void backBtn(View view){
        finish(); //redirect to the Main page
    }
}
