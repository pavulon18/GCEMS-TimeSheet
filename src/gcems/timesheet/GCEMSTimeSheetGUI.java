/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcems.timesheet;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Jim Baize
 */
public class GCEMSTimeSheetGUI extends Application
{
    
    @Override
    public void start(Stage primaryStage)
    {
        Label lblHeader1 = new Label("GCEMS Employee Time Sheet");
        Label lblEmpName = new Label("Employee Name: ");
        TextField txtFieldEmpName = new TextField();
        Label lblWeekOne = new Label("Week One");
        Label lblWeekTwo = new Label("Week Two");
        LocalDate firstDate;
        PayPeriodGUI payPeriod = new PayPeriodGUI();
        
        /*
        //create the WorkDay array and initialize it
        WorkDayEntryGUI[][] WorkDay_array = new WorkDayEntryGUI[2][7];
        for(int k = 0; k < 2; k++)
        {
        for(int l = 0; l < 7; l++)
        {
        WorkDay_array[k][l] = new WorkDayEntryGUI();
        }
        }
        */
        
        GridPane baseGrid = new GridPane();
        //baseGrid.setGridLinesVisible(true);
        baseGrid.addRow(0, lblHeader1);
        baseGrid.addRow(1, lblEmpName);
        baseGrid.add(txtFieldEmpName, 1, 1);
        baseGrid.addRow(2);
        baseGrid.add(lblWeekOne, 0, 2);
        baseGrid.add(lblWeekTwo, 2, 2);
        baseGrid.addRow(3, payPeriod.makePayPeriod());
        //baseGrid.addRow(4); // Friday
        //baseGrid.addRow(5); // Saturday
        //baseGrid.addRow(6); // Sunday
        //baseGrid.addRow(7); // Monday
        //baseGrid.addRow(8); // Tuesday
        //baseGrid.addRow(9); // Wednesday
        
        //baseGrid.add(payPeriod.makePayPeriod(), 0, 3);
        
        /*
        baseGrid.add(WorkDay_array[0][0].makeFirstThursday(), 0, 3);
        for(int j=1, k= 0; j<7; j++)
        {
        baseGrid.add(WorkDay_array[k][j].makeWorkDay(), 0, j+3);//Create a workday and assign it to Week One in the appropriate row
        //baseGrid.getChildren().locate(txtFieldDate).setText(WorkDay_array[k][j].getFirstDate());
        }
        
        for(int j=0, k=1; j<7; j++)
        {
        baseGrid.add(WorkDay_array[k][j].makeWorkDay(), 2, j+3);//Create a workday and assign it to Week Two in the appropriate row
        }
        */
        
        
        
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
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
