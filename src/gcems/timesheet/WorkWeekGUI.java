/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcems.timesheet;

import java.time.LocalDate;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jim Baize
 */
public class WorkWeekGUI
{
    WorkDayEntryGUI[] WorkWeekOne_array;
    VBox vboxWeekOne;
    WorkDayEntryGUI[] WorkWeekTwo_array;
    VBox vboxWeekTwo;
    LocalDate firstDate;
    
    public WorkWeekGUI()
    {
        vboxWeekOne = new VBox();
        vboxWeekTwo = new VBox();
        
    }
    
    public <T extends Node> T makeWorkWeekOne()
    {
        System.out.println("Making of WorkWeekOne");
        WorkWeekOne_array = new WorkDayEntryGUI[7]; 
        for (int j = 0; j < 7; j++)                 
        {
            WorkWeekOne_array[j] = new WorkDayEntryGUI();
        }
        
        vboxWeekOne.getChildren().add(WorkWeekOne_array[0].makeFirstThursday());    //This is the First Thursday.  It is the only one with a DatePicker object
        
        
        for(int j=1; j<7; j++)                                                      //This loop creates the rest of the 6 days in Week one.
        {
            vboxWeekOne.getChildren().add(WorkWeekOne_array[j].makeWorkDay(j));
            
        }
        
        return (T) vboxWeekOne;
    }
    
    public <T extends Node> T makeWorkWeekTwo()
    {
        
        WorkWeekTwo_array = new WorkDayEntryGUI[7];
        for (int j = 0; j < 7; j++)
        {
            WorkWeekTwo_array[j] = new WorkDayEntryGUI();
        }
        
        for(int j=0; j<7; j++)
        {
            vboxWeekTwo.getChildren().add(WorkWeekTwo_array[j].makeWorkDay(j));
        }
        return (T) vboxWeekTwo;
    }
}
