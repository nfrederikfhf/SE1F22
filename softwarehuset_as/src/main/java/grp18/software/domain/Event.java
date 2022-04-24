package grp18.software.domain;

import java.util.Calendar;

public class Event {
    private Calendar startTime;
    private Calendar endTime;
    private Calendar date;
    private int hoursWorked;
    private Activity relatedActivity;
    private int ID;

    public Event(Calendar startTime, Calendar endTime, Calendar date, Activity relatedActivity, int ID){
        this.startTime = startTime;
        this.endTime = endTime;
        //this.hoursWorked =
        this.relatedActivity = relatedActivity;
        this.date = date;
        this.ID = ID;
    }

    public int getID(){
        return this.ID;
    }

    public Calendar getStartTime(){
        return this.startTime;
    }

    public Calendar getEndTime(){
        return this.endTime;
    }

    public Calendar getDate(){
        return this.date;
    }

    public void setStartTime(Calendar startTime){
        this.startTime = startTime;
    }

    public void setEndTime(Calendar endTime){
        this.endTime = endTime;
    }
    public void setDate(Calendar date){
        this.date = date;
    }

    public Activity getRelatedActivity(){
        return relatedActivity;
    }

}
