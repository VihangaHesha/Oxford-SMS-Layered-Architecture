package lk.ijse.oxford.BO.Custom.Impl;

import lk.ijse.oxford.BO.Custom.AttendanceBO;
import lk.ijse.oxford.BO.Custom.SetAttendanceBO;
import lk.ijse.oxford.DAO.Custom.Impl.PaymentDAOImpl;
import lk.ijse.oxford.DAO.Custom.PaymentDAO;
import lk.ijse.oxford.DTO.AttendMarkingDTO;
import lk.ijse.oxford.Entity.CheckPayment;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.DTO.MarkAttendanceDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class SetAttendanceBOImpl implements SetAttendanceBO {
    AttendanceBO attendanceBO = new AttendanceBOImpl();

    PaymentDAO paymentDAO = new PaymentDAOImpl();


    public boolean markAttendance(MarkAttendanceDTO markAttendance) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        AttendMarkingDTO attendMarking = new AttendMarkingDTO(markAttendance.getAttendMarking().getAttendId(),
                markAttendance.getAttendMarking().getDate(),
                markAttendance.getAttendMarking().getAttendMark(),
                markAttendance.getAttendMarking().getStId());

        CheckPayment checkPayment = new CheckPayment(
                markAttendance.getCheckPayment().getStId(),
                markAttendance.getCheckPayment().getMonth());

        try {
            boolean isChecked = paymentDAO.getFromPayId(checkPayment);
            if (isChecked) {
                boolean isAttendMarked = attendanceBO.save(attendMarking);
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
