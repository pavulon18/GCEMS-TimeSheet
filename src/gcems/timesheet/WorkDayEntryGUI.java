package gcems.timesheet;


import java.util.ArrayList;
import javafx.beans.value.ObservableValue;
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
    Label lblCustomIn = new Label("In");
    Label lblCustomOut = new Label("Out");
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
            
            makeDetailedDataEntry();
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

    private void makeDetailedDataEntry()
    {
        final Stage dataEntryStage = new Stage();
        CheckBox chkBoxHoliday = new CheckBox();
        chkBoxHoliday.setText("Holiday");
        ComboBox<String> cboShiftDuration = new ComboBox<>();
        ComboBox<String> cboPTO = new ComboBox<>();
        
        Button btnSaveData = new Button("Save");
        HBox hBoxDataEntry = new HBox();
        HBox hBoxLineTwo = new HBox();
        HBox hBoxDataEntryThree = new HBox();
        VBox root = new VBox();
        
        TimeDials cboCustomHourIn = new TimeDials();
        TimeDials cboCustomMinuteIn = new TimeDials();
        TimeDials cboCustomHourOut = new TimeDials();
        TimeDials cboCustomMinuteOut = new TimeDials();
        
        
        cboPTO.getItems().addAll("Sick Day", "Vacation Day", "Personal Day");
        cboShiftDuration.getItems().addAll("First Half", "Second Half", "24 Hour Shift", "Custom Hours");
        cboShiftDuration.getSelectionModel().select("24 Hour Shift");
        cboShiftDuration.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> obs, String oldVal, String newVal) ->
            {
                //hBoxDataEntryThree.getChildren().remove(lblFirstHalf);
                //hBoxDataEntryThree.getChildren().remove(lblSecondHalf);
                //hBoxDataEntryThree.getChildren().remove(lblWholeShift);
                //hBoxDataEntryThree.getChildren().remove(lblCustomShift);
                switch(newVal)
                {
                    case "First Half":
                        //cwdLine1.getChildren().add(lblFirstHalf);
                        //hoursSingleShift = 12;
                        break;
                    case "Second Half":
                        //cwdLine1.getChildren().add(lblSecondHalf);
                        //hoursSingleShift = 12;
                        break;    
                    case "Whole Shift":
                        //cwdLine1.getChildren().add(lblWholeShift);
                        //hoursSingleShift = 16;
                        break;
                    case "Custom Hours":
                        hBoxDataEntryThree.getChildren().add(lblCustomIn);
                        hBoxDataEntryThree.getChildren().add(cboCustomHourIn.makeHourDial());
                        hBoxDataEntryThree.getChildren().add(cboCustomMinuteIn.makeMinuteDial());
                        hBoxDataEntryThree.getChildren().add(lblCustomOut);
                        hBoxDataEntryThree.getChildren().add(cboCustomHourOut.makeHourDial());
                        hBoxDataEntryThree.getChildren().add(cboCustomMinuteOut.makeMinuteDial());
                        break;
                    default:
                        break;
                }
            });
        
        btnSaveData.setOnAction(e ->
        {
            dataEntryStage.close();
        });
 
        
        hBoxDataEntry.getChildren().addAll(chkBoxHoliday, cboShiftDuration, cboPTO);
        hBoxLineTwo.getChildren().add(btnSaveData);
        root.getChildren().addAll(hBoxDataEntry, hBoxLineTwo, hBoxDataEntryThree);
        
        Scene DetailedDataEntryScene = new Scene(root);
        dataEntryStage.setScene(DetailedDataEntryScene);
        dataEntryStage.initModality(Modality.APPLICATION_MODAL);
        dataEntryStage.show();
    }

    public <T extends Node> T makeWorkDay()
        {
            return (T) customWorkDay;
        }
}