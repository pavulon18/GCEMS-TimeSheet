/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcems.timesheet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Jim Baize
 * Copyright: 2017
 * This project was started on July 1, 2017
 */
public class GCEMSTimeSheetGUI extends Application
{
    double weekHoursPTO = 0;    //hoursPTO = the number of paid hours for vacation days, personal days, sick days
        //and the 8 hours of holiday hours.
        
    double weekHoursOT = 0;    //hoursOT = Number of overtime hours.  These may be from hours worked over 40 hours per week
        //or hours over scheduled shift times
        
    double weekHoursReg = 0;    //hoursReg = Number of hours which will be paid at regular pay.
    int nightCounter = 0; // Used to add new lines for night runs.
    
    
    
    
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        Label lblHeader1 = new Label("GCEMS Employee Time Sheet");
        Label lblEmpName = new Label("Employee Name: ");
        TextField txtFieldEmpName = new TextField();
        Label lblWeekOne = new Label("Week One");
        Label lblWeekTwo = new Label("Week Two");
        
        //create the WorkDay array and initialize it
        WorkDay[][] WorkDay_array = new WorkDay[2][7];
        for(int k = 0; k < 2; k++)
        {
            for(int l = 0; l < 7; l++)
            {
                WorkDay_array[k][l] = new WorkDay();
            }
        }
        
        
        //CustomWorkDay Week1Thursday = new CustomWorkDay();
        
        GridPane baseGrid = new GridPane();
        //baseGrid.setGridLinesVisible(true);
        baseGrid.addRow(0, lblHeader1);
        baseGrid.addRow(1,lblEmpName);
        baseGrid.add(txtFieldEmpName, 1, 1);
        baseGrid.addRow(2);
        baseGrid.add(lblWeekOne, 0, 2);
        baseGrid.add(lblWeekTwo, 2, 2);
        baseGrid.addRow(3); // Thursday
        baseGrid.addRow(4); // Friday
        baseGrid.addRow(5); // Saturday
        baseGrid.addRow(6); // Sunday
        baseGrid.addRow(7); // Monday
        baseGrid.addRow(8); // Tuesday
        baseGrid.addRow(9); // Wednesday
        
        
        for(int j=0, k= 0; j<7; j++)
        {
        baseGrid.add(WorkDay_array[k][j].makeWorkDay(), 0, j+3);//Create a workday and assign it to Week One in the appropriate row
        }
        
        for(int j=0, k=1; j<7; j++)
        {
        baseGrid.add(WorkDay_array[k][j].makeWorkDay(), 2, j+3);//Create a workday and assign it to Week Two in the appropriate row
        }
        
        //Set alignments and Spannings
        GridPane.setHalignment(lblHeader1, HPos.CENTER);
        GridPane.setColumnSpan(lblHeader1, 3);
        GridPane.setColumnSpan(txtFieldEmpName, 2);
        GridPane.setHalignment(lblWeekOne, HPos.CENTER);
        GridPane.setHalignment(lblWeekTwo, HPos.CENTER);
        
        BorderPane root = new BorderPane();
        root.setCenter(baseGrid);
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("GCEMS Time Sheet");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //Going to make some notes so I can refer to them later.
        //I want a grid pane layout for the main area.
        
        //I think I'll lay it out in scene builder so I can get a visual idea.
        
        
        
        //an explaination of some of the variables I think I will need to use
        
        //I need to specify a date which is the Thursday of the first week of our pay period.
        
        //I made (well, still making) a class of a typical work day
        //This will include entries for a 12 1st half, 12 2nd half, and 24 hour shifts
        //as well as other custom hours.
        //This will also include the option of entering night time runs
        
