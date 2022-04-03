package grp18.software.domain;

import io.cucumber.java.bs.A;

import java.util.ArrayList;
import java.util.List;

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

}
