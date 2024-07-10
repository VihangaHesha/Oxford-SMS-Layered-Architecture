package lk.ijse.oxford.DAO.Custom;

import lk.ijse.oxford.DAO.CrudDAO;
import lk.ijse.oxford.Entity.CheckPayment;
import lk.ijse.oxford.Entity.PayDetail;
import lk.ijse.oxford.Entity.Payment;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {
    public  List<PayDetail> getAllPayments() throws SQLException, ClassNotFoundException;
    public boolean getFromPayId(CheckPayment entity) throws SQLException, ClassNotFoundException ;
    public  int getTotalPayments() throws SQLException, ClassNotFoundException ;
}
