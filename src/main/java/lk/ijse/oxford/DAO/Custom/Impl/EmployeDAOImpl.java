package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.DAO.Custom.EmployeeDAO;
import lk.ijse.oxford.Entity.Employee;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.DTO.EmployeeDTO;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeDAOImpl implements EmployeeDAO {
    @Override
    public  List<Employee> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");


        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String contact = resultSet.getString(4);
            String type = resultSet.getString(5);
            String userId = resultSet.getString(6);

            Employee employee = new Employee(id,name,address,contact,type,userId);
            employeeList.add(employee);
        }
        return employeeList;
    }
    
    @Override
    public  boolean save(Employee entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO employee VALUES(?,?,?,?,?,?)",
                entity.getEmpId(),
                entity.getName(),
                entity.getContact(),
                entity.getAddress(),
                entity.getType(),
                entity.getUserId());
    }
    
    @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM Employee WHERE StId =?",id);

    }
    
    @Override
    public  boolean update(Employee entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE Student SET Name = ? , Type = ? , Contact = ? , Address = ? ,UserId = ? WHERE EmpId = ?",
                entity.getName(),
                entity.getContact(),
                entity.getAddress(),
                entity.getType(),
                entity.getUserId(),
                entity.getEmpId());

    }
    
    @Override
    public  Employee searchById(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * From  Employee  where EmpId=?",id);
        Employee employee = new Employee(
                resultSet.getString("empId"),
                resultSet.getString("name"),
                resultSet.getString("contact"),
                resultSet.getString("address"),
                resultSet.getString("type"),
                resultSet.getString("userId"));
        return employee;
    }
    
    @Override
    public  ResultSet getCount() throws SQLException, ClassNotFoundException {
        
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) employee_count FROM Employee");
        return resultSet;
        
    }
    
    @Override
    public  ResultSet currentId() throws SQLException, ClassNotFoundException {
        
        ResultSet resultSet = SQLUtil.execute("SELECT EmpId FROM Employee ORDER BY EmpId desc LIMIT 1");
        
        return  resultSet;
    }
}
