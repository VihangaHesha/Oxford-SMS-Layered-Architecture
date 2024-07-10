package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.BO.Custom.SalaryBO;
import lk.ijse.oxford.DAO.Custom.SalaryDAO;
import lk.ijse.oxford.Entity.Salary;
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

    @Override
    public List<Salary> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Salary entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Salary entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Salary searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet getCount() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet currentId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
