/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcems.timesheet;

import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Jim Baize
 */
class DetailedDataEntry
{
    Stage dataEntryStage = new Stage();
    CheckBox chkBoxHoliday = new CheckBox();
    ComboBox<String> cboPTO = new ComboBox<>();
    ComboBox cboShiftDuration = new ComboBox();
    VBox vBoxHolidayPTO = new VBox();
    HBox hBoxDataEntry = new HBox();
    
    /*DetailedDataEntry()
    {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    chkBoxHoliday.setText("Holiday");
    cboPTO.getItems().addAll("Sick Day", "Vacation Day", "Personal Day");
    cboShiftDuration.getItems().addAll("First Half", "Second Half", "24 Hour Shift");
    cboShiftDuration.getSelectionModel().select("24 Hour Shift");
    
    hBoxDataEntry.getChildren().addAll(chkBoxHoliday, cboShiftDuration, cboPTO);
    
    Scene DetailedDataEntryScene = new Scene(hBoxDataEntry);
    dataEntryStage.setScene(DetailedDataEntryScene);
    dataEntryStage.initModality(Modality.APPLICATION_MODAL);
    dataEntryStage.show();
    }*/

    DetailedDataEntry()
    {
        chkBoxHoliday.setText("Holiday");
        cboPTO.getItems().addAll("Sick Day", "Vacation Day", "Personal Day");
        cboShiftDuration.getItems().addAll("First Half", "Second Half", "24 Hour Shift");
        cboShiftDuration.getSelectionModel().select("24 Hour Shift");
        
        hBoxDataEntry.getChildren().addAll(chkBoxHoliday, cboShiftDuration, cboPTO);
        
        Scene DetailedDataEntryScene = new Scene(hBoxDataEntry);
        dataEntryStage.setScene(DetailedDataEntryScene);
        dataEntryStage.initModality(Modality.APPLICATION_MODAL);
        dataEntryStage.show();
    }
    
    public <detailedDataEntry> void makeDetailedDataEntry()
    {
        //dataEntryStage.setScene(DetailedDataEntryScene);
        dataEntryStage.initModality(Modality.APPLICATION_MODAL);
        dataEntryStage.show();
    }
}
