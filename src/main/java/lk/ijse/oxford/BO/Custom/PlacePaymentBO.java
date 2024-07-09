package lk.ijse.oxford.BO.Custom;

import lk.ijse.oxford.DAO.Custom.Impl.PaymentDetailsRepo;
import lk.ijse.oxford.DAO.Custom.Impl.SubjectRepo;
import lk.ijse.oxford.DTO.PlacePayment;
import lk.ijse.oxford.Entity.Payment;
import lk.ijse.oxford.db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface PlacePaymentBO {
    public boolean placePayment(PlacePayment po) throws SQLException ;
}
