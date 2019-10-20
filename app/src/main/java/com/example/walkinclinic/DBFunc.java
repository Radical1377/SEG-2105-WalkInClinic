package com.example.walkinclinic;
import android.os.Bundle;

public interface DBFunc {
    void addUser(User newUser); //Database functionality for adding a user to the database
    User getUser(String input); //Database functionality for obtaining a user from the database
    //Input will be username (only input for now)
    void deleteUser(String input); //Database functionality for deleting a user from the database
    void editUser(User input); //Database functionality for editing a user's credentials inside the database
}
