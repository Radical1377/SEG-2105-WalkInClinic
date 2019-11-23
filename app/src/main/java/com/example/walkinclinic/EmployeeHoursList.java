package com.example.walkinclinic;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.walkinclinic.R;
import com.example.walkinclinic.WalkInClinic;
import com.example.walkinclinic.data.WorkHours;
import com.google.firebase.database.DatabaseReference;

import java.util.List;
//***************
//class to define what will be displayed in every ListView element
//in HoursActivity activity and XML layout
/*
public class EmployeeHoursList extends ArrayAdapter<WorkHours> {
    private Activity context;
    List<WorkHours> workHours; //list of employees, will modify working hours directly in their profile

    public EmployeeHoursList(Activity context, List<WorkHours> workHours) {
        super(context, R.layout.employee_hours_list, workHours);
        this.context = context;
        this.workHours = workHours;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_clinic_list, null, true);

        final WorkHours Hours=workHours.get(position);

        //Buttons
        Button setMonBtn =(Button) listViewItem.findViewById(R.id.setMonday);
        Button setTuesBtn =(Button) listViewItem.findViewById(R.id.setTuesday);
        Button setWedBtn =(Button) listViewItem.findViewById(R.id.setWednesday);
        Button setThurBtn =(Button) listViewItem.findViewById(R.id.setThursday);
        Button setFriBtn =(Button) listViewItem.findViewById(R.id.setFriday);
        Button setSatBtn =(Button) listViewItem.findViewById(R.id.setSaturday);
        Button setSunBtn =(Button) listViewItem.findViewById(R.id.setSunday);


        //all the hour displays for day in the week

        TextView _hoursMonday = (TextView) listViewItem.findViewById(R.id.hoursMonday);
        TextView _hoursTuesday = (TextView) listViewItem.findViewById(R.id.hoursTuesday);
        TextView _hoursWednesday = (TextView) listViewItem.findViewById(R.id.hoursWednesday);
        TextView _hoursThursday = (TextView) listViewItem.findViewById(R.id.hoursThursday);
        TextView _hoursFriday = (TextView) listViewItem.findViewById(R.id.hoursFriday);
        TextView _hoursSat = (TextView) listViewItem.findViewById(R.id.hoursSaturday);
        TextView _hoursSun = (TextView) listViewItem.findViewById(R.id.hoursSunday);

        //setting the text
        _hoursMonday.setText(employee.getDayHours("Monday").displayHours());
        _hoursTuesday.setText(employee.getDayHours("Tuesday").displayHours());
        _hoursWednesday.setText(employee.getDayHours("Wednesday").displayHours());
        _hoursThursday.setText(employee.getDayHours("Thursday").displayHours());
        _hoursFriday.setText(employee.getDayHours("Friday").displayHours());
        _hoursSat.setText(employee.getDayHours("Saturday").displayHours());
        _hoursSun.setText(employee.getDayHours("Sunday").displayHours());

        //Edit hours for days of the week (opening)
        final EditText _openHoursMonday = (EditText) listViewItem.findViewById(R.id.openMondayHours);
        final EditText _openHoursTuesday = (EditText) listViewItem.findViewById(R.id.openTuesdayHours);
        final EditText _openHoursWednesday = (EditText) listViewItem.findViewById(R.id.openWednesdayHours);
        final EditText _openHoursThursday = (EditText) listViewItem.findViewById(R.id.openThursdayHours);
        final EditText _openHoursFriday = (EditText) listViewItem.findViewById(R.id.openFridayHours);
        final EditText _openHoursSat = (EditText) listViewItem.findViewById(R.id.openSaturdayHours);
        final EditText _openHoursSun = (EditText) listViewItem.findViewById(R.id.openSundayHours);

        //Edit hours for days of the week (close)
        final EditText _closeHoursMonday = (EditText) listViewItem.findViewById(R.id.closeMondayHours);
        final EditText _closeHoursTuesday = (EditText) listViewItem.findViewById(R.id.closeTuesdayHours);
        final EditText _closeHoursWednesday = (EditText) listViewItem.findViewById(R.id.closeWednesdayHours);
        final EditText _closeHoursThursday = (EditText) listViewItem.findViewById(R.id.closeThursdayHours);
        final EditText _closeHoursFriday = (EditText) listViewItem.findViewById(R.id.closeFridayHours);
        final EditText _closeHoursSat = (EditText) listViewItem.findViewById(R.id.closeSaturdayHours);
        final EditText _closeHoursSun = (EditText) listViewItem.findViewById(R.id.closeSundayHours);

        //onClick listeners for buttons to set/update the hours
        setMonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day="Monday";
                double open=Double.parseDouble(_openHoursMonday.getText().toString().trim());
                double close=Double.parseDouble(_closeHoursMonday.getText().toString().trim());

                //TO_DO check here if within clinic hours
                //*******************

                //check if day hour already exists (if nonnull so exists something)
                if (employee.getDayHours(day)!=null) {
                    changeHours(day, open, close, employee); //call to change hours for this day
                } else {
                    //it doesn't yet exist so have to create
                    setHours(day, open, close, employee);
                }
            }
        });


        return listViewItem;
    }

    public void changeHours(String day, double open, double close, Employee employee){

        WorkHours workHours=new WorkHours(day, open, close);
        employee.set_dayHours(day, workHours);

        //save to firebase HOW

    }
    public void setHours(String day, double open, double close, Employee employee){
        //create new WorkingHours for that day
        WorkHours workHours=new WorkHours(day, open, close);

        //add it to the employee file
        employee.set_workHours(workHours);

        //save to firebase HOW


    }
}

 */
