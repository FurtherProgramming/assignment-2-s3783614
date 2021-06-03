package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeMakeBookingController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
            
    }


    public void previousPage(ActionEvent event) throws IOException
    {
        Util.sceneSwitcher("../View/employeeDashboard.fxml", Util.getStage(event));
    }

}
