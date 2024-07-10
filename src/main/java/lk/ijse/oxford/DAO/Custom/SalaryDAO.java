package lk.ijse.oxford.DAO.Custom;

import lk.ijse.oxford.BO.SuperBO;
import lk.ijse.oxford.DAO.CrudDAO;
import lk.ijse.oxford.Entity.Salary;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SalaryDAO extends CrudDAO<Salary> {
    public ResultSet getTotalSalary() throws SQLException, ClassNotFoundException ;
}
