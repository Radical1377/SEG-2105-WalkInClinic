package com.example.walkinclinic;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ClinicList extends ArrayAdapter<WalkInClinic> {
    private Activity context;
    List<WalkInClinic> clinics;

    public ClinicList(Activity context, List<WalkInClinic> clinics) {
        super(context, R.layout.layout_clinic_list, clinics);
        this.context = context;
        this.clinics = clinics;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_clinic_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewAll = (TextView) listViewItem.findViewById(R.id.textViewAll);

        WalkInClinic clinic = clinics.get(position);
        String address = clinic.get_address();
        String openD = String.valueOf(clinic.get_openingHourWeekDay());
        String closeD =String.valueOf(clinic.get_closingHourWeekDay());
        String openE = String.valueOf(clinic.get_openingHourWeekEnd());
        String closeE =String.valueOf(clinic.get_closingHourWeekEnd());
        String rate = String.valueOf(clinic.get_rate());

        String all = "Address: "+address + ", Monday to Friday: "+openD+" - "+closeD+", Saturday/Sunday: "+openE+" - "+closeE+", Rating: "+rate;

        textViewName.setText(clinic.get_name());
        textViewAll.setText(all);

        return listViewItem;
    }
}