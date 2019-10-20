package com.example.walkinclinic;

public interface DBFunc {
    public void addUser(User input); //Database functionality for adding a user to the database
    public User getUser(String input); //Database functionality for obtaining a user from the database
    public void deleteUser(String input); //Database functionality for deleting a user from the database
    public void editUser(String input); //Database functionality for editing a user's credentials inside the database
    public boolean existsUser(String input); //Database functionality for checking whether a certain user exists in the database or not
}
