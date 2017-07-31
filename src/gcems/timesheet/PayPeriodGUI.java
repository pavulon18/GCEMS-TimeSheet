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

public class PayPeriodGUI extends WorkWeekGUI
{
    PayPeriodGUI[] PayPeriod_array;
    HBox hboxPayPeriod;
    
    LocalDate firstThursday;
    
    public PayPeriodGUI()
    {
        
        PayPeriod_array = new PayPeriodGUI[2];
        
        hboxPayPeriod.getChildren().add(PayPeriod_array[0].makeWorkWeekOne());
        hboxPayPeriod.getChildren().add(PayPeriod_array[1].makeWorkWeekTwo());
        
        
    }
    
    /*public LocalDate getFirstThursday()
    {
    LocalDate firstThursday;
    return firstThursday;
    }
    
    public void setFirstThursday(LocalDate firstThursday)
    {
    this.firstThursday = firstThursday;
    }*/
    
}

