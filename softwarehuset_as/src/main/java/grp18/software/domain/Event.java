package grp18.software.domain;

import com.sun.source.tree.BreakTree;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Event {
    private GregorianCalendar startTime;
    private GregorianCalendar endTime;
    private GregorianCalendar date;
    private Long hoursWorked;
    private Activity relatedActivity;
    private int ID;

    public Event(GregorianCalendar startTime, GregorianCalendar endTime, GregorianCalendar date, Activity relatedActivity, int ID){
        this.startTime = startTime;
        this.endTime = endTime;
        this.hoursWorked = (endTime.getTimeInMillis() - startTime.getTimeInMillis()) / 3600000;
        this.relatedActivity = relatedActivity;
        this.date = date;
        this.ID = ID;
    }

    public int getID(){
        return this.ID;
    }

    public GregorianCalendar getStartTime(){
        return this.startTime;
    }

    public GregorianCalendar getEndTime(){
        return this.endTime;
    }

    public GregorianCalendar getDate(){
        return this.date;
    }

    public long getHoursWorked(){
        return this.hoursWorked;
    }

    public void setStartTime(GregorianCalendar startTime){
        this.startTime = startTime;
    }

    public void setEndTime(GregorianCalendar endTime){
        this.endTime = endTime;
    }
    public void setDate(GregorianCalendar date){
        this.date = date;
    }

    public Activity getRelatedActivity(){
        return relatedActivity;
    }

}
