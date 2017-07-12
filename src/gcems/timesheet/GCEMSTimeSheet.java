/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcems.timesheet;

import java.io.IOException;
import java.util.ArrayList;
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
public class GCEMSTimeSheet extends Application
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
        baseGrid.setGridLinesVisible(true);
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
    }

    
    public class WorkDay extends Parent
    {
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
        ComboBox<Integer> cboCustomHourIn = new ComboBox<>();
        ComboBox<Integer> cboCustomMinuteIn = new ComboBox<>();
        ComboBox<Integer> cboCustomHourOut = new ComboBox<>();
        ComboBox<Integer> cboCustomMinuteOut = new ComboBox<>();
        Button btnNewLine = new Button("+");
        Button btnRemoveLine = new Button("-");
        Label lblRegHours = new Label("Regular Hours: ");
        Label lblVarRegHours = new Label("0");
        Label lblOTHours = new Label("Overtime Hours:  ");
        Label lblVarOTHours = new Label("0");
        Label lblCustomIn = new Label("In");
        Label lblCustomOut = new Label("Out");
        ArrayList<NightRun> listNightRuns = new ArrayList<>();
        double hoursSingleShift = 0;
        double hoursNightRuns = 0;
        double hoursOT = 0;

               
        public WorkDay()
        {
            for (int j = 0; j < 24; j++)
            {
                cboCustomHourIn.getItems().add(j);
            }
            
            for (int j = 0; j < 24; j++)
            {
                cboCustomHourOut.getItems().add(j);
            }
            
            cboCustomMinuteIn.getItems().addAll(00, 15, 30, 45);
            cboCustomMinuteOut.getItems().addAll(00, 15, 30, 45);
            
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
                        cwdLine1.getChildren().add(cboCustomHourIn);
                        cwdLine1.getChildren().add(cboCustomMinuteIn);
                        cwdLine1.getChildren().add(lblCustomOut);
                        cwdLine1.getChildren().add(cboCustomHourOut);
                        cwdLine1.getChildren().add(cboCustomMinuteOut);
                        break;
                    default:
                        break;
                }
            });
                
        
            cwdLine1.getChildren().add(dp);
            cwdLine1.getChildren().add(cbShiftDuration);
        
            cwdLine2.getChildren().add(btnNewLine);
            cwdLine2.getChildren().add(btnRemoveLine);
            cwdLine2.getChildren().add(lblRegHours);
            cwdLine2.getChildren().add(lblVarRegHours);
            cwdLine2.getChildren().add(lblOTHours);
            cwdLine2.getChildren().add(lblVarOTHours);
        
            //This button will add a new line for night time runs
            btnNewLine.setOnAction((ActionEvent e)-> 
            {
                //nightCounter++;
                listNightRuns.add(new NightRun());
                int listIndex = listNightRuns.size();
                NightRun listNode = new NightRun();
                customWorkDay.getChildren().add(listNightRuns.get(listIndex - 1).makeNightRun());
            });

            //This button will remove selected lines of night time runs
            btnRemoveLine.setOnAction((ActionEvent e)->
            {

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
            return (T) cwdLine1.getParent();
        }
        //End of Day creation
    }
    
    public class NightRun extends Parent
    {
        HBox hboxNightRun = new HBox();
        Label lblRunNumber = new Label("Run Number");
        TextField tfRunNumber = new TextField();
        Label lblNightOut = new Label("Out: ");
        TimeDials cboNightHourOut = new TimeDials();
        TimeDials cboNightMinuteOut = new TimeDials();
        Label lblNightIn = new Label("In: ");
        TimeDials cboNightHourIn = new TimeDials();
        TimeDials cboNightMinuteIn = new TimeDials();
        Label lblNightElapsedTime = new Label("Elapsed Time: ");
        Integer intElapsedHour = 0;
        Integer intElapsedMinute = 0;
        CheckBox chkBoxNight = new CheckBox();
        Label lblDisplayElapsedTime = new Label();
        
        public NightRun()
        {
                               
            hboxNightRun.getChildren().add(chkBoxNight);
            hboxNightRun.getChildren().add(lblRunNumber);
            hboxNightRun.getChildren().add(tfRunNumber);
            hboxNightRun.getChildren().add(lblNightOut);
            hboxNightRun.getChildren().add(cboNightHourOut.makeHourDial());
            hboxNightRun.getChildren().add(cboNightMinuteOut.makeMinuteDial());
            hboxNightRun.getChildren().add(lblNightIn);
            hboxNightRun.getChildren().add(cboNightHourIn.makeHourDial());
            hboxNightRun.getChildren().add(cboNightMinuteIn.makeMinuteDial());
            hboxNightRun.getChildren().add(lblNightElapsedTime);
            
            //lblDisplayElapsedTime.setText(Integer.toString())
            //I need to make a action event on the time dials.
            //I need to pull the selected value from the dials and then send those
            //values to the TimeCalc class
            
        }
        
        public <nightRun extends Node> nightRun makeNightRun()
        {
            return (nightRun) hboxNightRun;
        }
    }
    
    public class TimeCalc
    {
        int hourOut;
        int hourIn;
        int hourElapsed;
        int minuteOut;
        int minuteIn;
        int minuteElapsed;
        double timeFinal;
        
        public double doTimeCalc(int hourOut, int hourIn, int minuteOut, int minuteIn)
        {
            minuteElapsed = minuteIn - minuteOut;
            if (minuteElapsed < 0)
            {
                minuteElapsed = minuteElapsed + 60;
                hourIn = hourIn - 1;
            }
            
            hourElapsed = hourIn - hourOut;
            if (hourElapsed < 0)
            {
                System.out.println("This is a placeholder error message until I can figure out how to ");
                System.out.println("write a proper pop-up error message window");
            }
            
            timeFinal = hourElapsed + ((double) minuteElapsed / (double) 60);
            
            return timeFinal;
        }
        
    }
    
    public class TimeDials extends ComboBox
    {
        ComboBox cboMinuteDial = new ComboBox();
        ComboBox cboHourDial = new ComboBox();
        
        public TimeDials()
        {
            
        }
        
        public <minuteDial extends ComboBox> minuteDial makeMinuteDial()
        {
            
            cboMinuteDial.getItems().addAll(00, 15, 30, 45);
            return (minuteDial) cboMinuteDial;
        }
        
        public <hourDial extends ComboBox> hourDial makeHourDial()
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
