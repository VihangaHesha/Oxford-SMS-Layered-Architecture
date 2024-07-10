package lk.ijse.oxford.DAO.Custom;

import lk.ijse.oxford.DAO.CrudDAO;
import lk.ijse.oxford.DTO.SubjectDTO;
import lk.ijse.oxford.Entity.PaymentDetails;
import lk.ijse.oxford.Entity.Subject;

import java.awt.geom.RectangularShape;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface SubjectDAO extends CrudDAO<Subject> {
    public ResultSet getIds() throws SQLException, ClassNotFoundException;

    public Subject searchByName(String subName) throws SQLException, ClassNotFoundException;

    public boolean updateSeats(List<PaymentDetails> pdList) throws SQLException, ClassNotFoundException ;

    public boolean updateQty(PaymentDetails od) throws SQLException, ClassNotFoundException ;
}
