package gcems.timesheet;


import javafx.scene.control.ComboBox;

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
