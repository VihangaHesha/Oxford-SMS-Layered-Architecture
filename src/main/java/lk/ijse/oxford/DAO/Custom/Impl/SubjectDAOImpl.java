package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.DAO.Custom.SubjectDAO;
import lk.ijse.oxford.Entity.PaymentDetails;
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

    public  List<String> getIds() throws SQLException {

        String sql = "SELECT description FROM Subject";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> subList = new ArrayList<>();

        while (resultSet.next()) {
            subList.add(resultSet.getString(1));
        }
        return subList;
    }

    public SubjectDTO searchByName(String subName) throws SQLException {
        String sql = "SELECT SubId,Description,FeeAmount,AvailableSeats FROM Subject WHERE Description = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, subName);
        ResultSet resultSet = pstm.executeQuery();

        SubjectDTO subject = null;

        if (resultSet.next()) {
            String subId = resultSet.getString(1);
            String desc = resultSet.getString(2);
            double feeAmount = Double.parseDouble(resultSet.getString(3));
            int ableSeats = Integer.parseInt(resultSet.getString(4));

            subject = new SubjectDTO(subId,desc,feeAmount,ableSeats);
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
}
