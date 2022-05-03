package grp18.software.domain;

import grp18.software.app.EventOverlapException;
import grp18.software.app.RegistrationApp;
import grp18.software.tools.StringToCalender;
import io.cucumber.java.bs.A;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public void setProjectManager(Boolean boolVal){
        projectManager = boolVal;
    }

    public boolean getProjectManager(){
        return projectManager;
    }

    public void addManagedProject(int projectID){
        Project project = RegistrationApp.INSTANCE.getProjectFromID(projectID);
        managementList.add(project);
    }

    public int getAmountOfActivities() {
        return activities.size();
    }

    public void addActivity(Activity activity){
        this.activities.add(activity);
    }

    public Boolean validateNoEventOverlap(Event event){
        List<Event> sameDateEvents = this.events.stream().filter(x -> x.getDate().equals(event.getDate())).collect(Collectors.toList());
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

    public void registerHours(GregorianCalendar startTime, GregorianCalendar endTime, GregorianCalendar date, Activity relatedActivity) throws EventOverlapException{
        int ID = events.size()+1; //ID 0 reserved for dummy ID in editEvent
        Event event = new Event(startTime, endTime, date, relatedActivity, ID);
        if (!validateNoEventOverlap(event)){
            throw new EventOverlapException("Event is overlapping another event");
        }
        events.add(event);
    }

    public Event getEventFromID(int ID){
        return this.events.stream().filter(x -> x.getID()==ID).findFirst().orElse(null);
    }

    public void editEvent(int eventID, String newStartTime, String newEndTime, String newDate) throws EventOverlapException{

        StringToCalender dateData = new StringToCalender(newDate, newStartTime, newEndTime);
        StringToCalender dateDataActivity = new StringToCalender("2222,02,02", "0,0", "0,0");
        Activity dummyActivity = new Activity("dummyActivity", dateDataActivity.startTimeCal, dateDataActivity.endTimeCal);
        Event dummyEvent = new Event(dateData.startTimeCal, dateData.endTimeCal, dateData.dateCal, dummyActivity, 0);
        if (!validateNoEventOverlap(dummyEvent)){
            throw new EventOverlapException("Event is overlapping another event");
        }
        getEventFromID(eventID).setStartTime(dateData.startTimeCal);
        getEventFromID(eventID).setEndTime(dateData.endTimeCal);
        getEventFromID(eventID).setDate(dateData.dateCal);
    }

    public List<Event> getEvents(){
        return this.events;
    }

    public int getHoursWorkedOnActivity(Activity activity){

        List<Event> events = this.getEvents(); //1

        Stream<Event> eventsRelatedToActivity= events.stream().filter(x -> x.getRelatedActivity() == activity); //2

        Stream<Long> hoursInEventRelatedToActivity = eventsRelatedToActivity.map(Event::getHoursWorked); //3

        int totalHoursRelatedToActivity = hoursInEventRelatedToActivity.mapToInt(Long::intValue).sum(); //4

        return totalHoursRelatedToActivity;
    }
}