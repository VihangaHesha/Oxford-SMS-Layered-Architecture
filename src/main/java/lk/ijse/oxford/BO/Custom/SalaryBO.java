package lk.ijse.oxford.BO.Custom;

import lk.ijse.oxford.DTO.SalaryDTO;
import lk.ijse.oxford.Entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SalaryBO {
    public List<SalaryDTO> getAll() throws SQLException, ClassNotFoundException ;
    public  SalaryDTO searchByCode(String empId) throws SQLException, ClassNotFoundException ;

    public  int getTotalSalary() throws SQLException, ClassNotFoundException ;
}
