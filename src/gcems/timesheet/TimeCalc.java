/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcems.timesheet;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Jim Baize
 */
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
            //System.out.println("This is a placeholder error message until I can figure out how to ");
            //System.out.println("write a proper pop-up error message window");
            
            Alert alert = new Alert(AlertType.CONFIRMATION, "Time cannot be negative.  Please go back and correct your time.", ButtonType.CANCEL);
            alert.showAndWait();

            /*
            if (alert.getResult() == ButtonType.YES)
            {
            //do stuff
            }
            */
        }

        timeFinal = hourElapsed + ((double) minuteElapsed / (double) 60);

        return timeFinal;
    }
}
