package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.BO.Custom.AttendanceBO;
import lk.ijse.oxford.BO.Custom.Impl.AttendanceBOImpl;
import lk.ijse.oxford.DTO.AttendMarkingDTO;
import lk.ijse.oxford.DTO.AttendanceDTO;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.DTO.MarkAttendance;

import java.sql.Connection;
import java.sql.SQLException;

public class SetAttendanceRepo {
    AttendanceBO attendanceBO = new AttendanceBOImpl();
    public static boolean markAttendance(MarkAttendance markAttendance) throws SQLException {
//        AttendMarkingDTO attendMarkingDTO = new AttendMarkingDTO(markAttendance.getAttendMarking());
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isChecked = PaymentRepo.getFromPayId(markAttendance.getCheckPayment());
            if (isChecked) {
                boolean isAttendMarked = attendanceBO.save(markAttendance.getAttendMarking());
                if (isAttendMarked) {
                        connection.commit();
                        return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
