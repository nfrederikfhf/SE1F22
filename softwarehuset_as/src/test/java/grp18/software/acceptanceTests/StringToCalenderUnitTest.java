package grp18.software.acceptanceTests;
import grp18.software.app.EventOverlapException;
import grp18.software.app.IllegalDateException;
import grp18.software.app.RegistrationApp;

import grp18.software.domain.Activity;
import grp18.software.domain.Event;
import grp18.software.domain.Worker;
import grp18.software.tools.StringToCalender;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//Niels
public class StringToCalenderUnitTest {

    @Test
    public void unitTestStringToCalender()throws IllegalDateException {
        StringToCalender testData = new StringToCalender("2000,01,01", "8,00", "16,00");
        GregorianCalendar dateComparisonObject = new GregorianCalendar(2000, 0, 1);
        GregorianCalendar startTimeComparisonObject = new GregorianCalendar(2000, 0, 1, 8, 0);
        GregorianCalendar endTimeComparisonObject = new GregorianCalendar(2000, 0, 1, 16, 0);
        assertEquals(testData.dateCal, dateComparisonObject);
        assertEquals(testData.startTimeCal, startTimeComparisonObject);
        assertEquals(testData.endTimeCal, endTimeComparisonObject);
    }

}
