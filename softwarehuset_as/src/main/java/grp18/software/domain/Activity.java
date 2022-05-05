package grp18.software.domain;

import grp18.software.app.IllegalDateException;
import grp18.software.tools.StringToCalender;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Activity {
    private String activityName;
    private Calendar startDate;
    private Calendar endDate;
    private List<Worker> workers = new ArrayList<>();

    public Activity(String activityName, String startDate, String endDate) throws IllegalDateException {
        this.activityName = activityName;
        try{
            StringToCalender startDatedata = new StringToCalender(startDate,"0,0", "0,0");
            StringToCalender endDatedata = new StringToCalender(endDate,"0,0", "0,0");
            this.startDate = startDatedata.dateCal;
            this.endDate = endDatedata.dateCal;
        } catch (IllegalDateException e){
            throw e;
        }
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

    public void getStatusReport(String prefix, StringBuilder stringBuilder){

        stringBuilder.append(prefix.substring(0,prefix.length()-3)+"|__" +this.activityName+":\n");
        int i = 0;
        for (Worker worker : workers) {
            stringBuilder.append(prefix + "|__" + worker.getInitials()+"\n");

            int hours = worker.getHoursWorkedOnActivity(this);

            if (i++ == workers.size() - 1) {
                stringBuilder.append(prefix + "   |__Hours worked: " + hours+"\n");
            } else {
                stringBuilder.append(prefix + "|  |__Hours worked: " + hours+"\n");
            }

        }
    }
}