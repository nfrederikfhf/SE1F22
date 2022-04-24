package grp18.software.domain;

import grp18.software.domain.Worker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Project{
    private String name;
    private List<Activity> activities = new ArrayList<>();
    private int ID;
    private Worker projectManager;
    private Calendar startDate;
    private Calendar endDate;

    public Project(String name){
        this(name,null,null);
    }

    public Project(String name, Calendar startDate, Calendar endDate){
            this.name = name;
            //this.ID = system.projectList.length+1
            this.startDate = startDate;
            this.endDate = endDate;
        }

    public void assignManager(Worker projectManager){
        this.projectManager = projectManager;
    }


    public int getID(){
        return this.ID;
    }
    public void setManager(Worker worker){
        projectManager = worker;
    }

    public Activity getActivityFromName(String name){
        return this.activities.stream().filter(x -> Objects.equals(x.getActivityName(), name)).findFirst().orElse(null);
    }

}
