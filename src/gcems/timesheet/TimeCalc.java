/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcems.timesheet;

/**
 *
 * @author pavul
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
            System.out.println("This is a placeholder error message until I can figure out how to ");
            System.out.println("write a proper pop-up error message window");
        }

        timeFinal = hourElapsed + ((double) minuteElapsed / (double) 60);

        return timeFinal;
    }
}
