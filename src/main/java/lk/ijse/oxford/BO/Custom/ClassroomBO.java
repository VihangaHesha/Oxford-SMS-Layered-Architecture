package lk.ijse.oxford.BO.Custom;

import lk.ijse.oxford.DTO.ClassroomDTO;
import lk.ijse.oxford.Entity.Classroom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ClassroomBO {
    public List<ClassroomDTO> getAll() throws SQLException, ClassNotFoundException ;

    public  boolean save(ClassroomDTO dto) throws SQLException, ClassNotFoundException ;

    public  int getClassCount() throws SQLException, ClassNotFoundException ;

    public  int getClassCapacity() throws SQLException, ClassNotFoundException ;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public  boolean update(ClassroomDTO dto) throws SQLException, ClassNotFoundException ;

    public String currentId() throws SQLException, ClassNotFoundException ;

}
