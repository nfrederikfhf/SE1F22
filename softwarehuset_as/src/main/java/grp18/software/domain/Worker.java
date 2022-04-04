package grp18.software.domain;

import grp18.software.app.RegistrationApp;
import io.cucumber.java.bs.A;
import  grp18.software.app.*;
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

    public void setProjectManager(Boolean boolVal){
        projectManager = boolVal;
    }

    public int getAmountOfActivities() {
        return activities.size();
    }

    public void addManagedProject(int projectID){
        Project project = RegistrationApp.INSTANCE.getProjectFromID(projectID);

        managementList.add(project);
    }
}
