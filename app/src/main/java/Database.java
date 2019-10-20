import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.R.id.message;

public class Database<U> implements DBFunc<U> {

    public void addUser(U input) { //Database functionality for adding a user to the database
        //TO_DO Matthew
    }

    public U getUser(String input){ //Database functionality for obtaining a user from the database
        //the input is a string which will be the username only
        return null;
    }
    //TO_DO Matthew
    public void deleteUser(String input){ //Database functionality for deleting a user from the database
        //TO_DO Matthew
    }

    public void editUser(U input){ //Database functionality for editing a user's credentials inside the database
        //TO_DO Matthew
    }

    public boolean existsUser(String input){ //Database functionality for checking whether a certain user exists in the database or not
        //TO_DO Matthew
        return null;
    }

}
