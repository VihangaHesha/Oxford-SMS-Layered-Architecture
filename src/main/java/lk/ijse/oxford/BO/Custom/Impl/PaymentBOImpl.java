package lk.ijse.oxford.BO.Custom.Impl;

import lk.ijse.oxford.BO.Custom.PaymentBO;
import lk.ijse.oxford.DAO.Custom.Impl.PaymentDAOImpl;
import lk.ijse.oxford.DAO.Custom.PaymentDAO;
import lk.ijse.oxford.DTO.CheckPaymentDTO;
import lk.ijse.oxford.DTO.PayDetailDTO;
import lk.ijse.oxford.DTO.PaymentDTO;
import lk.ijse.oxford.Entity.CheckPayment;
import lk.ijse.oxford.Entity.PayDetail;
import lk.ijse.oxford.Entity.Payment;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = new PaymentDAOImpl();
    public boolean getFromPayId(CheckPaymentDTO dto) throws SQLException, ClassNotFoundException {
        CheckPayment checkPayment = new CheckPayment(dto.getStId(), dto.getMonth());
        return paymentDAO.getFromPayId(checkPayment);
    }



    public  String currentId() throws SQLException, ClassNotFoundException {
        return paymentDAO.currentId();
    }

    public List<PayDetailDTO> getAll() throws SQLException, ClassNotFoundException {

        List<PayDetail> payDetail = paymentDAO.getAll();
        List<PayDetailDTO> payDetailDTOS = new ArrayList<>();

        for (PayDetail payDetails : payDetail){
            PayDetailDTO payDetailDTO = new PayDetailDTO(
                    payDetails.getPayId(),
                    payDetails.getStId(),
                    payDetails.getFee(),
                    payDetails.getDate()
            );
            payDetailDTOS.add(payDetailDTO);
        }
        return payDetailDTOS;
    }

    public  boolean save(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        Payment payment = new Payment(
                dto.getPayId(),
                dto.getAmount(),
                dto.getDate(),
                dto.getStId(),
                dto.getSubId());

        return paymentDAO.save(payment);
    }

    public  int getTotalPayments() throws SQLException, ClassNotFoundException {

        return paymentDAO.getTotalPayments();
    }
}
