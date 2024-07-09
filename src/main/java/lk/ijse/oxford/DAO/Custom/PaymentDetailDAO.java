package lk.ijse.oxford.DAO.Custom;

import lk.ijse.oxford.Entity.PaymentDetails;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDetailDAO {
    public  boolean save(List<PaymentDetails> pdList) throws SQLException, ClassNotFoundException ;
    public boolean save(PaymentDetails entity) throws SQLException, ClassNotFoundException ;
}
