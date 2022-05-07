package grp18.software.domain;

import grp18.software.app.ActivityNotFoundException;
import grp18.software.app.IllegalDateException;
import grp18.software.app.OperationNotAllowedException;
import grp18.software.app.RegistrationApp;
import grp18.software.tools.StringToCalender;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

//Jacob Class Owner
public class Project{
    private String name;
    private List<Activity> activities = new ArrayList<>();
    private List<Worker> workers = new ArrayList<>();
    private int ID;
    private Worker projectManager;
    private Calendar startDate;
    private Calendar endDate;

    public Project(String name) throws IllegalDateException {
        this(name, "0,0,0", "0,0,0");
    }

    public Project(String name, String startDate, String endDate) throws IllegalDateException {
            this.name = name;
            this.ID = 0;
            try {
                StringToCalender startDatedata = new StringToCalender(startDate, "0,0", "0,0");
                StringToCalender endDatedata = new StringToCalender(endDate, "0,0", "0,0");
                this.startDate = startDatedata.dateCal;
                this.endDate = endDatedata.dateCal;
            } catch (IllegalDateException e){
                throw e;
            }

        }
    //William
    public void assignManager(Worker projectManager) throws OperationNotAllowedException{
        if(projectManager == null) {
            throw new OperationNotAllowedException("Worker not found");
        }
        this.projectManager = projectManager;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public int getID(){
        return this.ID;
    }
    //Jacob
    public Activity getActivityFromName(String name){
        return this.activities.stream().filter(x -> Objects.equals(x.getActivityName(), name)).findFirst().orElse(null);
    }
    //William
    public Activity checkActivityName(String name) throws ActivityNotFoundException {
        if(getActivityFromName(name) == null) {
            throw new ActivityNotFoundException("Activity not found");
        }
        return getActivityFromName(name);
    }
    //William
    public void addActivity (Activity activity) throws OperationNotAllowedException {
        if (getActivityFromName(activity.getActivityName())!=null){
            throw new OperationNotAllowedException("Name for activity already used in the project");
        }
        activities.add(activity);
    }
    //William
    public void addWorkerToActivity(Worker worker, Activity activity) throws OperationNotAllowedException{
        assert activity != null; // Pre condition

        if (activity.getWorkers().contains(worker)){ // 1
            throw new OperationNotAllowedException("Worker Already exists in database");
        }
        if(worker == null){ // 2
            throw new OperationNotAllowedException("The worker is invalid");
        }
        worker.addActivity(activity); // 3
        activity.addWorker(worker); // 4

        assert activity.getWorkers().contains(worker) && worker.getActivities().contains(activity); // Post conditions
    }

    public List<Worker> getWorkers(){
        return this.workers;
    }

    //Jacob
    public void addWorker(Worker worker) throws OperationNotAllowedException{
        if (this.workers.contains(worker)){
            throw new OperationNotAllowedException("Worker already in project");
        }
        this.workers.add(worker);
    }

    //Jacob
    public void getStatusReport(String prefix, StringBuilder stringBuilder){
        String p = "";

        stringBuilder.append("|__"+this.name+":\n");
        if (this.projectManager != null) {
            stringBuilder.append("|  PM: " + this.projectManager.getInitials()+"\n");
        }
        for (Activity activity : this.getActivities()){

            Boolean isLast = activity == this.getActivities().get(this.getActivities().size() - 1);

            if (isLast){
                p = prefix +"   ";
            }else{
                p = prefix +"|  ";
            }

            activity.getStatusReport(p,stringBuilder);
        }
    }

    public List<Activity> getActivities(){
        return this.activities;
    }


}
