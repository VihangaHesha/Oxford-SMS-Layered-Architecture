package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.DAO.Custom.PaymentDAO;
import lk.ijse.oxford.Entity.CheckPayment;
import lk.ijse.oxford.Entity.PayDetail;
import lk.ijse.oxford.Entity.Payment;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.DTO.PayDetailDTO;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    public boolean getFromPayId(CheckPayment entity) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT DISTINCT StId AS StudentID FROM Payment WHERE MONTH(Date) = ? AND StId = ?",entity);

        // Check if the student ID exists in the result set
        if (resultSet.next()) {
            String id = resultSet.getString(1);
            if (id.equals(entity.getStId())) {
                return true;
            }
        }
        return false;
    }



    public  ResultSet currentId() throws SQLException, ClassNotFoundException {
        
        ResultSet resultSet = SQLUtil.execute("SELECT PayId FROM Payment ORDER BY PayId desc LIMIT 1");
        return resultSet;
    }

    public  List<PayDetail> getAllPayments() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Payment ORDER BY PayId desc");
        
        List<PayDetail>payDetailList = new ArrayList<>();
        while (resultSet.next()) {
            String payId = resultSet.getString(3);
            double fee = Double.parseDouble(resultSet.getString(4));
            Date date = Date.valueOf(resultSet.getString(2));
            String stId = resultSet.getString(1);

            PayDetail payDetail = new PayDetail(payId,stId,fee,date);
            payDetailList.add(payDetail);
        }
        return payDetailList;
    }

    public  boolean save(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Payment VALUES(?, ?, ?, ?)",
                entity.getPayId(),
                entity.getStId(),
                entity.getDate(),
                entity.getAmount());
    }

    public  int getTotalPayments() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT SUM(amount) AS total_payment FROM Payment");

        int totPayment= 0;
        if (resultSet.next()){
            totPayment=resultSet.getInt("total_payment");
        }
        return totPayment;
    }

    @Override
    public List<Payment> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Payment searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet getCount() throws SQLException, ClassNotFoundException {
        return null;
    }
}
