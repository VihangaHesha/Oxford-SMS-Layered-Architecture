package lk.ijse.oxford.BO.Custom.Impl;

import lk.ijse.oxford.BO.Custom.PlacePaymentBO;
import lk.ijse.oxford.DAO.Custom.Impl.PaymentDAOImpl;
import lk.ijse.oxford.DAO.Custom.Impl.PaymentDetailsRepo;
import lk.ijse.oxford.DAO.Custom.Impl.SubjectRepo;
import lk.ijse.oxford.DAO.Custom.PaymentDAO;
import lk.ijse.oxford.Entity.Payment;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.DTO.PlacePayment;

import java.sql.Connection;
import java.sql.SQLException;

public class PlacePaymentBOImpl implements PlacePaymentBO {
    PaymentDAO paymentDAO = new PaymentDAOImpl();
    @Override
    public boolean placePayment(PlacePayment po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        Payment payment = new Payment(po.getPayment().getPayId(),
        po.getPayment().getAmount(),po.getPayment().getDate(),po.getPayment().getStId(),po.getPayment().getSubId());

        try {
            boolean isPaymentSaved = paymentDAO.save(payment);
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
