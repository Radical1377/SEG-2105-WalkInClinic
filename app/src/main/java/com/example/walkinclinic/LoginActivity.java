package com.example.walkinclinic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    private static Database db = new Database();
    private static String username;
    private static String encpassword;
    private static Intent intent = null;

    private static User loggedInUser = null;
    private static Employee loggedInEmployee = null;

    DatabaseReference databaseUser = FirebaseDatabase.getInstance().getReference("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loggedInUser = null;
        loggedInEmployee = null;
        intent = null;
        username= null;
        encpassword = null;
    }

    public void submitBtn(View view) throws Exception {

        EditText eUser = (EditText)findViewById(R.id.username);
        EditText ePassword = (EditText)findViewById(R.id.password);
        username = eUser.getText().toString();
        encpassword = Sha256.encrypt(ePassword.getText().toString());

        final Context thisContext = this;

        databaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnap : dataSnapshot.getChildren()){

                    loggedInUser = postSnap.getValue(User.class);
                    //Toast.makeText(getApplicationContext(), loggedInUser.stringInfo(), Toast.LENGTH_SHORT).show();

                    if (loggedInUser!=null && username.equals(loggedInUser.getUsername()) && encpassword.equals(loggedInUser.getPassword())) {
                        if (loggedInUser.getRole() == 0 ) {
                            intent = new Intent(thisContext, WelcomeActivity.class);
                            startActivity(intent);
                            break;
                        }
                        else if (loggedInUser.getRole() == 1) {
                            loggedInEmployee = new Employee(loggedInUser.getUsername(),null);

                            intent = new Intent(thisContext, WelcomeEmployee.class);
                            startActivity(intent);
                            break;
                        }
                        else if (loggedInUser.getRole() == 2) {
                            intent = new Intent(thisContext, WelcomePatient.class);
                            startActivity(intent);
                            break;
                        }
                    }
                    else {
                        intent = null;
                    }


                }
                if (intent==null) {
                    Toast.makeText(getApplicationContext(),"Incorrect username/password.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }
        );

    }

    public void backBtn(View view){
        finish(); //redirect to the login page
    }
    public static Employee getLoggedInEmployee() {
        return loggedInEmployee;
    }
    public static User getLoggedInUser(){
        return loggedInUser;
    }

    public static void setLoggedInEmployee(Employee emp) {

        loggedInEmployee = emp;
    }

    public static void setLoggedInUser(User use) {

        loggedInUser = use;
    }


}
