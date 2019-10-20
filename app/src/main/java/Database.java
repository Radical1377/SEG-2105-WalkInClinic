import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.R.id.message;

public class Database implements DBFunc {
    //log
    private static final String TAG = "Database";

    private FirebaseAuth mAuth;


    public boolean addUser(User input) { //Database functionality for adding a user to the database
        String email = input.getUsername();
        String password = input.getPassword();
        if (!email.equals("") && !password.equals("")){
            //call to mAuth firebase method using text inputs
            mAuth.createUserWithEmailAndPassword(email, password);
            return true;
        } else {
            return false;
        }
    }

    public User getUser(String input){ //Database functionality for obtaining a user from the database
        //the input is a string which will be the username only
        return null;
    }
    //TO_DO Matthew
    public void deleteUser(String input){ //Database functionality for deleting a user from the database
        //TO_DO_LATER Matthew
    }

    public void editUser(User input){ //Database functionality for editing a user's credentials inside the database
        //TO_DO Matthew
    }

    public boolean existsUser(String input){ //Database functionality for checking whether a certain user exists in the database or not
        //TO_DO Matthew

        return true;
    }

}
