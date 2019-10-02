public class Patient extends User {

    public final static int USER_ROLE = 2; //1 = patient role

    //class constructor
    public Patient(String username, String password, String first_name, String last_name, String email){

        super(username, password, first_name, last_name, email);
    }

    //getter method
    public int getUserRole(){
        return USER_ROLE;
    }

    //info printer methpd
    public void printInfo(){
        super.printInfo();
        System.out.println("Role: Patient");
    }

    //patient functionalities to be added in future deliverables...
}
