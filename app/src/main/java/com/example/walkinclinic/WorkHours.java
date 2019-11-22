package com.example.walkinclinic;

public class WorkHours {
    private String day; //what day is the hour for?
    private double _startingHour;
    private double _endingHour;
    private String[] dayArray= {"Sunday","Monday","Tuesday",
    "Wednesday","Thursday","Friday","Saturday"}; //can assign days when initializing

    public WorkHours() {
        //default if not yet initialised
        this._startingHour=0.00;
        this._endingHour=0.00;
    }
    public WorkHours(String day, double start, double end) {
        this.day=day;
        this._startingHour=start;
        this._endingHour=end; 
    }
    //Getters and setters
    public double getStartHours(){return this._startingHour;}
    public double get_endingHour() {return this._endingHour; }

    public String getDay() {
        return day;
    }

    public String getDayArray(int index) { return this.dayArray[index];}
    public void set_startingHour(double start){this._startingHour=start;}

    public void set_endingHour(double _endingHour) {
        this._endingHour = _endingHour;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHours(){
        return day+" "+_startingHour+" "+_endingHour;
    }

    public double totalHours(){ //total time worked in the day
        return (this._startingHour - this._endingHour);
    }
    public String displayHours() {//method used to display the working hours
        String hrs=day+" Hours: " +getStartHours()+ " to "+get_endingHour();
        return hrs;
    }
}
