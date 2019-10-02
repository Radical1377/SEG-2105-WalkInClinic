public class Employee extends User {

    public final static int USER_ROLE = 1; //1 = employee role

    //class constructor
    public Employee(String username, String password, String first_name, String last_name, String email){

        super(username, password, first_name, last_name, email);
    }

    //getter method
    public int getUserRole(){
        return USER_ROLE;
    }

    //info printer methpd
    public void printInfo(){
        super.printInfo();
        System.out.println("Role: Employee");
    }

    //employee functionalities to be added in future deliverables...
}