        //I have to devise a way to deal with Holidays and PTO days
    }

    
    public class WorkDay
    {
        /* The folowing is the initial way I was trying to do stuff.   I am going to rewrite
        just about everything, I think.  So, I'll keep re-writing until I get it like I want it.
        */
        
        //Create the Day
        VBox customWorkDay = new VBox();
        HBox cwdLine1 = new HBox();
        HBox cwdLine2 = new HBox();
        HBox cwdLine3 = new HBox();
        DatePicker dp = new DatePicker();
        Label lblFirstHalf = new Label("In: 0800     Out: 2000");
        Label lblSecondHalf = new Label("In:  2000     Out:  0800");
        Label lblWholeShift = new Label("In:  0800     Out:  0800");
        Label lblCustomShift = new Label("Custom Shift Placeholder");
        ComboBox<String> cbShiftDuration = new ComboBox<>();
        TimeDials cboCustomHourIn = new TimeDials();
        TimeDials cboCustomMinuteIn = new TimeDials();
        TimeDials cboCustomHourOut = new TimeDials();
        TimeDials cboCustomMinuteOut = new TimeDials();
        Button btnNewLine = new Button("+");
        Button btnRemoveLine = new Button("-");
        Label lblRegHours = new Label("Regular Hours: ");
        Label lblVarRegHours = new Label("0");
        Label lblOTHours = new Label("Overtime Hours:  ");
        Label lblVarOTHours = new Label("0");
        Label lblCustomIn = new Label("In");
        Label lblCustomOut = new Label("Out");
        ArrayList<NightRun> listNightRuns = new ArrayList<>();
        ArrayList<WorkDayEntryGUI> listWorkDayEntry;
        double hoursSingleShift = 0;
        double hoursNightRuns = 0;
        double hoursOT = 0;

               
        public WorkDay()
        {       
            this.listWorkDayEntry = new ArrayList<>();
            cbShiftDuration.getItems().addAll("First Half", "Second Half", "Whole Shift", "Custom");
            cbShiftDuration.getSelectionModel().select(null);
            cbShiftDuration.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> obs, String oldVal, String newVal) ->
            {
                cwdLine1.getChildren().remove(lblFirstHalf);
                cwdLine1.getChildren().remove(lblSecondHalf);
                cwdLine1.getChildren().remove(lblWholeShift);
                cwdLine1.getChildren().remove(lblCustomShift);
                switch(newVal)
                {
                    case "First Half":
                        cwdLine1.getChildren().add(lblFirstHalf);
                        hoursSingleShift = 12;
                        break;
                    case "Second Half":
                        cwdLine1.getChildren().add(lblSecondHalf);
                        hoursSingleShift = 12;
                        break;    
                    case "Whole Shift":
                        cwdLine1.getChildren().add(lblWholeShift);
                        hoursSingleShift = 16;
                        break;
                    case "Custom":
                        cwdLine1.getChildren().add(lblCustomIn);
                        cwdLine1.getChildren().add(cboCustomHourIn.makeHourDial());
                        cwdLine1.getChildren().add(cboCustomMinuteIn.makeMinuteDial());
                        cwdLine1.getChildren().add(lblCustomOut);
                        cwdLine1.getChildren().add(cboCustomHourOut.makeHourDial());
                        cwdLine1.getChildren().add(cboCustomMinuteOut.makeMinuteDial());
                        break;
                    default:
                        break;
                }
            });
            
            /*
            This is the orignial setup.  After looking at it, I realized it did not
            cover every situation.  I had to rethink my setup.  I will keep this until
            I get everything working like I want it
            
            cwdLine1.getChildren().add(dp);
            cwdLine1.getChildren().add(cbShiftDuration);
        
            cwdLine2.getChildren().add(btnNewLine);
            cwdLine2.getChildren().add(btnRemoveLine);
            cwdLine2.getChildren().add(lblRegHours);
            cwdLine2.getChildren().add(lblVarRegHours);
            cwdLine2.getChildren().add(lblOTHours);
            cwdLine2.getChildren().add(lblVarOTHours);
            */
            
            //This is the second attempt at setting up my day properly
            cwdLine1.getChildren().addAll(dp, lblRegHours, lblOTHours, lblVarOTHours);
            cwdLine2.getChildren().add(btnNewLine);
            
        
            //This button will add a new line for night time runs
            btnNewLine.setOnAction((ActionEvent e)-> 
            {
                /*
                this is the original code I had set up.  It was used to make a new line which was strictly for night runs
                I am going to have to rework it to make a pop-up window.
                The popup window will then get all the needed info for regular day, PTO day, Holiday, Night run, etc
                I will remove these lines once I get everything working.
                
                listNightRuns.add(new NightRun());
                int listIndex = listNightRuns.size();
                NightRun listNode = new NightRun();
                customWorkDay.getChildren().add(listNightRuns.get(listIndex - 1).makeNightRun());
                */
                
                listWorkDayEntry.add(new WorkDayEntryGUI());
                
            });

            //This button will remove selected lines of night time runs
            btnRemoveLine.setOnAction((ActionEvent e)->
            {
                //Right now, there is a lot of nonfunctional code in here.
                //I am attempting to get this button to work but I keep coming up short.
                //Below are some of the things I've tried.  Once I get a method that works
                //I'll delete all of the rest which do not work.
                
                
                /*listNightRuns.forEach((NightRun _item) ->
                {
                //System.out.println(_item);
                if(_item.chkBoxNight.isSelected())
                {
                //listNightRuns.remove();
                }
                });*/
                
                //cwdLine3.getChildren().removeIf(chkBoxNight.isSelected);
                //listNightRuns.removeIf(chkBoxNight.isSelected);
                
                /*for (Iterator<NightRun> iterator = listNightRuns.iterator(); iterator.hasNext(); )
                {
                iterator.next();
                if (listNightRuns(iterator).isSelected)
                {
                iterator.remove();
                }
                }*/
                
                //listNightRuns.removeIf(_item ->  NightRun.chkBoxNight.getChildren(_item).isSelected());
                
                //final List<NightRun> filtered = original.stream().filter(item -> item.check.isSelected()).collect(Collectors.toList());

                //listNightRuns.clear(); listNightRuns.addAll(filtered);
                
            });

            customWorkDay.getChildren().add(cwdLine1);
            customWorkDay.getChildren().add(cwdLine2);
        }
        
        /**
         *
         * @param <T>
         * @return
         */
        public <T extends Node> T makeWorkDay()
        {
            return (T) customWorkDay;
        }
        //End of Day creation
    }
    
    
    public class TimeDials
    {
        ComboBox cboMinuteDial = new ComboBox();
        ComboBox cboHourDial = new ComboBox();
        
        public TimeDials()
        {
            
        }
        
        public <minuteDial> minuteDial makeMinuteDial()
        {
            cboMinuteDial.getItems().addAll(00, 15, 30, 45);
            return (minuteDial) cboMinuteDial;
        }
        
        public <hourDial> hourDial makeHourDial()
        {
            for (int j = 0; j < 24; j++)
            {
                cboHourDial.getItems().add(j);
            }
            return (hourDial) cboHourDial;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
