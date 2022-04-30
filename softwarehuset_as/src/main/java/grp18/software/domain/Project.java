package grp18.software.domain;

import grp18.software.app.OperationNotAllowedException;
import grp18.software.app.RegistrationApp;
import grp18.software.app.TooManyActivitiesException;
import grp18.software.domain.Worker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Project{
    private String name;
    private List<Activity> activities = new ArrayList<>();
    private List<Worker> workers = new ArrayList<>();
    private int ID;
    private Worker projectManager;
    private Calendar startDate;
    private Calendar endDate;
    private RegistrationApp RApp;

    public Project(String name){
        this(name,null,null);
    }

    public Project(String name, Calendar startDate, Calendar endDate){
            this.name = name;
            this.ID = 0;
            this.startDate = startDate;
            this.endDate = endDate;
        }

    public void assignManager(Worker projectManager){
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

    public void setManager(Worker worker){
        projectManager = worker;
    }

    public Activity getActivityFromName(String name){
        return this.activities.stream().filter(x -> Objects.equals(x.getActivityName(), name)).findFirst().orElse(null);
    }

    public void addActivity (Activity activity) throws OperationNotAllowedException {
        if (getActivityFromName(activity.getActivityName())!=null){
            throw new OperationNotAllowedException("Name for activity already used in the project");
        }
        activities.add(activity);
    }

    public void addWorkerToActivity(Worker worker, Activity activity) throws OperationNotAllowedException{
        if (activity.getWorkers().contains(worker)){
            throw new OperationNotAllowedException("Worker Already exists in database");
        }
        worker.addActivity(activity);
        activity.addWorker(worker);
    }

    public List<Worker> getWorkers(){
        return this.workers;
    }

    public void addWorker(Worker worker) throws OperationNotAllowedException{
        if (this.workers.contains(worker)){
            throw new OperationNotAllowedException("Worker already in project");
        }
        this.workers.add(worker);
    }

}
