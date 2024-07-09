package lk.ijse.oxford.BO.Custom;

import lk.ijse.oxford.DTO.CheckPaymentDTO;
import lk.ijse.oxford.DTO.PayDetailDTO;
import lk.ijse.oxford.DTO.PaymentDTO;
import lk.ijse.oxford.Entity.CheckPayment;
import lk.ijse.oxford.Entity.PayDetail;
import lk.ijse.oxford.Entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO {
    public boolean getFromPayId(CheckPaymentDTO dto) throws SQLException, ClassNotFoundException ;

    public  String currentId() throws SQLException, ClassNotFoundException ;

    public List<PayDetailDTO> getAll() throws SQLException, ClassNotFoundException ;

    public  boolean save(PaymentDTO dto) throws SQLException, ClassNotFoundException ;

    public  int getTotalPayments() throws SQLException, ClassNotFoundException ;
}
