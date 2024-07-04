package lk.ijse.oxford.DAO.Custom;

import lk.ijse.oxford.Entity.Attendance;
import lk.ijse.oxford.Entity.Salary;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface QueryDAO {
    public List<Salary> getAll() throws SQLException, ClassNotFoundException ;

    public  Salary searchByCode(String empId) throws SQLException, ClassNotFoundException ;
    public Attendance searchById(String id) throws SQLException, ClassNotFoundException ;

    public  List<Attendance> getAttendanceAll() throws SQLException, ClassNotFoundException;
}
