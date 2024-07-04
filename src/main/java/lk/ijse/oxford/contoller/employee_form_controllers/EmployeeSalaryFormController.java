package lk.ijse.oxford.contoller.employee_form_controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.oxford.BO.Custom.Impl.SalaryBOImpl;
import lk.ijse.oxford.BO.Custom.SalaryBO;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.DTO.SalaryDTO;
import lk.ijse.oxford.DAO.Custom.Impl.SalaryDAOImpl;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;

import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeSalaryFormController implements Initializable {
    @FXML
    private JFXTextField txtEmpSearch;
    @FXML
    private Label lblEmpName;
    @FXML
    private Label lblEmpId;
    @FXML
    private Label lblSalaryAmount;
    @FXML
    private Label lblTotalSalary;
    private int totalSalary;
    @FXML
    private Label lblSalaryDate;
    @FXML
    private TableColumn<SalaryDTO,String> colEmpId;
    @FXML
    private TableColumn<SalaryDTO,String> colName;
    @FXML
    private TableColumn<SalaryDTO,String>colSalaryId;
    @FXML
    private TableColumn<SalaryDTO,Double>colAmount;
    @FXML
    private TableColumn<SalaryDTO, Date>colDate;
    @FXML
    private TableView<SalaryDTO> tblSalary;

    SalaryBO salaryBO = new SalaryBOImpl();
    private ObservableList<SalaryDTO> salaryObservableList =FXCollections.observableArrayList();
    /*public void initialize(){
        try {
            totalSalary = SalaryRepo.getTotalSalary();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(totalSalary);
        setTotalSalary(totalSalary);
    }*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            totalSalary = salaryBO.getTotalSalary();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setTotalSalary(totalSalary);

        try {
            String sql = "SELECT s.SalaryId,s.Amount,s.Date,s.EmpId,e.Name FROM Salary s JOIN Employee e ON s.EmpId=e.EmpId";
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

            ResultSet resultSet = pstm.executeQuery(sql);

            while (resultSet.next()){

                String salaryId = resultSet.getString(1);
                String  amount = String.valueOf(resultSet.getDouble(2));
                Date date = resultSet.getDate(3);
                String empId = resultSet.getString(4);
                String name = resultSet.getString(5);

//                populate the observale list
                salaryObservableList.add(new SalaryDTO(salaryId,amount,date,empId,name));
            }
            colSalaryId.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
            colEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

            tblSalary.setItems(salaryObservableList);

                FilteredList<SalaryDTO> filteredList = new FilteredList<>(salaryObservableList, b ->true);
                txtEmpSearch.textProperty().addListener((observable ,oldValue,newValue) ->{
                    filteredList.setPredicate(salary -> {

                        if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                            return true;
                        }

                        String keyword = newValue.toLowerCase();
                        if (salary.getSalaryId().toLowerCase().indexOf(keyword) > -1){
                            return true;
                        } else if (salary.getEmpId().toLowerCase().indexOf(keyword) >-1) {
                            return true;
                        } else if (salary.getName().toLowerCase().indexOf(keyword) >-1) {
                            return true;
                        } else if (salary.getDate().toString().indexOf(keyword) >-1) {
                            return true;
                        } else if (salary.getAmount().toLowerCase().indexOf(keyword) >-1) {
                            return true;
                        }else {
                            return false;
                        }
                    });
                });

                SortedList<SalaryDTO> sortedList = new SortedList<>(filteredList);
                sortedList.comparatorProperty().bind(tblSalary.comparatorProperty());

                tblSalary.setItems(sortedList);
        } catch (SQLException e) {
            Logger.getLogger(EmployeeSalaryFormController.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }
    }
    private void setTotalSalary(int totalSalary) {
        lblTotalSalary.setText(String.valueOf(totalSalary));
    }


    public void empSalarySearchOnAction(MouseEvent mouseEvent) {
        SalaryDTO selectedItem = tblSalary.getSelectionModel().getSelectedItem();
        lblEmpId.setText(selectedItem.getEmpId());
        lblEmpName.setText(selectedItem.getName());
        lblSalaryDate.setText(String.valueOf(selectedItem.getDate()));
        lblSalaryAmount.setText(String.valueOf(selectedItem.getAmount()));
    }

    public boolean isValidate(){
        if(!Regex.setTextColor(TextFields.NAME,txtEmpSearch))return false;
        return true;
    }
    public void txtEmpIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtEmpSearch);
    }
}
