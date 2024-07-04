package lk.ijse.oxford.contoller.employee_form_controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.oxford.BO.Custom.EmployeeBO;
import lk.ijse.oxford.BO.Custom.Impl.EmployeeBOImpl;
import lk.ijse.oxford.DTO.EmployeeDTO;
import lk.ijse.oxford.DTO.User;
import lk.ijse.oxford.DTO.tm.EmployeeTm;
import lk.ijse.oxford.DAO.Custom.Impl.EmployeDAOImpl;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddEmployeeFormContoller {
    @FXML
    private JFXTextField txtEmployeeName;
    @FXML
    private JFXTextField txtContactNumber;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtEmpType;
    @FXML
    private Label lblEmpId;
    @FXML
    private Label lblUserId;
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
    private User user;
    public void setUser(User user) {
        this.user=user;
        setUserId(user);
    }

    EmployeeBO employeeBO = new EmployeeBOImpl();

    private void setUserId(User user) {
        lblUserId.setText(this.user.getUId());
    }

    public void initialize(){
        this.employeeList = getAllEmployees();
        setCellValueFactory();
        loadEmployeeTable();
        loadNextEmpId();
    }
    private void loadNextEmpId() {
        try {
            String currentId = employeeBO.currentId();
            String nextId = nextId(currentId);

            lblEmpId.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("E");
            int id = Integer.parseInt(split[1]);
            id++;

            // Format the ID with leading zeros using String.format
            return "E" + String.format("%03d", id);
        } else {
            return "E001";
        }
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
    public void btnEmpAddOnAction(ActionEvent actionEvent) {
       if (isValidate()){
           String id = lblEmpId.getText();
           String name = txtEmployeeName.getText();
           String address = txtAddress.getText();
           String tel = txtContactNumber.getText();
           String type = txtEmpType.getText();
           String userId = lblUserId.getText();

           EmployeeDTO employee = new EmployeeDTO(id, name, tel,address,type,userId);

           try {
               boolean isSaved = employeeBO.save(employee);
               if (isSaved) {
                   new Alert(Alert.AlertType.CONFIRMATION, "Employee Data Saved!").show();
                   initialize();
                   txtEmployeeName.setText("");
                   txtContactNumber.setText("");
                   txtAddress.setText("");
                   lblEmpId.setText("");
                   txtEmpType.setText("");
                   lblUserId.setText("");
               }
           } catch (SQLException e) {
               new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
           } catch (ClassNotFoundException e) {
               throw new RuntimeException(e);
           }
       }
    }

    public boolean isValidate(){
        if(!Regex.setTextColor(TextFields.NAME,txtEmployeeName))return false;
        if(!Regex.setTextColor(TextFields.ADDRESS,txtAddress))return false;
        if(!Regex.setTextColor(TextFields.CONTACT,txtContactNumber))return false;
        if(!Regex.setTextColor(TextFields.TYPE,txtEmpType))return false;
        return true;
    }

    public void txtEmpNameCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtEmployeeName);
    }

    public void txtContactCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.CONTACT,txtContactNumber);
    }

    public void txtAddressCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS,txtAddress);
    }

    public void txtEmpTypeCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.TYPE,txtEmpType);
    }
}
