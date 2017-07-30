/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcems.timesheet;

import java.time.LocalDate;

/**
 *
 * @author Jim Baize Copyright 2017
 *
 * This class, as is planned right now, will be the class which gathers the data
 * from the GUI class and stores the data for later use.
 */
public class DataKeeper
{

    LocalDate firstThursday;
    LocalDate referenceDate;
    
    public DataKeeper()
    {
        referenceDate = LocalDate.of(2017,6,29);
    }
    
    public LocalDate getFirstThursday()
    {
        return firstThursday;
    }
    
    public void setFirstThursday(LocalDate firstThursday)
    {
        this.firstThursday = firstThursday;
    }

}
