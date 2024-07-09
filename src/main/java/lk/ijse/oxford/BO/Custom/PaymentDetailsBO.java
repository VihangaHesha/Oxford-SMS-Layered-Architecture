package lk.ijse.oxford.BO.Custom;

import lk.ijse.oxford.DTO.PaymentDetailsDTO;
import lk.ijse.oxford.Entity.PaymentDetails;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDetailsBO {

    boolean save(List<PaymentDetailsDTO> pdList) throws SQLException, ClassNotFoundException;
}
