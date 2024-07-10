package lk.ijse.oxford.BO.Custom;

import lk.ijse.oxford.BO.SuperBO;
import lk.ijse.oxford.DTO.MarkAttendanceDTO;

import java.sql.SQLException;

public interface SetAttendanceBO extends SuperBO {
    public boolean markAttendance(MarkAttendanceDTO markAttendance) throws SQLException ;
}
