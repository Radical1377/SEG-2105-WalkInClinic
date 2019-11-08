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
        String address = clinic.getAddress();
        String open = String.valueOf(clinic.getOpeningHour());
        String close =String.valueOf(clinic.getClosingHour());
        String rate = String.valueOf(clinic.getRate());

        String all = "Address: "+address + ", Hours: "+open+" - "+close+", Rating: "+rate;

        textViewName.setText(clinic.getName());
        textViewAll.setText(all);

        return listViewItem;
    }
}