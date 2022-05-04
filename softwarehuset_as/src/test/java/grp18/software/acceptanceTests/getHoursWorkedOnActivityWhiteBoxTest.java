package grp18.software.acceptanceTests;

import grp18.software.app.EventOverlapException;
import grp18.software.domain.Activity;
import grp18.software.domain.Worker;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class getHoursWorkedOnActivityWhiteBoxTest extends TestCase{

    private Worker worker = new Worker("JHM");
    private Activity activity1 = new Activity("Activity 1","2022,06,01","2022,06,10");
    private Activity activity2 = new Activity("Activity 2","2022,06,01","2022,06,10");

    @Test
    @DisplayName("Case A")
    public void getHoursWorkedOnActivityWhiteBoxTestCaseA(){

        Assertions.assertEquals(0, worker.getHoursWorkedOnActivity(activity1));
    }

    @Test
    @DisplayName("Case B")
    public void getHoursWorkedOnActivityWhiteBoxTestCaseB() throws EventOverlapException {

        worker.registerHours("09,00","17,00","2022,06,02",activity1);

        Assertions.assertEquals(8, worker.getHoursWorkedOnActivity(activity1));
    }

    @Test
    @DisplayName("Case C")
    public void getHoursWorkedOnActivityWhiteBoxTestCaseC() throws EventOverlapException {

        worker.registerHours("09,00","17,00","2022,06,02",activity2);

        Assertions.assertEquals(0,worker.getHoursWorkedOnActivity(activity1));
    }
    @Test
    @DisplayName("Case D")
    public void getHoursWorkedOnActivityWhiteBoxTestCaseD() throws EventOverlapException {

        worker.registerHours("09,00","17,00","2022,06,02",activity1);
        worker.registerHours("09,00","17,00","2022,06,03",activity2);

        Assertions.assertEquals(8,worker.getHoursWorkedOnActivity(activity1));
    }

    @Test
    @DisplayName("Case E")
    public void getHoursWorkedOnActivityWhiteBoxTestCaseE() throws EventOverlapException {

        activity1.addWorker(worker);
        worker.registerHours("09,00","17,00","2022,06,02",activity1);
        worker.registerHours("09,00","17,00","2022,06,03",activity1);

        Assertions.assertEquals(16,worker.getHoursWorkedOnActivity(activity1));
    }
}

