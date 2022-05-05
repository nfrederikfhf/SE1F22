package grp18.software.domain;

import grp18.software.app.IllegalDateException;
import grp18.software.tools.StringToCalender;

import java.util.GregorianCalendar;
//Niels Class Owner
public class Event {
    private GregorianCalendar startTime;
    private GregorianCalendar endTime;
    private GregorianCalendar date;
    private Long hoursWorked;
    private Activity relatedActivity;
    private int ID;

    public Event(String startTime, String endTime, String date, Activity relatedActivity, int ID) throws IllegalDateException {
        try{
            StringToCalender timeData = new StringToCalender(date, startTime, endTime);
            this.startTime = timeData.startTimeCal;
            this.endTime = timeData.endTimeCal;
            this.date = timeData.dateCal;
        } catch (IllegalDateException e){
            throw e;
        }
        this.hoursWorked = (this.endTime.getTimeInMillis() - this.startTime.getTimeInMillis()) / 3600000;
        this.relatedActivity = relatedActivity;
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

    //Niels
    public void setTimeframe(String date, String startTime, String endTime) throws IllegalDateException{
        StringToCalender dateData = null;
        try {
            dateData = new StringToCalender(date, startTime, endTime);
        } catch (IllegalDateException e) {
            throw e;
        }
        this.date = dateData.dateCal;
        this.startTime = dateData.startTimeCal;
        this.endTime = dateData.endTimeCal;
    }

    public Activity getRelatedActivity(){
        return relatedActivity;
    }

}
