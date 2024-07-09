package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.DAO.Custom.PaymentDAO;
import lk.ijse.oxford.DAO.Custom.PaymentDetailDAO;
import lk.ijse.oxford.Entity.PaymentDetails;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.DTO.PaymentDetailsDTO;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PaymentDetailsDAOImpl implements PaymentDetailDAO {
    @Override
    public boolean save(List<PaymentDetails> pdList) throws SQLException, ClassNotFoundException {
        for (PaymentDetails entity : pdList) {
            if(!save(entity)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean save(PaymentDetails entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO Payment_Details VALUES(?, ?, ?, ?)",
                entity.getPayId(),
                entity.getSubId(),
                entity.getTotalFee(),
                entity.getSeats());

    }
}
