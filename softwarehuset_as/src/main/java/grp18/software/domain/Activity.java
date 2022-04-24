package grp18.software.domain;

import java.util.Calendar;

public class Activity {
    private String activityName;
    private Calendar startDate;
    private Calendar endDate;


    public Activity(String activityName, Calendar startDate, Calendar endDate){
        this.activityName = activityName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getActivityName() {
        return activityName;
    }
}