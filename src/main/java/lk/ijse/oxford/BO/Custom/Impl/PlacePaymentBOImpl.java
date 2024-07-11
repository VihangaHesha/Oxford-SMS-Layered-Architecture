package lk.ijse.oxford.BO.Custom.Impl;

import lk.ijse.oxford.BO.Custom.PlacePaymentBO;
import lk.ijse.oxford.DAO.Custom.Impl.PaymentDAOImpl;
import lk.ijse.oxford.DAO.Custom.Impl.PaymentDetailsDAOImpl;
import lk.ijse.oxford.DAO.Custom.Impl.SubjectDAOImpl;
import lk.ijse.oxford.DAO.Custom.PaymentDAO;
import lk.ijse.oxford.DAO.Custom.PaymentDetailDAO;
import lk.ijse.oxford.DAO.Custom.SubjectDAO;
import lk.ijse.oxford.DAO.DAOFactory;
import lk.ijse.oxford.DTO.PaymentDetailsDTO;
import lk.ijse.oxford.Entity.Payment;
import lk.ijse.oxford.Entity.PaymentDetails;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.DTO.PlacePaymentDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlacePaymentBOImpl implements PlacePaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    PaymentDetailDAO paymentDetailDAO = (PaymentDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENTDETAILS);

    SubjectDAO subjectDAO = (SubjectDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUBJECT);

    public boolean updateSeats(List<PaymentDetailsDTO> pdList) throws SQLException, ClassNotFoundException {
        List<PaymentDetails> paymentDetails = new ArrayList<>(
          pdList.stream().map(pd -> new PaymentDetails(
              pd.getPayId(),
              pd.getSubId(),
              pd.getTotalFee(),
              pd.getSeats()
          )).toList()
        );
        return subjectDAO.updateSeats(paymentDetails);
    }

    @Override
    public boolean save(List<PaymentDetailsDTO> pdList) throws SQLException, ClassNotFoundException {
        List<PaymentDetails> paymentDetails = new ArrayList<>(
                pdList.stream().map(pd -> new PaymentDetails(
                        pd.getPayId(),
                        pd.getSubId(),
                        pd.getTotalFee(),
                        pd.getSeats()
                )).toList()
        );

        return paymentDetailDAO.save(paymentDetails);
    }

    @Override
    public boolean placePayment(PlacePaymentDTO po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        Payment payment = new Payment(po.getPayment().getPayId(),
        po.getPayment().getAmount(),po.getPayment().getDate(),po.getPayment().getStId(),po.getPayment().getSubId());

        try {
            boolean isPaymentSaved = paymentDAO.save(payment);
            if (isPaymentSaved) {
                boolean isPaymentDetailSaved = save(po.getPdList());
                if (isPaymentDetailSaved) {
                    boolean isSubjectSeatsUpdate = updateSeats(po.getPdList());
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
