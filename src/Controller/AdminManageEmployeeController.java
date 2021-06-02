package Controller;

import Model.ManageEmpsModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.User;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminManageEmployeeController implements Initializable {
    ManageEmpsModel manageEmpsModel = new ManageEmpsModel();

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


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<User> employees;
        try
        {
            employees = manageEmpsModel.observableEmployeeList();
            tblEmployees.setItems(employees);
            // List<User> showing = employees.get;


            // System.out.println(employees.toString());
            tblEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));

            // System.out.println("Hello reee");
            tblFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));

            // System.out.println("Hello second time");
            tblLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            tblUsername.setCellValueFactory(new PropertyValueFactory<>("username"));


        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    // public void fillTable()


    public void previousPage(ActionEvent event) throws IOException {
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Parent root = FXMLLoader.load(getClass().getResource("../View/adminDashboard.fxml"));
        // Scene scene = new Scene(root);
        // stage.setScene(scene);
        // stage.show();
        Util.sceneSwitcher("../View/adminDashboard.fxml", Util.getStage(event));
    }


}
