package lk.ijse.oxford.BO.Custom;

import lk.ijse.oxford.BO.SuperBO;
import lk.ijse.oxford.DTO.SubjectDTO;
import lk.ijse.oxford.Entity.Subject;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SubjectBO extends SuperBO {
    public List<String> getIds() throws SQLException, ClassNotFoundException ;

    public SubjectDTO searchByName(String subName) throws SQLException, ClassNotFoundException ;
}
