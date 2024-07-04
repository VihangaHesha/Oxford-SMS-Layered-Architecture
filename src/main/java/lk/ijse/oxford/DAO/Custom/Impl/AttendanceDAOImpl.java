package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.DAO.Custom.AttendanceDAO;
import lk.ijse.oxford.DTO.AttendMarkingDTO;
import lk.ijse.oxford.Entity.AttendMarking;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.*;
import java.time.LocalDate;

public class AttendanceDAOImpl implements AttendanceDAO {
    @Override
    public ResultSet getAttendanceCount(LocalDate date) throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SQLUtil.execute("SELECT COUNT(*) AS attend_count FROM Attendance WHERE AttendMArk='I' AND Date =?",date);

        return resultSet;
    }
    @Override
    public ResultSet currentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT AttendId FROM Attendance ORDER BY AttendId desc LIMIT 1");
        return resultSet;
    }
    @Override
    public boolean save(AttendMarking attendMarking) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO Attendance VALUES (?,?,?,?)",
                attendMarking.getAttendId(),
                attendMarking.getDate(),
                attendMarking.getAttendMark(),
                attendMarking.getStId());
    }

}
