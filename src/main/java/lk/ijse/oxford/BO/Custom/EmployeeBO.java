package lk.ijse.oxford.BO.Custom;

import lk.ijse.oxford.BO.SuperBO;
import lk.ijse.oxford.DTO.EmployeeDTO;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {

    
    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException ;

    
    public  boolean save(EmployeeDTO dto) throws SQLException, ClassNotFoundException ;

    
    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;

    
    public  boolean update(EmployeeDTO dto) throws SQLException, ClassNotFoundException ;

    
    public  EmployeeDTO searchByCode(String id) throws SQLException, ClassNotFoundException ;

    
    public  int getEmployeeCount() throws SQLException, ClassNotFoundException ;

    
    public  String currentId() throws SQLException, ClassNotFoundException ;
}
