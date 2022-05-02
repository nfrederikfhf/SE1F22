package grp18.software.domain;

import grp18.software.app.OperationNotAllowedException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Activity {
    private String activityName;
    private Calendar startDate;
    private Calendar endDate;
    private List<Worker> workers = new ArrayList<>();
    private List<Activity> activities = new ArrayList<>();
    private String editActivityName;


    public Activity(String activityName, Calendar startDate, Calendar endDate){
        this.activityName = activityName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getActivityName() {
        return this.activityName;
    }

    public void addWorker(Worker worker){
        this.workers.add(worker);
    }

    public void setActivityName (String activityName){
        this.activityName = activityName;
    }

    public List<Worker> getWorkers(){
        return this.workers;
    }


}