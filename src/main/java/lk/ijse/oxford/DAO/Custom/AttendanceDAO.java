package lk.ijse.oxford.DAO.Custom;

import lk.ijse.oxford.DTO.AttendMarkingDTO;
import lk.ijse.oxford.Entity.AttendMarking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public interface AttendanceDAO {
    public ResultSet getAttendanceCount(LocalDate date) throws SQLException, ClassNotFoundException ;

    public ResultSet currentId() throws SQLException, ClassNotFoundException ;

    public boolean save(AttendMarking attendMarking) throws SQLException, ClassNotFoundException ;
}
