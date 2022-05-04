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

    public Worker(String initials) {
        this.initials = initials;
    }

    public String getInitials() {
        return initials;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setProjectManager(Boolean boolVal) {
        projectManager = boolVal;
    }

    public boolean getProjectManager() {
        return projectManager;
    }

    public void addManagedProject(int projectID) {
        Project project = RegistrationApp.INSTANCE.getProjectFromID(projectID);
        managementList.add(project);
    }

    public int getAmountOfActivities() {
        return activities.size();
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public Boolean validateNoEventOverlap(Event newEvent) {
        List<Event> sameDateEvents = this.events.stream().filter(x -> x.getDate().equals(newEvent.getDate())).collect(Collectors.toList());

        for (Event oldEvent : sameDateEvents) {
            long oldstart = oldEvent.getStartTime().getTime().getTime();
            long oldend = oldEvent.getEndTime().getTime().getTime();
            long newstart = newEvent.getStartTime().getTime().getTime();
            long newend = newEvent.getEndTime().getTime().getTime();

            if (oldstart <= newstart && oldend >= newstart) {
                return false;
            }
            if (oldstart <= newend && oldend >= newend) {
                return false;
            }
            if (oldstart <= newstart && oldend >= newend) {
                return false;
            }
            if (oldstart >= newstart && oldend <= newend) {
                return false;
            }
        }
        return true;
    }

    public void registerHours(GregorianCalendar startTime, GregorianCalendar endTime, GregorianCalendar date, Activity relatedActivity) throws EventOverlapException {
        int ID = events.size() + 1; //ID 0 reserved for dummy ID in editEvent
        Event event = new Event(startTime, endTime, date, relatedActivity, ID);
        if (!validateNoEventOverlap(event)) {
            throw new EventOverlapException("Event is overlapping another event");
        }
        events.add(event);
    }

    public Event getEventFromID(int ID) {
        return this.events.stream().filter(x -> x.getID() == ID).findFirst().orElse(null);
    }

    public void editEvent(int eventID, String newStartTime, String newEndTime, String newDate) throws EventOverlapException {

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