package grp18.software.dto;

import java.util.Calendar;

public class ActivityInfo {

    private String activityName;
    private Calendar startDate;
    private Calendar endDate;
    private int timeBudget;

    public ActivityInfo(String activityName, Calendar startDate, Calendar endDate, int timeBudget){
    this.activityName = activityName;
    this.startDate = startDate;
    this.endDate = endDate;

    }

    private void addWorker(Worker worker){

    }

    private void deleteWorker(Worker worker){

    }

    private String activityStatus(){

    }


}
