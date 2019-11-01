package com.example.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class userAdmin extends AppCompatActivity {

    DatabaseReference databaseUsers;
    ListView listViewUsers;
    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_admin);

        listViewUsers = (ListView) findViewById(R.id.listUsers);

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        users = new ArrayList<>();

        listViewUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                User user = users.get(i);
                showUpdateDeleteDialog(user.getUsername());
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseUsers.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();

                for (DataSnapshot postSnap : dataSnapshot.getChildren()){
                    User user = postSnap.getValue(User.class);

                    users.add(user);
                }

                UserList productsAdapter = new UserList(userAdmin.this, users);

                listViewUsers.setAdapter(productsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }
        );
    }

    public void backBtn(View view){
        finish(); //redirect to the welcome page
    }


    private void showUpdateDeleteDialog(final String username) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_delete_user, null);
        dialogBuilder.setView(dialogView);

        final Button buttonBack = (Button) dialogView.findViewById(R.id.cancel);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.deleteUser);

        dialogBuilder.setTitle(username);
        final AlertDialog b = dialogBuilder.create();

        b.show();

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    b.dismiss();
                }

            }
        );

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser(username);
                b.dismiss();
            }
        });
    }

    // DELETING DOESN'T WORK
    private boolean deleteUser(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("users").child(id);

        dR.removeValue();
        Toast.makeText(getApplicationContext(), "User Deleted", Toast.LENGTH_LONG).show();
        return true;
    }
}
