package lk.ijse.oxford.DAO.Custom;

import lk.ijse.oxford.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SalaryDAO {
    public ResultSet getTotalSalary() throws SQLException, ClassNotFoundException ;
}
