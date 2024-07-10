package lk.ijse.oxford.contoller.student_form_controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.oxford.BO.Custom.Impl.StudentBOImpl;
import lk.ijse.oxford.BO.Custom.StudentBO;
import lk.ijse.oxford.DTO.StudentDTO;
import lk.ijse.oxford.DTO.UserDTO;
import lk.ijse.oxford.DTO.tm.StudentTm;
import lk.ijse.oxford.DAO.Custom.Impl.StudentDAOImpl;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddStudentFormContoller {
    @FXML
    private JFXTextField txtStudentName;
    @FXML
    private Label lblStId;
    @FXML
    private JFXTextField txtContactNumber;
    @FXML
    private Label lblUserId;
    @FXML
    private JFXTextField txtGrade;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private TableColumn<?,?>colStId;
    @FXML
    private TableColumn<?,?>colUserId;
    @FXML
    private TableColumn<?,?>colStName;
    @FXML
    private TableColumn<?,?>colContact;
    @FXML
    private TableColumn<?,?>colStAddress;
    @FXML
    private TableColumn<?,?>colStGrade;
    @FXML
    private TableView<StudentTm>tblStudent;
    private List<StudentDTO> studentList = new ArrayList<>();

    StudentBO studentBO = new StudentBOImpl();

    private UserDTO user;
    public void setUser(UserDTO user) {
        this.user=user;
        setUserId(user);
    }

    private void setUserId(UserDTO user) {
        lblUserId.setText(this.user.getUId());
    }

    public void initialize(){
        this.studentList = getAllStudents();
        setCellValueFactory();
        loadStudentTable();
        loadNextStId();
    }

    private void loadNextStId() {
        try {
            String currentId = studentBO.currentId();
            String nextId = nextId(currentId);

            lblStId.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("S");
            int id = Integer.parseInt(split[1]);
            id++;

            // Format the ID with leading zeros using String.format
            return "S" + String.format("%03d", id);
        } else {
            return "S001";
        }
    }

    private List<StudentDTO> getAllStudents() {
        List<StudentDTO> customerList = null;
        try {
            customerList = studentBO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }

    private void loadStudentTable() {
        ObservableList<StudentTm> tmList = FXCollections.observableArrayList();

        for (StudentDTO student : studentList) {
            StudentTm customerTm = new StudentTm(
                    student.getStId(),
                    student.getUserId(),
                    student.getName(),
                    student.getContact(),
                    student.getAddress(),
                    student.getGrade()
            );

            tmList.add(customerTm);
        }
        tblStudent.setItems(tmList);
    }

    private void setCellValueFactory() {
        colStId.setCellValueFactory(new PropertyValueFactory<>("stId"));
        colStName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colStAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    public void btnStudentAddOnAction(ActionEvent actionEvent) {
        if (isValidate()){
            String id = lblStId.getText();
            String name = txtStudentName.getText();
            String address = txtAddress.getText();
            String tel = txtContactNumber.getText();
            String grade = txtGrade.getText();
            String userId = lblUserId.getText();

            StudentDTO student = new StudentDTO(id, grade,name , tel,address,userId);

            try {
                boolean isSaved = studentBO.save(student);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Data Saved!").show();
                    txtStudentName.setText("");
                    txtContactNumber.setText("");
                    txtAddress.setText("");
                    lblStId.setText("");
                    txtGrade.setText("");
                    lblUserId.setText("");
                    initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isValidate(){
        if(!Regex.setTextColor(TextFields.NAME,txtStudentName))return false;
        if(!Regex.setTextColor(TextFields.ADDRESS,txtAddress))return false;
        if(!Regex.setTextColor(TextFields.CONTACT,txtContactNumber))return false;
        if(!Regex.setTextColor(TextFields.GRADE,txtGrade))return false;
        return true;
    }

    public void txtGradeCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.GRADE,txtGrade);
    }

    public void txtAddressCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS,txtAddress);
    }

    public void txtStContactOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.CONTACT,txtContactNumber);
    }

    public void txtStNameCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtStudentName);
    }
}
