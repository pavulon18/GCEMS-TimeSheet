package gcems.timesheet;


import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Author:  Jim Baize
 * Copyright:  2017
 * 
 * The purpose of this class is to create the GUI to accept all the info needed to 
 * correctly determine the type of work day and hours.
 * 
 * When the "+" button is clicked a popup window will be displayed.  
 * This pop up window will determine if the hours being entered are half shift, whole shift
 * PTO days, Holidays, etc.
 * Once the data has been collected, the
 * data will then be displayed on this main page in a more compressed format.
 */

public class WorkDayEntryGUI
{
    VBox customWorkDay = new VBox();
    HBox cwdLine1 = new HBox();
    HBox cwdLine2 = new HBox();
    HBox cwdLine3 = new HBox();
    DatePicker dp = new DatePicker();
    Button btnNewLine = new Button("+");
    Button btnRemoveLine = new Button("-");
    Label lblRegHours = new Label("Regular Hours: ");
    Label lblVarRegHours = new Label("0");
    Label lblOTHours = new Label("Overtime Hours:  ");
    Label lblVarOTHours = new Label("0");
    
    public WorkDayEntryGUI()
            {
                cwdLine1.getChildren().addAll(dp, lblRegHours, lblOTHours, lblVarOTHours);
                cwdLine2.getChildren().addAll(lblRegHours, lblVarRegHours, lblOTHours, lblVarOTHours);
                cwdLine3.getChildren().add(btnNewLine);
            }
}