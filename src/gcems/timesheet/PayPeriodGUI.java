package gcems.timesheet;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

/* 
    Our pay periods consist of two weeks.  They start on Thursday at 0800 and end
    at Friday at 075959.  With the way our pay period works, however, this is not a 
    set in stone issue.  If an employee is on a run when the day ends, the time will go
    on the first day, not the second day.
    
    I am also trying to simply the impementation.  It wasn't working exactly as I had it set up
    so I am trying a different approach.
    */

public class PayPeriodGUI
{
    //PayPeriodGUI[] PayPeriod_array;
    HBox hboxPayPeriod = new HBox();
    WorkWeekGUI workWeekOne;
    WorkWeekGUI workWeekTwo;
    
    LocalDate firstThursday;
    
    public PayPeriodGUI()
    { 
        workWeekOne = new WorkWeekGUI();
        workWeekTwo = new WorkWeekGUI();
        //PayPeriod_array = new PayPeriodGUI[2];
        //PayPeriod_array[0] = new PayPeriodGUI();
        //PayPeriod_array[1] = new PayPeriodGUI();
        
        //WorkDayEntryGUI[][] WorkDay_array = new WorkDayEntryGUI[2][7];
        //WorkDay_array[k][l] = new WorkDayEntryGUI();
        
        System.out.println("Making of PayPeriodGUI");
        
        hboxPayPeriod.getChildren().add(workWeekOne.makeWorkWeekOne());
        hboxPayPeriod.getChildren().add(workWeekTwo.makeWorkWeekTwo());
    }
    
    public <T extends Node> T makePayPeriod()
    {
        System.out.println("start of makePayPeriod()");
        return (T) hboxPayPeriod;
    }
    
}

