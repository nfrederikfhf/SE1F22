package grp18.software.domain;

import grp18.software.app.OperationNotAllowedException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Objects;

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

    public void setActivityName (String activityName){
        this.activityName = activityName;
    }

    public List<Worker> getWorkers(){
        return this.workers;
    }

    public void printStatusReport(String prefix){


        System.out.println(prefix.substring(0,prefix.length()-3)+"|__" +this.activityName+":");
        int i = 0;
        for (Worker worker : workers){
            System.out.println(prefix+"|__"+worker.getInitials());

            int hours = worker.getHoursWorkedOnActivity(this);

            if(i++ == workers.size() - 1){
                System.out.println(prefix +"   |__Hours worked: "+ hours );
                System.out.println(prefix);
            }else {
                System.out.println(prefix + "|  |__Hours worked: " + hours);
                System.out.println(prefix +"|" );
            }

        }
        //System.out.println(prefix);
    }
}