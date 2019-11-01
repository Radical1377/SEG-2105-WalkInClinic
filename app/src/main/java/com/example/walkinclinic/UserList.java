package com.example.walkinclinic;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserList extends ArrayAdapter<User>{
    private Activity context;
    List<User> users;

    public UserList(Activity context, List<User> users) {
        super(context, R.layout.layout_user_list, users);
        this.context = context;
        this.users = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_clinic_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewUsername);
        TextView textViewAll = (TextView) listViewItem.findViewById(R.id.textViewAll);

        User user = users.get(position);
        String fname = user.getFirst_name();
        String lname = user.getLast_name();
        String email = user.getEmail();
        int roleInt = user.getRole();
        String role;

        if (roleInt == 0){
            role = "admin";
        } else if (roleInt == 1){
            role = "employee";
        } else {
            role = "patient";
        }

        String all = "Name: "+fname+" "+lname + ", Email: "+email+", Role: "+role;

        textViewName.setText(user.getUsername());
        textViewAll.setText(all);

        return listViewItem;
    }
}
