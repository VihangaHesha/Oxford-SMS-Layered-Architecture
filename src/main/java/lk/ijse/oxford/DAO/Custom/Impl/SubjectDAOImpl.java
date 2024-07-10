package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.DAO.Custom.SubjectDAO;
import lk.ijse.oxford.Entity.PaymentDetails;
import lk.ijse.oxford.Entity.Subject;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.DTO.SubjectDTO;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {

    @Override
    public  ResultSet getIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT SubId FROM Subject");
        return resultSet;
    }
    @Override
    public Subject searchByName(String subName) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT SubId,Description,FeeAmount,AvailableSeats FROM Subject WHERE Description = ?",
                subName);

        Subject subject = null;

        if (resultSet.next()) {
            String subId = resultSet.getString(1);
            String desc = resultSet.getString(2);
            double feeAmount = Double.parseDouble(resultSet.getString(3));
            int ableSeats = Integer.parseInt(resultSet.getString(4));

            subject = new Subject(subId,desc,feeAmount,ableSeats);
        }
        return subject;
    }

    public boolean updateSeats(List<PaymentDetails> pdList) throws SQLException, ClassNotFoundException {
        for (PaymentDetails od : pdList) {
            if(!updateQty(od)) {
                return false;
            }
        }
        return true;
    }

    public boolean updateQty(PaymentDetails od) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE Subject SET AvailableSeats = AvailableSeats - ? WHERE SubId = ?",
                od.getSeats(), od.getSubId());
        
    }

    @Override
    public List<Subject> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Subject entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Subject entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Subject searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet getCount() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet currentId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
