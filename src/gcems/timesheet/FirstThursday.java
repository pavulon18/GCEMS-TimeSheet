/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcems.timesheet;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Jim Baize
 */
public class FirstThursday
{
    private final LocalDate referenceDate;
    LocalDate calendarDate;
    
    boolean firstThursday = false;

    public FirstThursday(LocalDate date)
    //public FirstThursday()
    {
        this.calendarDate = date;
        //this.calendarDate = LocalDate.now();
        this.referenceDate = LocalDate.of(2017,6,29);

    }
    
    public boolean isFirstThursday()
    {
        long p = referenceDate.until(calendarDate, ChronoUnit.DAYS);
        return (calendarDate.isAfter(referenceDate)) &&
                ((referenceDate.until(calendarDate, ChronoUnit.DAYS)) % 14) == 0;
    }
}
