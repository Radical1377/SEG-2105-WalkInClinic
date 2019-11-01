package com.example.walkinclinic;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ServiceList extends ArrayAdapter<Service>{
    private Activity context;
    List<Service> services;

    public ServiceList(Activity context, List<Service> services) {
        super(context, R.layout.layout_clinic_list, services);
        this.context = context;
        this.services = services;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_service_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewAll = (TextView) listViewItem.findViewById(R.id.textViewAll);

        Service service = services.get(position);
        int staff = service.getAppropriateStaff();
        String clinic = String.valueOf(service.getId());
        String aStaff;

        if (staff == 0){
            aStaff = "doctor";
        } else if (staff == 1){
            aStaff = "nurse";
        } else {
            aStaff = "staff";
        }

        String all = "Clinic: "+clinic + ", Staff: "+aStaff;

        textViewName.setText(service.getName());
        textViewAll.setText(all);

        return listViewItem;
    }
}
