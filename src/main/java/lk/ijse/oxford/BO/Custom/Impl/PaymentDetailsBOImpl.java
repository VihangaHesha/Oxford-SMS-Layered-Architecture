package lk.ijse.oxford.BO.Custom.Impl;

import lk.ijse.oxford.BO.Custom.PaymentDetailsBO;
import lk.ijse.oxford.BO.Custom.PlacePaymentBO;
import lk.ijse.oxford.DAO.Custom.Impl.PaymentDetailsDAOImpl;
import lk.ijse.oxford.DAO.Custom.PaymentDetailDAO;
import lk.ijse.oxford.DTO.PaymentDetailsDTO;
import lk.ijse.oxford.Entity.PaymentDetails;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDetailsBOImpl implements PaymentDetailsBO {

    PaymentDetailDAO paymentDetailDAO = new PaymentDetailsDAOImpl();

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

    /*@Override
    public boolean save(PaymentDetails entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO Payment_Details VALUES(?, ?, ?, ?)",
                entity.getPayId(),
                entity.getSubId(),
                entity.getTotalFee(),
                entity.getSeats());

    }*/
}
