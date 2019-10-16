package com.example.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToRegister(View v){
        Intent intent1 = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivityForResult(intent1, 0);
    }

    public void goToLogin(View v){
        Intent intent2 = new Intent(getApplicationContext(), LoginActivity.class);
        startActivityForResult(intent2, 0);
    }

    public void onClick(View view) {
        Intent intent1 = new Intent(getApplicationContext(), RegisterActivity.class);
        Intent intent2 = new Intent(getApplicationContext(), LoginActivity.class);
        switch(view.getId()) {
            case R.id.regButton:
                startActivity(intent1);
                break;
            case R.id.logButton:
                startActivity(intent2);
                break;
        }
    }
}
