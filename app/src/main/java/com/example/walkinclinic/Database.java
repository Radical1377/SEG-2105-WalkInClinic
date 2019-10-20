package com.example.walkinclinic;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class Database implements DBFunc {
    //log
    private static final String TAG = "Database";
    //list to hold users
    List<User> userList;
    //global user variable
    private User globalUser;
    private boolean userExists;

    //private FirebaseAuth mAuth;

    DatabaseReference databaseUser;


    public void addUser(User newUser) { //Database functionality for adding a user to the database
        databaseUser = FirebaseDatabase.getInstance().getReference("users");
        //get unique id with push method
        //create unique id and use as Primary Key for product
        String id = databaseUser.push().getKey();
        //save product
        databaseUser.child(id).setValue(newUser);
    }

    public User getUser(final String username){ //Database functionality for obtaining a user from the database
        //the input is a string which will be the username only
        //global user variable
        globalUser = null;
        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnap: dataSnapshot.getChildren()) {
                    User user = postSnap.getValue(User.class);
                    if (user.getUsername()==username) {
                        globalUser=user;
                        return;
                    }

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                throw databaseError.toException();
            }
        });
        return globalUser;
    }
    //TO_DO Matthew
    public void deleteUser(String input){ //Database functionality for deleting a user from the database
        //TO_DO_LATER Matthew
    }

    public void editUser(User input){ //Database functionality for editing a user's credentials inside the database
        //TO_DO Matthew
    }

    public boolean existsUser(String username){ //Database functionality for checking whether a certain user exists in the database or not
        //TO_DO Matthew
        userExists=false;
        databaseUser=FirebaseDatabase.getInstance().getReference();
        databaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(username).exists() ){
                    //check if child in database (username) exists or not
                    userExists=true;
                    return;
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                throw databaseError.toException();
            }
        });
        return userExists;
    }

}
