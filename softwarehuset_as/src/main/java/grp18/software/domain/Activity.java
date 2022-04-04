package grp18.software.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Activity {
    private String activityName;
    private Calendar startDate;
    private Calendar endDate;
    private Worker worker;

    private String initials;
    private List<Activity> activities = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
    private List<Project> managementList = new ArrayList<>();

    public Activity(String initials){
        this.initials = initials;
    }

    public String getInitials() {
        return initials;
    }

}
