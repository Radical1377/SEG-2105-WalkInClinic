package com.example.walkinclinic;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ServicesClinicList extends ArrayAdapter<ServicesClinic> {

    private Activity context;
    List<ServicesClinic> services;

    public ServicesClinicList(Activity context, List<ServicesClinic> services) {
        super(context, R.layout.layout_clinic_list, services);
        this.context = context;
        this.services = services;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_services_clinic_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewAll = (TextView) listViewItem.findViewById(R.id.textViewAll);

        ServicesClinic service = services.get(position);

        int staff = service.getService().getAppropriateStaff();
        int rate = service.getRate();
        String aStaff;

        if (staff == 0){
            aStaff = "Doctor";
        } else if (staff == 1){
            aStaff = "Nurse";
        } else {
            aStaff = "Other";
        }

        String all = "Staff: "+aStaff+", Rate: "+rate;

        textViewName.setText(service.getService().getName());
        textViewAll.setText(all);

        return listViewItem;
    }
}
