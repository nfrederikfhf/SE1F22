package grp18.software.domain;

import java.util.Calendar;

public class Event {
    private Calendar startTime;
    private Calendar endTime;
    private int hoursWorked;
    private Activity relatedActivity;
    private Calendar date;

    public Event(Calendar startTime, Calendar endTime, int hoursWorked, Activity relatedActivity, Calendar date){
        this.startTime = startTime;
        this.endTime = endTime;
        this.hoursWorked = hoursWorked;
        this.relatedActivity = relatedActivity;
        this.date = date;
    }

}
