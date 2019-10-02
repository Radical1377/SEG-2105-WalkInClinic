import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Admin extends User {

    public final static int USER_ROLE = 0; //0 = admin role

    //class constructor
    public Admin(String first_name, String last_name, String email){

        super(first_name,last_name,email);

        this.username = "admin";
        try {
            this.password = Sha256.encrypt("5T5ptQ");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //getter method
    public int getUserRole(){
        return USER_ROLE;
    }

    //info printer methpd
    public void printInfo(){
        super.printInfo();
        System.out.println("Role: Admin");
    }

    //admin functionalities to be added in future deliverables...
}
