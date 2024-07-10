package lk.ijse.oxford.BO.Custom;

import lk.ijse.oxford.BO.SuperBO;
import lk.ijse.oxford.DAO.Custom.SubjectDAO;
import lk.ijse.oxford.DTO.AttendMarkingDTO;
import lk.ijse.oxford.DTO.AttendanceDTO;
import lk.ijse.oxford.Entity.AttendMarking;
import lk.ijse.oxford.Entity.Attendance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface AttendanceBO extends SuperBO {

    public AttendanceDTO searchById(String id) throws SQLException, ClassNotFoundException ;
    public List<AttendanceDTO> getAttendanceAll() throws SQLException, ClassNotFoundException ;
    public int getAttendanceCount(LocalDate date) throws SQLException, ClassNotFoundException ;
    public String currentId() throws SQLException, ClassNotFoundException ;
    public boolean save(AttendMarkingDTO dto) throws SQLException, ClassNotFoundException ;
}
