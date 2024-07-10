package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.DAO.Custom.AttendanceDAO;
import lk.ijse.oxford.DTO.AttendMarkingDTO;
import lk.ijse.oxford.Entity.AttendMarking;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

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
    public List<AttendMarking> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(AttendMarking attendMarking) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO Attendance VALUES (?,?,?,?)",
                attendMarking.getAttendId(),
                attendMarking.getDate(),
                attendMarking.getAttendMark(),
                attendMarking.getStId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(AttendMarking entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public AttendMarking searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet getCount() throws SQLException, ClassNotFoundException {
        return null;
    }

}
