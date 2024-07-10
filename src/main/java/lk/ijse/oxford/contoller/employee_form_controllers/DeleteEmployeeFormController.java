package lk.ijse.oxford.contoller.employee_form_controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.oxford.BO.BOFactory;
import lk.ijse.oxford.BO.Custom.EmployeeBO;
import lk.ijse.oxford.BO.Custom.Impl.EmployeeBOImpl;
import lk.ijse.oxford.DTO.EmployeeDTO;
import lk.ijse.oxford.DTO.tm.EmployeeTm;
import lk.ijse.oxford.DAO.Custom.Impl.EmployeDAOImpl;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteEmployeeFormController {
    @FXML
    private Label lblEmployeeCount;
    private int employeeCount;
    @FXML
    private JFXTextField txtEmpId;
    @FXML
    private JFXTextField txtEmployeeName;
    @FXML
    private TableColumn<?,?> colEmpId;
    @FXML
    private TableColumn<?,?>colUserId;
    @FXML
    private TableColumn<?,?>colEmpName;
    @FXML
    private TableColumn<?,?>colContact;
    @FXML
    private TableColumn<?,?>colEmpAddress;
    @FXML
    private TableColumn<?,?>colEmpType;
    @FXML
    private TableView<EmployeeTm> tblEmployee;
    private List<EmployeeDTO> employeeList = new ArrayList<>();

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    public void initialize(){
        this.employeeList = getAllEmployees();
        setCellValueFactory();
        loadEmployeeTable();
        try {
            employeeCount= employeeBO.getEmployeeCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setEmployeeCount(employeeCount);
    }

    private void setEmployeeCount(int employeeCount) {
        lblEmployeeCount.setText(String.valueOf(employeeCount));
    }


    private List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employeeList = null;
        try {
            employeeList = employeeBO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    private void loadEmployeeTable() {
        ObservableList<EmployeeTm> tmList = FXCollections.observableArrayList();

        for (EmployeeDTO employee : employeeList) {
            EmployeeTm employeeTm = new EmployeeTm(
                    employee.getEmpId(),
                    employee.getUserId(),
                    employee.getName(),
                    employee.getContact(),
                    employee.getAddress(),
                    employee.getType()
            );

            tmList.add(employeeTm);
        }
        tblEmployee.setItems(tmList);
    }

    private void setCellValueFactory() {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmpType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmpAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }
    public void btnEmpDeleteOnAction(ActionEvent actionEvent) {
       if (isValidate()){
           String id = txtEmpId.getText();

           try {
               boolean isDeleted = employeeBO.delete(id);
               if (isDeleted) {
                   new Alert(Alert.AlertType.CONFIRMATION, "Employee Data Deleted!").show();
               }
           } catch (SQLException e) {
               new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
           } catch (ClassNotFoundException e) {
               throw new RuntimeException(e);
           }
       }
    }

    public void empDeleteOnAction(MouseEvent mouseEvent) {
        EmployeeTm selectedItem = tblEmployee.getSelectionModel().getSelectedItem();
        txtEmpId.setText(selectedItem.getEmpId());
        txtEmployeeName.setText(selectedItem.getName());
    }

    public void btnEmpRefreshOnAction(ActionEvent actionEvent) {
        initialize();
        txtEmployeeName.setText("");
        txtEmpId.setText("");
    }

    public boolean isValidate(){
        if(!Regex.setTextColor(TextFields.NAME,txtEmployeeName))return false;
        if(!Regex.setTextColor(TextFields.EID,txtEmpId))return false;
        return true;
    }
    public void txtEmpNameCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtEmployeeName);
    }
    public void txtEmpIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.EID,txtEmpId);
    }
}
