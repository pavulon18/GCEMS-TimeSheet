package gcems.timesheet;


import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    ArrayList<WorkDayEntryGUI> listWorkDayEntry;
    //DetailedDataEntry dde = new DetailedDataEntry();
    
    WorkDayEntryGUI()
    {
        listWorkDayEntry = new ArrayList<>();
        cwdLine1.getChildren().addAll(dp, lblRegHours, lblOTHours, lblVarOTHours);
        cwdLine2.getChildren().addAll(lblRegHours, lblVarRegHours, lblOTHours, lblVarOTHours);
        cwdLine3.getChildren().add(btnNewLine);

        btnNewLine.setOnAction((ActionEvent event) ->
        {
            final Stage dataEntryStage = new Stage();
            CheckBox chkBoxHoliday = new CheckBox();
            ComboBox<String> cboPTO = new ComboBox<>();
            ComboBox cboShiftDuration = new ComboBox();
            VBox vBoxHolidayPTO = new VBox();
            HBox hBoxDataEntry = new HBox();
            chkBoxHoliday.setText("Holiday");
            cboPTO.getItems().addAll("Sick Day", "Vacation Day", "Personal Day");
            cboShiftDuration.getItems().addAll("First Half", "Second Half", "24 Hour Shift");
            cboShiftDuration.getSelectionModel().select("24 Hour Shift");
            
            hBoxDataEntry.getChildren().addAll(chkBoxHoliday, cboShiftDuration, cboPTO);
            
            Scene DetailedDataEntryScene = new Scene(hBoxDataEntry);
            dataEntryStage.setScene(DetailedDataEntryScene);
            dataEntryStage.initModality(Modality.APPLICATION_MODAL);
            dataEntryStage.show();
        });

        btnRemoveLine.setOnAction((ActionEvent e)->
            {
                /*
                *This button will delete the selected line(s)
                */
            });
        
        customWorkDay.getChildren().add(cwdLine1);
        customWorkDay.getChildren().add(cwdLine2);
        customWorkDay.getChildren().add(cwdLine3);
    }

    public <T extends Node> T makeWorkDay()
        {
            return (T) customWorkDay;
        }
}