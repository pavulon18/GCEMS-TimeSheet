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

    public FirstThursday()
    {
        this.calendarDate = LocalDate.now();
        this.referenceDate = LocalDate.of(2017,6,29);
        System.out.println("This is the FirstThursday class constructor");
    }
    
    public boolean isFirstThursday()
    {
        System.out.println("This is the isFirstThursday method");
        System.out.println(calendarDate.compareTo(referenceDate));
        System.out.println("Calendar Date " + calendarDate);
        System.out.println("Reference Date " + referenceDate);
        
        long p = referenceDate.until(calendarDate, ChronoUnit.DAYS);
        System.out.println("Difference between days = " + p);
        System.out.println("Mod of difference = " + referenceDate.until(calendarDate, ChronoUnit.DAYS) % 14);
        
        return (calendarDate.isAfter(referenceDate)) &&
                ((referenceDate.until(calendarDate, ChronoUnit.DAYS)) % 14) == 0;
    }
}
