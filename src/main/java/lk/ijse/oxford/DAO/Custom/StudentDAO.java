package lk.ijse.oxford.DAO.Custom;

import lk.ijse.oxford.Entity.Student;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentDAO {
    public List<Student> getAll() throws SQLException, ClassNotFoundException ;

    public  boolean save(Student student) throws SQLException, ClassNotFoundException ;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public  boolean update(Student student) throws SQLException, ClassNotFoundException ;

    public ResultSet getStudentCount() throws SQLException, ClassNotFoundException ;

    public  ResultSet currentId() throws SQLException, ClassNotFoundException ;
}
