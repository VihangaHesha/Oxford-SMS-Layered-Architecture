package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.BO.Custom.SalaryBO;
import lk.ijse.oxford.DAO.Custom.SalaryDAO;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.DTO.SalaryDTO;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public  ResultSet getTotalSalary() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT SUM(Amount) AS total_salary FROM Salary");
        return  resultSet;
    }
}
