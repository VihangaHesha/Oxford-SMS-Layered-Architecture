package lk.ijse.oxford.contoller.student_form_controllers;

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
import lk.ijse.oxford.BO.Custom.AttendanceBO;
import lk.ijse.oxford.BO.Custom.Impl.AttendanceBOImpl;
import lk.ijse.oxford.DTO.AttendanceDTO;
import lk.ijse.oxford.DTO.tm.AttedanceTm;
import lk.ijse.oxford.DAO.Custom.Impl.AttendanceDAOImpl;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentAttendanceController {
    @FXML
    private Label lblCurrentAttendance;
    private int currentAttendance;
    @FXML
    private Label lblStudentName;
    @FXML
    private Label lblAttendDate;
    @FXML
    private Label lblAttendMark;
    @FXML
    private JFXTextField txtStudentId;
    @FXML
    private TableColumn<?,?> colStId;
    @FXML
    private TableColumn<?,?> colStName;
    @FXML
    private TableColumn<?,?>colAttendId;
    @FXML
    private TableColumn<?,?>colAttendMark;
    @FXML
    private TableColumn<?,?>colDate;
    @FXML
    private TableView<AttedanceTm> tblAttendance;
    private List<AttendanceDTO> attendanceList = new ArrayList<>();

    AttendanceBO attendanceBO = (AttendanceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ATTENDANCE);

    public void initialize(){
        LocalDate date = LocalDate.now();
        this.attendanceList = getAllAttendance();
        setCellValueFactory();
        loadStudentTable();
        try {
            currentAttendance = attendanceBO.getAttendanceCount(date);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setAttendCount(currentAttendance);
    }

    private void setAttendCount(int currentAttendance) {
        lblCurrentAttendance.setText(String.valueOf(currentAttendance));
    }

    private List<AttendanceDTO> getAllAttendance() {
        List<AttendanceDTO> attendanceList = null;
        try {
            attendanceList = attendanceBO.getAttendanceAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return attendanceList;
    }

    private void loadStudentTable() {
        ObservableList<AttedanceTm> tmList = FXCollections.observableArrayList();

        for (AttendanceDTO attendance : attendanceList) {
            AttedanceTm attedanceTm = new AttedanceTm(
                    attendance.getAttendId(),
                    attendance.getDate(),
                    attendance.getAttendMark(),
                    attendance.getStId(),
                    attendance.getName()
            );

            tmList.add(attedanceTm);
        }
        tblAttendance.setItems(tmList);
    }

    private void setCellValueFactory() {
        colAttendId.setCellValueFactory(new PropertyValueFactory<>("attendId"));
        colStName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAttendMark.setCellValueFactory(new PropertyValueFactory<>("attendMark"));
        colStId.setCellValueFactory(new PropertyValueFactory<>("stId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    public void btnAttendSearchOnAction(ActionEvent actionEvent) {
        if (isValidate()){
            String id =txtStudentId.getText();

            try {
                AttendanceDTO attendance = attendanceBO.searchById(id);

                if (attendance != null) {
                    lblStudentName.setText(attendance.getName());
                    lblAttendDate.setText(String.valueOf(attendance.getDate()));
                    lblAttendMark.setText(attendance.getAttendMark());
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void getStudentDetailOnAction(MouseEvent mouseEvent) {
        AttedanceTm selectedItem = tblAttendance.getSelectionModel().getSelectedItem();
        lblStudentName.setText(selectedItem.getName());
        lblAttendDate.setText(String.valueOf(selectedItem.getDate()));
        lblAttendMark.setText(selectedItem.getAttendMark());
        txtStudentId.setText(selectedItem.getStId());
    }

    public boolean isValidate(){
        if(!Regex.setTextColor(TextFields.SID,txtStudentId))return false;
        return true;
    }
    public void txtStIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.SID,txtStudentId);
    }
}
