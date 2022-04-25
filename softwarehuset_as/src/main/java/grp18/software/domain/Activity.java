package grp18.software.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Activity {
    private String activityName;
    private Calendar startDate;
    private Calendar endDate;
    private List<Worker> workers = new ArrayList<>();


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

    public List<Worker> getWorkers(){
        return this.workers;
    }

}