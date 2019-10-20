public interface DBFunc<U> {
    public void addUser(U input); //Database functionality for adding a user to the database
    public U getUser(String input); //Database functionality for obtaining a user from the database
    //Input will be username (only input for now)
    public void deleteUser(String input); //Database functionality for deleting a user from the database
    public void editUser(U input); //Database functionality for editing a user's credentials inside the database
}
