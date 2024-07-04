package lk.ijse.oxford.DAO.Custom;

import lk.ijse.oxford.Entity.Classroom;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ClassroomDAO {
    public List<Classroom> getAll() throws SQLException, ClassNotFoundException ;

    public  boolean save(Classroom classroom) throws SQLException, ClassNotFoundException ;

    public  ResultSet getClassCount() throws SQLException, ClassNotFoundException ;

    public  ResultSet getClassCapacity() throws SQLException, ClassNotFoundException ;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public  boolean update(Classroom classroom) throws SQLException, ClassNotFoundException ;

    public ResultSet currentId() throws SQLException, ClassNotFoundException;
}
