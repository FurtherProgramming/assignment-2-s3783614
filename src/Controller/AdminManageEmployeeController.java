package Controller;

import Model.ManageEmpsModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.User;
import main.UserHolder;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminManageEmployeeController implements Initializable {
    ManageEmpsModel manageEmpsModel = new ManageEmpsModel();
    User user = new User();
    @FXML
    private  TableView<User> tblEmployees;
    @FXML
    private TableColumn<User, Integer> tblEmpId;
    @FXML
    private TableColumn<User, String> tblFirstName;
    @FXML
    private TableColumn<User, String> tblLastName;
    @FXML
    private TableColumn<User, String> tblUsername;
    @FXML
    private TableColumn<User, String> tblStatus;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnAdd;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try
        {
            fillTable();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void fillTable() throws SQLException
    {
        ObservableList<User> employees;
            employees = manageEmpsModel.observableEmployeeList();
            tblEmployees.setItems(employees);
            tblEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
            tblFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            tblLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            tblUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
            tblStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void enableEmployee()
    {
        if(tblEmployees.getSelectionModel().getSelectedItem() == null)
        {
            String errorMessage = "Please select a field to proceed!";
            Util.alertError(errorMessage);
        }
        else
        {
            //gets empID from table view
            int empId = tblEmployees.getSelectionModel().getSelectedItem().getEmpId();
            String status = "Active";
            //checks if the status is equal
            if(tblEmployees.getSelectionModel().getSelectedItem().getStatus().equals(status))
            {
                Util.alertError("Employee is already Active!");
            }
            else if(Util.alertConfirmation("Are you sure?"))
            {
                try
                {
                    //Updates status of emp
                    manageEmpsModel.updateStatus(status,empId);
                    fillTable();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void disableEmployee()
    {
        if(tblEmployees.getSelectionModel().getSelectedItem() == null)
        {
            String errorMessage = "Please select a field to proceed!";
            Util.alertError(errorMessage);
        }
        else
        {
            int empId = tblEmployees.getSelectionModel().getSelectedItem().getEmpId();
            String status = "Inactive";

            if(tblEmployees.getSelectionModel().getSelectedItem().getStatus().equals(status))
            {
                Util.alertError("Employee is already inactive!");
            }
            else if(Util.alertConfirmation("Disabling the employee will deny them access to their account. Are you sure?"))
            {
                try {
                    manageEmpsModel.updateStatus(status,empId);
                    fillTable();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteEmployee()
    {
        if(tblEmployees.getSelectionModel().getSelectedItem() == null)
        {
            String errorMessage = "Please select a field to proceed!";
            Util.alertError(errorMessage);
        }
        else if(Util.alertConfirmation("Are you sure you want to delete employee?"))
        {
            int empId = tblEmployees.getSelectionModel().getSelectedItem().getEmpId();

            try
            {
                manageEmpsModel.deleteEmployee(empId);
                fillTable();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void editEmployee(ActionEvent event) throws IOException {

        if(tblEmployees.getSelectionModel().getSelectedItem() == null)
        {
            String errorMessage = "Please select a field to proceed!";
            Util.alertError(errorMessage);
        }
        else
        {
            int empId = tblEmployees.getSelectionModel().getSelectedItem().getEmpId();
            UserHolder.getInstance().setEmpId(empId);

            Util.popButtonUpWindow("../View/adminUpdateEmployeeDetails.fxml",btnEdit);

            try
            {
                fillTable();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }

    public void addEmployee() throws IOException
    {

        Util.popButtonUpWindow("../View/adminCreateNewEmployee.fxml",btnAdd);
        // Util.getStage(event).close();
        try
        {
            fillTable();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }


    public void previousPage(ActionEvent event) throws IOException
    {
        Util.sceneSwitcher("../View/adminDashboard.fxml", Util.getStage(event));
    }


}
