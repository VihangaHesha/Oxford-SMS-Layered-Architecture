package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.BO.Custom.Impl.PaymentBOImpl;
import lk.ijse.oxford.BO.Custom.PaymentBO;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.DTO.PlacePayment;

import java.sql.Connection;
import java.sql.SQLException;

public class PlacePaymentRepo {
    PaymentBO paymentBO = new PaymentBOImpl();

    public boolean placePayment(PlacePayment po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isPaymentSaved = paymentBO.save(po.getPayment());
            if (isPaymentSaved) {
                boolean isPaymentDetailSaved = PaymentDetailsRepo.save(po.getPdList());
                if (isPaymentDetailSaved) {
                    boolean isSubjectSeatsUpdate = SubjectRepo.updateSeats(po.getPdList());
                    if (isSubjectSeatsUpdate) {
                        connection.commit();
                        return true;
                    }
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
