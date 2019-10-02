import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class User {

    protected String username; // primary identifier
    protected String password; // encrypted using SHA-256 encryption
    protected String first_name, last_name; // real first and last name
    protected String email; // email address

    //class constructors

    public User(String first_name, String last_name, String email){

        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public User(String username, String password, String first_name, String last_name, String email){

        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;

        //exception handling
        try {
            this.password = Sha256.encrypt(password); //encrypts given plain password
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //setter methods
    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        try {
            this.password = Sha256.encrypt(password); //encrypts given plain password
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void setFull_name(String first_name, String last_name){
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    //getter methods
    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getFirst_name(){
        return this.first_name;
    }

    public String getLast_name(){
        return this.last_name;
    }

    public String getEmail(){
        return this.email;
    }

    //info printer method
    public void printInfo(){
        System.out.println("User: " + this.username);
        System.out.println("Password: " + this.password);
        System.out.println("Full Name: " + this.first_name + " " + this.last_name);
        System.out.println("Email: " + this.email);
    }

}
