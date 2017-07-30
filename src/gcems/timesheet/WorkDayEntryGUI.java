package gcems.timesheet;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    //DatePicker dpDate = new DatePicker();
    DatePicker dpDate = new DatePicker(LocalDate.of(2017,6,29));
    LocalDate dateOfWorkDay;
    Label lblDatePlaceHolder = new Label("Date Placeholder");
    TextField txtFieldDate = new TextField();
    Button btnNewLine = new Button("+");
    Button btnRemoveLine = new Button("-");
    Label lblRegHours = new Label("Regular Hours: ");
    Label lblVarRegHours = new Label("0");
    Label lblOTHours = new Label("Overtime Hours:  ");
    Label lblVarOTHours = new Label("0");
    Label lblCustomIn = new Label("In");
    Label lblCustomOut = new Label("Out");
    ArrayList<WorkDayEntryGUI> listWorkDayEntry;
    //boolean boolIsFirstThurs = false;
    FirstThursday isFirstThurs;
    boolean isFirstThursNew = false;
    LocalDate referenceDate;
    
    
    //Set the DatePicker to only allow the First Thursdays as determined by 
    //the class FirstThursday
        
    WorkDayEntryGUI()
    {
        this.referenceDate = LocalDate.of(2017,6,29);
        dpDate.setOnAction(event -> 
        {
            LocalDate selectedDate = dpDate.getValue();
            
        });
        this.isFirstThurs = new FirstThursday(dpDate.getValue());
        //this.isFirstThurs = new FirstThursday();
        final Callback<DatePicker, DateCell> dayCellFactory = (final DatePicker dpDate1) -> new DateCell()
        {
            @Override
            public void updateItem(LocalDate item, boolean empty)
            {
                super.updateItem(item, empty);
                
                //if (empty || item.getDayOfWeek() != DayOfWeek.THURSDAY);
                if(empty || ((item.isAfter(referenceDate)) &&
                ((referenceDate.until(item, ChronoUnit.DAYS)) % 14) == 0))
                {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };
        

        dpDate.setDayCellFactory(dayCellFactory);
        
        txtFieldDate.setText(dateOfWorkDay.toString());

        listWorkDayEntry = new ArrayList<>();
        cwdLine1.getChildren().addAll(txtFieldDate, lblRegHours, lblOTHours, lblVarOTHours);
        cwdLine2.getChildren().addAll(lblRegHours, lblVarRegHours, lblOTHours, lblVarOTHours);
        cwdLine3.getChildren().add(btnNewLine);

        btnNewLine.setOnAction((ActionEvent event) ->
        {
            makeDetailedDataEntry();
        });

        btnRemoveLine.setOnAction((ActionEvent e) ->
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
        CheckBox chkBoxNightRun = new CheckBox("Night Run");
        
        ComboBox<String> cboShiftDuration = new ComboBox<>();
        ComboBox<String> cboPTO = new ComboBox<>();
        
        Button btnSaveData = new Button("Save");
        HBox hBoxDataEntry = new HBox();
        HBox hBoxLineTwo = new HBox();
        HBox hBoxDataEntryThree = new HBox();
        HBox hBoxDataEntryFour = new HBox();
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
                hBoxDataEntryThree.getChildren().clear();
                
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
                        hBoxDataEntryThree.getChildren().add(chkBoxNightRun);
                        hBoxDataEntryThree.getChildren().add(new Text("Is this a night run or an extention to your shift?"));
                        hBoxDataEntryFour.getChildren().add(lblCustomIn);
                        hBoxDataEntryFour.getChildren().add(cboCustomHourIn.makeHourDial());
                        hBoxDataEntryFour.getChildren().add(cboCustomMinuteIn.makeMinuteDial());
                        hBoxDataEntryFour.getChildren().add(lblCustomOut);
                        hBoxDataEntryFour.getChildren().add(cboCustomHourOut.makeHourDial());
                        hBoxDataEntryFour.getChildren().add(cboCustomMinuteOut.makeMinuteDial());
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
        root.getChildren().addAll(hBoxDataEntry, hBoxLineTwo, hBoxDataEntryThree, hBoxDataEntryFour);
        
        Scene DetailedDataEntryScene = new Scene(root);
        dataEntryStage.setScene(DetailedDataEntryScene);
        dataEntryStage.initModality(Modality.APPLICATION_MODAL);
        dataEntryStage.show();
    }

    public <T extends Node> T makeWorkDay()
        {
            return (T) customWorkDay;
        }
    
    public <T extends Node> T makeFirstThursday()
    {
        cwdLine1.getChildren().remove(lblDatePlaceHolder);
        cwdLine1.getChildren().remove(txtFieldDate);
        cwdLine1.getChildren().add(0,dpDate);
        
        return (T) customWorkDay;
    }
    
    public void setDate(LocalDate incomingDate)
    {
        dateOfWorkDay = incomingDate;
    }
    
    public LocalDate getDate()
    {
        return dateOfWorkDay;
    }
    
    public LocalDate getFirstDate()
    {
        return dpDate.getValue();
    }
}