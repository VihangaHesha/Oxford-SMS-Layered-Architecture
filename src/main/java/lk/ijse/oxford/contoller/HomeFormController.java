package lk.ijse.oxford.contoller;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import lk.ijse.oxford.BO.BOFactory;
import lk.ijse.oxford.BO.Custom.*;
import lk.ijse.oxford.BO.Custom.Impl.*;
import lk.ijse.oxford.DAO.Custom.Impl.*;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.DTO.*;
import lk.ijse.oxford.DTO.tm.TimeTableTm;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HomeFormController {
    @FXML
    private JFXTextField txtStId;
    @FXML
    private Label lblDates;
    @FXML
    private Label lblTimes;
    @FXML
    private Label lblStudentCount;
    private int studentCount;
    @FXML
    private Label lblEmployeeCount;
    private int employeeCount;
    @FXML
    private Label lblEquipCount;
    private int equipmentCount;
    @FXML
    private Label lblTotalSalary;
    private int totalSalary;
    @FXML
    private Label lblTotalPayment;
    @FXML
    private Label lblUserId;
    private int totalPayments;
    @FXML
    private TableColumn<TimeTableTm,String> colSubjectNo;
    @FXML
    private TableColumn<TimeTableTm,String> colSubject;
    @FXML
    private TableColumn<TimeTableTm,String> colTimePeriod;
    @FXML
    private TableColumn<TimeTableTm, Date> colDate;

    @FXML
    private BarChart bcStudentChart;
    @FXML
    private TableView<TimeTableTm> tblSchedule;
    @FXML
    private List<TimeTableDTO> timeTableList = new ArrayList<>();
    private volatile boolean stop = false;
    private String nextId;

    TimeTableBO timeTableBO = (TimeTableBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TIMETABLE);
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);
    SalaryBO salaryBO = (SalaryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SALARY);
    EquipmentBO equipmentBO = (EquipmentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EQUIPMENT);
    AttendanceBO attendanceBO = (AttendanceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ATTENDANCE);
    SetAttendanceBO setAttendanceBO = (SetAttendanceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SETATTENDANCE);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);


    public void initialize(){
        this.timeTableList = getTimeTable();
        setDate();
        setCellFactory();
        loadTimeTable();
        loadNextAttendId();
        try {
            studentAttendance(bcStudentChart);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            studentCount = studentBO.getCount();
            employeeCount = employeeBO.getEmployeeCount();
            equipmentCount = equipmentBO.getCount();
            totalSalary = salaryBO.getTotalSalary();
            totalPayments = paymentBO.getTotalPayments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setStudentCount(studentCount);
        setEmployeeCount(employeeCount);
        setEquipmentCount(equipmentCount);
        setTotalSalary(totalSalary);
        setTotalPayment(totalPayments);
    }

    private void setTotalPayment(int totalPayments) {
        lblTotalPayment.setText(String.valueOf("Rs."+totalPayments+"/="));
    }

    private void setTotalSalary(int totalSalary) {
        lblTotalSalary.setText(String.valueOf("Rs."+totalSalary+"/="));
    }

    private void setEquipmentCount(int equipmentCount) {
        lblEquipCount.setText(String.valueOf(equipmentCount));
    }

    private void setEmployeeCount(int employeeCount) {
        lblEmployeeCount.setText(String.valueOf(employeeCount));
    }

    private void setStudentCount(int studentCount) {
        lblStudentCount.setText(String.valueOf(studentCount));
    }

    private void loadTimeTable() {
        ObservableList<TimeTableTm> tmList = FXCollections.observableArrayList();

        for (TimeTableDTO tb : timeTableList) {
            TimeTableTm tableTm = new TimeTableTm(
                    tb.getTimeId(),
                    tb.getTimePeriod(),
                    tb.getSubject(),
                    tb.getDate()
            );

            tmList.add(tableTm);
        }
        tblSchedule.setItems(tmList);
        TimeTableTm selectedItem = tblSchedule.getSelectionModel().getSelectedItem();
    }

    private List<TimeTableDTO> getTimeTable() {
        List<TimeTableDTO> timeTableList = null;
        try {
            timeTableList = timeTableBO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return timeTableList;
    }
    private void setCellFactory() {
        colSubjectNo.setCellValueFactory(new PropertyValueFactory<>("timeId"));
        colTimePeriod.setCellValueFactory(new PropertyValueFactory<>("timePeriod"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

    }
    private void setDate() {

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM dd");
        String formattedDate = date.format(formatter);
        lblDates.setText(formattedDate);

        Thread thread = new Thread(() ->{
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
            while (!stop){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                   throw new RuntimeException(e);
                }
                final String timeNow = dateFormat.format(new java.util.Date());
                Platform.runLater(()->{
                    lblTimes.setText(timeNow);
                });
            }
        });
        thread.start();
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

    private void loadNextAttendId() {
        try {
            String currentId = attendanceBO.currentId();
            nextId = nextId(currentId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("A");
            int id = Integer.parseInt(split[1]);
            id++;

            // Format the ID with leading zeros using String.format
            return "A" + String.format("%03d", id);
        } else {
            return "A001";
        }
    }

    public void btnMarkAttendanceOnAction(ActionEvent actionEvent) {
        if (isValidate()){
            // Get the current date
            LocalDate currentDate = LocalDate.now();
            // Get the month number
            int monthNumber = currentDate.getMonthValue();
            LocalDate date = LocalDate.now();
            Date thisDate = Date.valueOf(date);
            String stId = txtStId.getText();
            String attendMark ="I";
            String attendId = nextId;


            AttendMarkingDTO attendMarking = new AttendMarkingDTO(attendId,thisDate,attendMark,stId);
            CheckPaymentDTO checkPayment = new CheckPaymentDTO(stId,monthNumber);

            MarkAttendanceDTO markAttendance = new MarkAttendanceDTO(attendMarking,checkPayment);

            try {
                boolean isChecked = setAttendanceBO.markAttendance(markAttendance);
                if(isChecked) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Attendance Marked!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING,
                            "You Haven't Paid The Class Fees For This Month! Pay it through the Student Form.").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
    public boolean isValidate(){
        if(!Regex.setTextColor(TextFields.SID,txtStId))return false;
        return true;
    }

    public void txtStIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.SID,txtStId);
    }
}
