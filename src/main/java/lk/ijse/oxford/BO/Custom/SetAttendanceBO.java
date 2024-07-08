package lk.ijse.oxford.BO.Custom;

import lk.ijse.oxford.DTO.MarkAttendanceDTO;

import java.sql.SQLException;

public interface SetAttendanceBO {
    public boolean markAttendance(MarkAttendanceDTO markAttendance) throws SQLException ;
}
