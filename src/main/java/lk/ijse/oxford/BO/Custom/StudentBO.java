package lk.ijse.oxford.BO.Custom;

import lk.ijse.oxford.BO.SuperBO;
import lk.ijse.oxford.DAO.Custom.StudentDAO;
import lk.ijse.oxford.DTO.StudentDTO;
import lk.ijse.oxford.Entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {
    public List<StudentDTO> getAll() throws SQLException, ClassNotFoundException ;

    public  boolean save(StudentDTO dto) throws SQLException, ClassNotFoundException ;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public  boolean update(StudentDTO dto) throws SQLException, ClassNotFoundException ;

    public int getCount() throws SQLException, ClassNotFoundException ;

    public  String currentId() throws SQLException, ClassNotFoundException ;
}


