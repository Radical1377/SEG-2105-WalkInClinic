package com.example.walkinclinic;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        databaseUser = FirebaseDatabase.getInstance().getReference();
        databaseUser.child("users").child(newUser.getUsername()).setValue(newUser);
    }

    public User getUser(final String username){ //Database functionality for obtaining a user from the database
        //the input is a string which will be the username only
        //global user variable
        //globalUser = null;
        //get specified user reference
        databaseUser= FirebaseDatabase.getInstance().getReference().child("users");

        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //attempt at another way?
                globalUser=(User)dataSnapshot.child(username).getValue();
                return;
                /*
                for (DataSnapshot postSnap: dataSnapshot.getChildren()) {
                    User user = postSnap.getValue(User.class);
                    if (user.getUsername()==username) {
                        Log.w(TAG, "GOT USER SUCCESSFUL");
                        globalUser=user;
                        return;
                    }

                }

                 */
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

    public boolean existsUser(final String input){ //Database functionality for checking whether a certain user exists in the database or not
        //TO_DO Matthew
        //userExists=null;

        databaseUser=FirebaseDatabase.getInstance().getReferenceFromUrl("https://seg2105-walkinclinic.firebaseio.com/users");

        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(input).exists() ){
                    //check if child in database (username) exists or not
                    Log.w(TAG, "USER FOUND");
                    userExists=true;
                    return;
                }
                else {userExists=false;}
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
