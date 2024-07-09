package lk.ijse.oxford.BO.Custom;

import lk.ijse.oxford.DTO.PaymentDetailsDTO;
import lk.ijse.oxford.DTO.PlacePaymentDTO;

import java.sql.SQLException;
import java.util.List;

public interface PlacePaymentBO {
    public boolean save(List<PaymentDetailsDTO> pdList) throws SQLException, ClassNotFoundException ;
    public boolean placePayment(PlacePaymentDTO po) throws SQLException ;
}
