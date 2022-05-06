package grp18.software.domain;

import grp18.software.app.EventOverlapException;
import grp18.software.app.IllegalDateException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//William
public class Worker {
    private String initials;
    private List<Activity> activities = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
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

    //William
    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    //Niels
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

            if (oldstart >= newstart && oldend <= newend) {                 //5
                return false;
            }
        }
        return true;
    }

    //Niels
    public void registerHours(String startTime, String endTime, String date, Activity relatedActivity) throws EventOverlapException, IllegalDateException {

        int ID = events.size() + 1; //ID 0 reserved for dummy ID in editEvent'
        Event event = null;
        try {
            event = new Event(startTime, endTime, date, relatedActivity, ID);
        } catch (IllegalDateException e) {
            throw e;
        }
        if (!validateNoEventOverlap(event)) {
            throw new EventOverlapException("Event is overlapping another event");
        }
        events.add(event);
    }

    public Event getEventFromID(int ID) {
        return this.events.stream().filter(x -> x.getID() == ID).findFirst().orElse(null);
    }

    //Jacob
    public void editEvent(int eventID, String newStartTime, String newEndTime, String newDate) throws EventOverlapException, IllegalDateException {

        Event oldEvent = getEventFromID(eventID);
        events.remove(oldEvent);
        try {
            Event newEvent = new Event(newStartTime, newEndTime, newDate, null, 0);
            if (!validateNoEventOverlap(newEvent)) {
                events.add(oldEvent);
                throw new EventOverlapException("Event is overlapping another event");
            }
            oldEvent.setTimeframe(newDate, newStartTime, newEndTime);
            events.add(oldEvent);
        } catch (IllegalDateException e) {
            throw e;
        }
    }

    public List<Event> getEvents() {
        return this.events;
    }

    //Jacob
    public double getHoursWorkedOnActivity(Activity activity) {
        assert true; //No pre condition
        if (activity == null) {     //1
            return 0;
        }
        double sum = 0;
        double sumStartOfLoop;
        for (Event event : this.getEvents()) {           //2
            sumStartOfLoop = sum;
            if (event.getRelatedActivity() == activity) {   //3
                sum += event.getHoursWorked();
            }
            assert (sum >= sumStartOfLoop); //Loop invariant condition
        }
        assert (sum >= 0); //Post condition
        assert (this.getEvents().stream()
                .filter(anyEvent -> anyEvent.getRelatedActivity() == activity)
                .map(Event::getHoursWorked)
                .mapToDouble(hoursWorked -> hoursWorked)
                .sum() == sum); //Post conditions
        return sum;
    }
}