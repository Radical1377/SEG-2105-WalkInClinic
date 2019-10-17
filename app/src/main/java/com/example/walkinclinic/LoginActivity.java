package com.example.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void submitBtn(View view){

        EditText eUser = (EditText)findViewById(R.id.username);
        EditText ePassword = (EditText)findViewById(R.id.password);

        //To do: User Authentication from the Database

        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    public void backBtn(View view){
        finish(); //redirect to the login page
    }
}
