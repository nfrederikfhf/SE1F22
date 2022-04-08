package grp18.software.app;

import grp18.software.domain.Project;
import grp18.software.domain.Worker;

import java.util.*;

public class RegistrationApp extends Observable{

    private List<Worker> workers = new ArrayList<>();
    private  List<Project> projects = new ArrayList<>();
    private DateServer dateServer = new DateServer();
    public static final RegistrationApp INSTANCE = new RegistrationApp();


    public Project getProjectFromID(int projectID) {
        return projects.stream().filter(x -> x.getID()==(projectID)).findFirst().orElse(null);
    }

}
