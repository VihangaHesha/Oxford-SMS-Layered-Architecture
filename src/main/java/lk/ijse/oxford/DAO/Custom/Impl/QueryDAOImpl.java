package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.DAO.Custom.QueryDAO;
import lk.ijse.oxford.DTO.AttendanceDTO;
import lk.ijse.oxford.Entity.Attendance;
import lk.ijse.oxford.Entity.Salary;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public Attendance searchById(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT s.Name,a.Date,a.AttendMark,a.AttendId,a.StId FROM attendance a LEFT JOIN  Student s ON a.StId = s.StId WHERE a.StId= ?",id);

        Attendance attendance = null;

        if (resultSet.next()) {
            String stId = resultSet.getString(1);
            Date date = Date.valueOf(resultSet.getString(2));
            String attendMark = resultSet.getString(3);
            String attendId = resultSet.getString(4);
            String name = resultSet.getString(5);

            attendance = new Attendance(stId,date,attendMark,attendId,name);
        }

        return attendance;
    }
    @Override
    public  List<Salary> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT s.SalaryId,s.Amount,s.Date,s.EmpId,e.Name FROM Salary s JOIN Employee e ON s.EmpId=e.EmpId");

        List<Salary> salaryList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String amount = String.valueOf(Double.parseDouble(resultSet.getString(2)));
            Date date = Date.valueOf(resultSet.getString(3));
            String empId = resultSet.getString(4);
            String name = resultSet.getString(5);

            Salary salary = new Salary(id,amount,date,empId,name);
            salaryList.add(salary);
        }
        return salaryList;
    }

    public List<Attendance> getAttendanceAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT s.Name,a.Date,a.AttendMark,a.AttendId,a.StId FROM attendance a LEFT JOIN  Student s ON a.StId = s.StId");

        List<Attendance>attendanceList = new ArrayList<>();
        Attendance attendance = null;
        while (resultSet.next()) {
            String stId = resultSet.getString(1);
            Date date = Date.valueOf(resultSet.getString(2));
            String attendMark = resultSet.getString(3);
            String attendId = resultSet.getString(4);
            String name = resultSet.getString(5);

            attendance = new Attendance(name, date, attendMark,stId,attendId);
            attendanceList.add(attendance);
        }
        return attendanceList;
    }

    @Override
    public  Salary searchByCode(String empId) throws SQLException, ClassNotFoundException {
        
        ResultSet resultSet = SQLUtil.execute("SELECT * From  Salary s JOIN Employee e ON s.EmpId=e.EmpId WHERE EmpId=?",
                empId);
        
        if(resultSet.next()) {
            return new Salary(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return null;
    }
}
