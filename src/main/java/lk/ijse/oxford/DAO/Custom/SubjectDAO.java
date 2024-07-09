package lk.ijse.oxford.DAO.Custom;

import lk.ijse.oxford.DTO.SubjectDTO;
import lk.ijse.oxford.Entity.PaymentDetails;

import java.sql.SQLException;
import java.util.List;

public interface SubjectDAO {
    public List<String> getIds() throws SQLException ;

    public SubjectDTO searchByName(String subName) throws SQLException ;

    public boolean updateSeats(List<PaymentDetails> pdList) throws SQLException, ClassNotFoundException ;

    public boolean updateQty(PaymentDetails od) throws SQLException, ClassNotFoundException ;
}
