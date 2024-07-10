package lk.ijse.oxford.DAO.Custom;

import lk.ijse.oxford.DAO.CrudDAO;
import lk.ijse.oxford.Entity.PaymentDetails;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDetailDAO extends CrudDAO<PaymentDetails> {
    public  boolean save(List<PaymentDetails> pdList) throws SQLException, ClassNotFoundException ;

}
