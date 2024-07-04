package lk.ijse.oxford.DAO.Custom;

import lk.ijse.oxford.Entity.Employee;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeDAO {
    
    public  List<Employee> getAll() throws SQLException, ClassNotFoundException ;

    
    public  boolean save(Employee employee) throws SQLException, ClassNotFoundException ;

    
    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;

    
    public  boolean update(Employee employee) throws SQLException, ClassNotFoundException ;

    
    public  Employee searchByCode(String id) throws SQLException, ClassNotFoundException ;

    
    public  ResultSet getEmployeeCount() throws SQLException, ClassNotFoundException ;

    
    public  ResultSet currentId() throws SQLException, ClassNotFoundException ;
}
