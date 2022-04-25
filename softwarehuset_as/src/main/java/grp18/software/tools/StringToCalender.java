/*
Niels Frandsen

Class to convert stringformatted dates and times
from .feature tiles to calender types.
 */

package grp18.software.tools;

import java.util.GregorianCalendar;
import java.util.*;

public class StringToCalender {
    public GregorianCalendar startTimeCal;
    public GregorianCalendar endTimeCal;
    public GregorianCalendar dateCal;

    public StringToCalender(String date, String startTime, String endTime) {
        String startTimeString = date + "," + startTime;
        String endTimeString = date + "," + endTime;
        String[] dateStringList = date.split(",");
        String[] startTimeStringList = startTimeString.split(",");
        String[] endTimeStringList = endTimeString.split(",");
        ArrayList<Integer> dateListInt = new ArrayList<Integer>();
        ArrayList<Integer> stIntList = new ArrayList<Integer>();
        ArrayList<Integer> etIntList = new ArrayList<Integer>();
        for (String s : dateStringList) dateListInt.add(Integer.valueOf(s));
        for (String s : startTimeStringList) stIntList.add(Integer.valueOf(s));
        for (String s : endTimeStringList) etIntList.add(Integer.valueOf(s));
        GregorianCalendar startTimeCalender = new GregorianCalendar(stIntList.get(0).intValue(), stIntList.get(1).intValue(), stIntList.get(2).intValue(), stIntList.get(3).intValue(), stIntList.get(4).intValue());
        GregorianCalendar endTimeCalender = new GregorianCalendar(etIntList.get(0).intValue(), etIntList.get(1).intValue(), etIntList.get(2).intValue(), etIntList.get(3).intValue(), etIntList.get(4).intValue());
        GregorianCalendar dateCalender = new GregorianCalendar(dateListInt.get(0).intValue(), dateListInt.get(1).intValue(), dateListInt.get(2).intValue());
        this.startTimeCal = startTimeCalender;
        this.endTimeCal = endTimeCalender;
        this.dateCal = dateCalender;
    }

}
