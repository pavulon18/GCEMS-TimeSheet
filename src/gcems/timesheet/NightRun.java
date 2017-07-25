/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcems.timesheet;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author Jim Baize
 * Copyright:  2017
 */
public class NightRun
{
    
    HBox hboxNightRun = new HBox();
    Label lblRunNumber = new Label("Run Number");
    TextField tfRunNumber = new TextField();
    Label lblNightOut = new Label("Out: ");
    GCEMSTimeSheet.TimeDials cboNightHourOut;
    GCEMSTimeSheet.TimeDials cboNightMinuteOut;
    Label lblNightIn = new Label("In: ");
    GCEMSTimeSheet.TimeDials cboNightHourIn;
    GCEMSTimeSheet.TimeDials cboNightMinuteIn;
    Label lblNightElapsedTime = new Label("Elapsed Time: ");
    Integer intElapsedHour = 0;
    Integer intElapsedMinute = 0;
    CheckBox chkBoxNight = new CheckBox();
    Label lblDisplayElapsedTime = new Label();
    private final GCEMSTimeSheet outer;

    public NightRun(final GCEMSTimeSheet outer)
    {
        this.cboNightMinuteIn = outer.new TimeDials();
        this.cboNightHourIn = outer.new TimeDials();
        this.cboNightMinuteOut = outer.new TimeDials();
        this.cboNightHourOut = outer.new TimeDials();
        this.outer = outer;
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
        //chkBoxNight.isSelected();
    }

    public <nightRun> nightRun makeNightRun()
    {
        return (nightRun) hboxNightRun;
    }
    
}
