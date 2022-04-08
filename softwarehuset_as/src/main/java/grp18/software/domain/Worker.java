package grp18.software.domain;

import grp18.software.app.EventOverlapException;
import io.cucumber.java.bs.A;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class Worker {
    private String initials;
    private int hoursWorked;
    private List<Activity> activities = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
    private List<Project> managementList = new ArrayList<>();
    private Boolean projectManager = false;

    public Worker(String initials){
        this.initials = initials;
    }

    public String getInitials() {
        return initials;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public int getAmountOfActivities() {
        return activities.size();
    }

    public Boolean validateNoEventOverlap(Event event){
        List<Event> sameDateEvents = this.events.stream().filter(x -> x.getDate()==event.getDate()).collect(Collectors.toList());
        for (Event e : sameDateEvents){
            if ((e.getStartTime().getTimeInMillis()<event.getStartTime().getTimeInMillis()) || (e.getEndTime().getTimeInMillis()>event.getEndTime().getTimeInMillis()) ){
                return false;
            }
            if ((e.getStartTime().getTimeInMillis()<event.getEndTime().getTimeInMillis()) || e.getEndTime().getTimeInMillis()>event.getEndTime().getTimeInMillis()){
                return false;
            }
        }
        return true;
    }

    public void registerHours(Calendar startTime, Calendar endTime, Calendar date, Activity relatedActivity) throws EventOverlapException{
        int ID = events.size()+1;
        Event event = new Event(startTime, endTime, date, relatedActivity, ID);
        if (!validateNoEventOverlap(event)){
            throw new EventOverlapException("Event is overlapping another event");
        }
        events.add(event);
    }

    public Event getEventFromID(int ID){
        return this.events.stream().filter(x -> x.getID()==ID).findFirst().orElse(null);
    }


}