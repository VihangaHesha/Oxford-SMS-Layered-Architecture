package lk.ijse.oxford.DAO;

import lk.ijse.oxford.BO.SuperBO;
import lk.ijse.oxford.Entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    public List<T> getAll() throws SQLException, ClassNotFoundException ;


    public  boolean save(T entity) throws SQLException, ClassNotFoundException ;


    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;


    public  boolean update(T entity) throws SQLException, ClassNotFoundException ;


    public  T searchById(String id) throws SQLException, ClassNotFoundException ;


    public ResultSet getCount() throws SQLException, ClassNotFoundException ;


    public  ResultSet currentId() throws SQLException, ClassNotFoundException ;
}
