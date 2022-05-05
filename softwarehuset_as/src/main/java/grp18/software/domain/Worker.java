package grp18.software.domain;

import grp18.software.app.EventOverlapException;
import grp18.software.app.IllegalDateException;
import grp18.software.app.RegistrationApp;
import grp18.software.tools.StringToCalender;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Worker {
    private String initials;
    private List<Activity> activities = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
    private List<Project> managementList = new ArrayList<>();
    private Boolean projectManager = false;

    public Worker(String initials) {
        this.initials = initials;
    }

    public String getInitials() {
        return initials;
    }

    public void setProjectManager(Boolean boolVal) {
        projectManager = boolVal;
    }

    public boolean getProjectManager() {
        return projectManager;
    }

    public int getAmountOfActivities() {
        return activities.size();
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public Boolean validateNoEventOverlap(Event newEvent) {
        List<Event> sameDateEvents = this.events.stream().filter(x -> x.getDate().equals(newEvent.getDate())).collect(Collectors.toList());

        for (Event oldEvent : sameDateEvents) {                             //1
            long oldstart = oldEvent.getStartTime().getTime().getTime();
            long oldend = oldEvent.getEndTime().getTime().getTime();
            long newstart = newEvent.getStartTime().getTime().getTime();
            long newend = newEvent.getEndTime().getTime().getTime();

            if (oldstart <= newstart && oldend >= newstart) {               //2
                return false;
            }
            if (oldstart <= newend && oldend >= newend) {                   //3
                return false;
            }
            if (oldstart <= newstart && oldend >= newend) {                 //4
                return false;
            }
            if (oldstart >= newstart && oldend <= newend) {                 //5
                return false;
            }
        }
        return true;
    }

    public void registerHours(String startTime, String endTime, String date, Activity relatedActivity) throws EventOverlapException {
        try {
            StringToCalender dateData = new StringToCalender(date, startTime, endTime);
            int ID = events.size() + 1; //ID 0 reserved for dummy ID in editEvent
            Event event = new Event(dateData.startTimeCal, dateData.endTimeCal, dateData.dateCal, relatedActivity, ID);
            if (!validateNoEventOverlap(event)) {
                throw new EventOverlapException("Event is overlapping another event");
            }
            events.add(event);
        } catch (IllegalDateException e){
            e.printStackTrace();
        }

    }

    public Event getEventFromID(int ID) {
        return this.events.stream().filter(x -> x.getID() == ID).findFirst().orElse(null);
    }

    public void editEvent(int eventID, String newStartTime, String newEndTime, String newDate) throws EventOverlapException {

        try{
            StringToCalender newDateData = new StringToCalender(newDate, newStartTime, newEndTime);
            //StringToCalender dateDataActivity = new StringToCalender("2222,02,02", "0,0", "0,0");
            //Activity dummyActivity = new Activity("dummyActivity", dateDataActivity.startTimeCal, dateDataActivity.endTimeCal);
            //Event dummyEvent = new Event(dateData.startTimeCal, dateData.endTimeCal, dateData.dateCal, dummyActivity, 0);
            Event oldEvent = getEventFromID(eventID);
            events.remove(oldEvent);
            Event newEvent = new Event(newDateData.startTimeCal, newDateData.endTimeCal, newDateData.dateCal, null, 0);
            if (!validateNoEventOverlap(newEvent)) {
                events.add(oldEvent);
                throw new EventOverlapException("Event is overlapping another event");
            }
            oldEvent.setStartTime(newDateData.startTimeCal);
            oldEvent.setEndTime(newDateData.endTimeCal);
            oldEvent.setDate(newDateData.dateCal);
            events.add(oldEvent);
        } catch (IllegalDateException e){
            e.printStackTrace();
        }
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public int getHoursWorkedOnActivity(Activity activity) {

        int sum = 0;
        for (Event event : this.getEvents()){           //1
            if(event.getRelatedActivity()==activity){   //2
                sum += event.getHoursWorked();
            }
        }
        return sum;
    }
}