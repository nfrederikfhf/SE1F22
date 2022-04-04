package grp18.software.domain;

import grp18.software.domain.Worker;
import java.util.Calendar;

public class Project{
    private String name;
    private int ID;
    private Worker projectManager;
    private Calendar startDate;
    private Calendar endDate;

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


}
