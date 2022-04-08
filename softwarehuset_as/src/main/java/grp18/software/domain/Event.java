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

    int getID(){
        return this.ID;
    }

    Calendar getStartTime(){
        return this.startTime;
    }

    Calendar getEndTime(){
        return this.endTime;
    }

    Calendar getDate(){
        return this.date;
    }

}
