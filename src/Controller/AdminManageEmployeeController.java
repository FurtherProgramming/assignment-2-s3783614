package Controller;

import Model.ManageEmpsModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.User;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    @FXML
    private Button btnEdit;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try
        {
            fillTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void fillTable() throws SQLException {
        ObservableList<User> employees;
            employees = manageEmpsModel.observableEmployeeList();
            tblEmployees.setItems(employees);
            tblEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
            tblFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            tblLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            tblUsername.setCellValueFactory(new PropertyValueFactory<>("username"));

    }

    public void deleteEmployee(ActionEvent event)
    {
        if(tblEmployees.getSelectionModel().getSelectedItem() == null)
        {
            System.out.println("do something!");
        }
        else
        {
            int empId = tblEmployees.getSelectionModel().getSelectedItem().getEmpId();

            try
            {
                manageEmpsModel.deleteEmployee(empId);
                System.out.println("EmpId : " + empId);
                fillTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void editEmployee(ActionEvent event)
    {
        if(tblEmployees.getSelectionModel().getSelectedItem() == null)
        {
            //TODO: ALERT BOX
            System.out.println("do something!");
        }
        // else
        // {
        //
        // }
        // if(tblEmployees.getSelectionModel().selectedItemProperty())
        // {
        //     System.out.println("Yes!!");
        // }
    }

    public void addEmployee(ActionEvent event) throws IOException {
        // Stage stage = new Stage();
        // FXMLLoader fXMLLoader;
        // Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("../View/adminCreateNewEmployee.fxml")));
        // stage.setScene(new Scene(root));
        // stage.initModality(Modality.APPLICATION_MODAL);
        // stage.initOwner(btnEdit.getScene().getWindow());
        // stage.showAndWait();


        Util.popUpWindow("../View/adminCreateNewEmployee.fxml",btnEdit,Util.getStage(event));

        // Stage stage;
        // Parent root;
        // stage = new Stage();
        // root = FXMLLoader.load(getClass().getResource("../View/adminCreateNewEmployee.fxml"));
        // stage.setScene(new Scene(root));
        // stage.initModality(Modality.APPLICATION_MODAL);
        // stage.initOwner(btnEdit.getScene().getWindow());
        // stage.showAndWait();
        // initialize();
        try
        {
            fillTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Util.sceneSwitcher("../View/adminCreateNewEmployee.fxml",Util.getStage(event));
    }



    public void previousPage(ActionEvent event) throws IOException {
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Parent root = FXMLLoader.load(getClass().getResource("../View/adminDashboard.fxml"));
        // Scene scene = new Scene(root);
        // stage.setScene(scene);
        // stage.show();
        Util.sceneSwitcher("../View/adminDashboard.fxml", Util.getStage(event));
    }


}
