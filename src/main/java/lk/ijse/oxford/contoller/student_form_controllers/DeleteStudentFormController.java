package lk.ijse.oxford.contoller.student_form_controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.oxford.BO.BOFactory;
import lk.ijse.oxford.BO.Custom.Impl.StudentBOImpl;
import lk.ijse.oxford.BO.Custom.StudentBO;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.DTO.StudentDTO;
import lk.ijse.oxford.DTO.tm.StudentTm;
import lk.ijse.oxford.DAO.Custom.Impl.StudentDAOImpl;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteStudentFormController {
    @FXML
    private BarChart bcStudentChart;
    @FXML
    private Label lblStudentCount;
    private int studentCount;
    @FXML
    private JFXTextField txtStudentId;
    @FXML
    private JFXTextField txtStudentName;
    @FXML
    private TableColumn<?,?> colStId;
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
    private TableView<StudentTm> tblStudent;
    private List<StudentDTO> studentList = new ArrayList<>();

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize(){
        this.studentList = getAllStudents();
        setCellValueFactory();
        loadStudentTable();
        try {
            studentAttendance(bcStudentChart);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            studentCount = studentBO.getCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setStudentCount(studentCount);
    }

    private void setStudentCount(int studentCount) {
        lblStudentCount.setText(String.valueOf(studentCount));
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
            StudentTm studentTm = new StudentTm(
                    student.getStId(),
                    student.getUserId(),
                    student.getName(),
                    student.getContact(),
                    student.getAddress(),
                    student.getGrade()
            );

            tmList.add(studentTm);
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
    public void btnStudentDeleteOnAction(ActionEvent actionEvent) {
        if (isValidate()){
            String id = txtStudentId.getText();

            try {
                boolean isDeleted = studentBO.delete(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Data Deleted!").show();
                    initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteClickedOnAction(MouseEvent mouseEvent) {
        StudentTm selectedItem = tblStudent.getSelectionModel().getSelectedItem();
        txtStudentId.setText(selectedItem.getStId());
        txtStudentName.setText(selectedItem.getName());
    }

    public boolean isValidate(){
        if(!Regex.setTextColor(TextFields.NAME,txtStudentName))return false;
        if(!Regex.setTextColor(TextFields.SID,txtStudentId))return false;
        return true;
    }
    public void txtStIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.SID,txtStudentId);
    }
    public void txtStNameCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtStudentName);
    }

    private void studentAttendance(BarChart<String , Number> bcStudentChart) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "select MONTHNAME(date) as month, count(AttendMark) attendMark from Attendance group by MONTHNAME(date);";

        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        while (resultSet.next()) {
            String month = resultSet.getString("month");
            int attendMark = resultSet.getInt("attendMark");
            series.getData().add(new XYChart.Data<>(month, attendMark));
        }

        bcStudentChart.getData().add(series);

        for(Node n:bcStudentChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #0C87F2;");
        }
    }
}
